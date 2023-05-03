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

    public void applyOperator(Root root) {
        Solution randomSolution = randomSolutionGenerator.generate(root);
        DataSender.sendSolutionToFront(randomSolution);
        List<Solution> neighbours = operator.getNeighbours(randomSolution);
        Solution bestSolution = neighbours.stream().max(Comparator.comparing(Solution::fitness)).orElseThrow(() -> new RuntimeException("Cannot find best solution"));
        DataSender.sendSolutionToFront(bestSolution);
    }

}
