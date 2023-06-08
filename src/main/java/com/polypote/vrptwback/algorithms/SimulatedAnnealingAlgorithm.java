package com.polypote.vrptwback.algorithms;

import com.polypote.vrptwback.client.DataSender;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.Abstractions.Operator;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class SimulatedAnnealingAlgorithm {

    private final int changesOfTemperatures = 1000;
    private final int movesAtTemperatureTk = 50;
    private final double µ = 0.8;
    private final Random random = new Random();
    private List<Operator> operatorList;

    public Solution resolve(final Solution x0) {
        double t0 = calculateInitialTemperature(x0);
        Solution xMin = x0;
        int fMin = x0.fitness();
        double currentTemperature = t0;
        for (int k = 0; k < changesOfTemperatures; k++) {
            for (int l = 0; l < movesAtTemperatureTk; l++) {
                List<Solution> neighbours = operatorList.get(random.nextInt(operatorList.size())).getNeighbours(xMin);
                Solution randomChoice = neighbours.get(random.nextInt(neighbours.size()));

                int neighboursFitness = randomChoice.fitness();
                double deltaFitness = neighboursFitness - fMin;
                if (deltaFitness < 0.0) {
                    if (neighboursFitness < fMin) {
                        xMin = randomChoice;
                        fMin = neighboursFitness;
                    }
                } else {
                    double randomDouble = random.nextDouble();
                    if (randomDouble < Math.exp(-deltaFitness / currentTemperature)) {
                        xMin = randomChoice;
                    }
                }
            }
            currentTemperature *= µ;
            DataSender.sendSolutionToFront(xMin);
        }
        return xMin;
    }

    private double calculateInitialTemperature(Solution x0) {
        return operatorList.get(random.nextInt(operatorList.size())).getNeighbours(x0)
                .stream()
                .max(Comparator.comparing(Solution::fitness))
                .map(Solution::fitness)
                .map(fitness -> -(fitness - x0.fitness()) / Math.log(µ))
                .orElseThrow(() -> new RuntimeException("Cannot find Max solution or calculate initial temperature"));
    }

}
