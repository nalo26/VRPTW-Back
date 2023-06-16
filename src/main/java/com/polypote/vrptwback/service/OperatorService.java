package com.polypote.vrptwback.service;

import com.polypote.vrptwback.client.DataSender;
import com.polypote.vrptwback.generator.RandomSolutionGenerator;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.Abstractions.Operator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.polypote.vrptwback.Utils.getBestSolution;

@AllArgsConstructor
@Service
public class OperatorService {

    private Operator operator;

    private RandomSolutionGenerator randomSolutionGenerator;

    public void applyOperator(Root root) {
        Solution randomSolution = randomSolutionGenerator.generate(root);
        DataSender.sendSolutionToFront(randomSolution);
        List<Solution> neighbours = operator.getNeighbours(randomSolution);
        Solution bestSolution;
        int previousFitness = Integer.MAX_VALUE;
        int fitness = randomSolution.fitness();
        while (previousFitness > fitness) {
            bestSolution = getBestSolution(neighbours);
            previousFitness = bestSolution.fitness();
            neighbours = operator.getNeighbours(bestSolution);
            bestSolution = getBestSolution(neighbours);
            fitness = bestSolution.fitness();
            DataSender.sendSolutionToFront(bestSolution);
        }
        System.out.println("Done !");
    }

}
