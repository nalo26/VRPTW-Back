package com.polypote.vrptwback.service;

import com.polypote.vrptwback.client.DataSender;
import com.polypote.vrptwback.generator.RandomSolutionGenerator;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.Abstractions.Operator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
public class OperatorService {

    private Operator operator;

    private RandomSolutionGenerator randomSolutionGenerator;

    private static Solution getBestSolution(List<Solution> neighbours) {
        return neighbours.stream().min(Comparator.comparing(Solution::fitness)).orElseThrow(() -> new RuntimeException("Cannot find best solution"));
    }

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
        System.out.println("Everyone is relocated");
    }

}
