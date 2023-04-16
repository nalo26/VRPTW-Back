package com.polypote.vrptwback.service;

import com.polypote.vrptwback.client.DataSender;
import com.polypote.vrptwback.generator.RandomSolutionGenerator;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomSolutionService {

    @Autowired
    private RandomSolutionGenerator generator;

    public void generateAndSend(Root root) {
        Solution solution = generator.generate(root);
        DataSender.sendSolutionToFront(solution);
    }
}
