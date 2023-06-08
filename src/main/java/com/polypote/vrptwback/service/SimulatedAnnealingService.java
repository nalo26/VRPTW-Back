package com.polypote.vrptwback.service;

import com.polypote.vrptwback.algorithms.SimulatedAnnealingAlgorithm;
import com.polypote.vrptwback.client.DataSender;
import com.polypote.vrptwback.generator.RandomSolutionGenerator;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.Abstractions.Operator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SimulatedAnnealingService {

    private List<Operator> operators;

    private RandomSolutionGenerator randomSolutionGenerator;

    public void callSimulatedAnnealing(Root root) {
        Solution initial = randomSolutionGenerator.generate(root);
        SimulatedAnnealingAlgorithm algorithm = new SimulatedAnnealingAlgorithm(operators);
        Solution result = algorithm.resolve(initial);
        DataSender.sendSolutionToFront(result);
    }
}
