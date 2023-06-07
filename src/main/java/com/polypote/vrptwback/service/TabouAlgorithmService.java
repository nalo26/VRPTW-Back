package com.polypote.vrptwback.service;

import com.polypote.vrptwback.algorithms.TabouSearchAlgorithm;
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
public class TabouAlgorithmService {


    private List<Operator> operators;

    private RandomSolutionGenerator randomSolutionGenerator;


    public void callTabouSearch(Root root, int nbIter, int tabouSize) {
        Solution randomSolution = randomSolutionGenerator.generate(root);
        TabouSearchAlgorithm tabouSearchAlgorithm = new TabouSearchAlgorithm(nbIter, operators, tabouSize);
        Solution result = tabouSearchAlgorithm.resolve(randomSolution);
        DataSender.sendSolutionToFront(result);
    }
}
