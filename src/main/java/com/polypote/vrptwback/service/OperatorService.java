package com.polypote.vrptwback.service;

import com.polypote.vrptwback.client.DataSender;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.AbstractOperator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
public class OperatorService {

    AbstractOperator operator;

    public void applyOperator(Solution solution) {
        List<Solution> neighbours = operator.getNeighbours(solution);
        Solution bestSolution = neighbours.stream().max(Comparator.comparing(Solution::fitness)).orElseThrow(() -> new RuntimeException("Cannot find best solution"));
        DataSender.sendSolutionToFront(bestSolution);
    }

}
