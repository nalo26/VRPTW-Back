package com.polypote.vrptwback.controller;

import com.polypote.vrptwback.client.DataSender;
import com.polypote.vrptwback.generator.RandomSolutionGenerator;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.ExchangeOperator;
import com.polypote.vrptwback.service.OperatorService;
import com.polypote.vrptwback.service.RandomSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private RandomSolutionService randomSolutionService;

    private OperatorService operatorService;

    @PostMapping("/random")
    public ResponseEntity<Void> generateRandomSolution(@RequestBody Root root) {
        new Thread(() -> {
            randomSolutionService.generateAndSend(root);
        }).start();
        return ResponseEntity.ok(null);
    }

    @PostMapping("/exchange/intra")
    public ResponseEntity<Void> applyIntraExchangeOperator(@RequestBody Root root) {
        new Thread(() -> {
            RandomSolutionGenerator randomSolutionGenerator = new RandomSolutionGenerator();
            Solution solution = randomSolutionGenerator.generate(root);
            DataSender.sendSolutionToFront(solution);
            operatorService = new OperatorService(new ExchangeOperator());
            operatorService.applyOperator(solution);
        }).start();
        return ResponseEntity.ok(null);
    }
}
