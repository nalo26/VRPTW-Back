package com.polypote.vrptwback.controller;

import com.polypote.vrptwback.generator.RandomSolutionGenerator;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.operators.OperatorFactory;
import com.polypote.vrptwback.service.OperatorService;
import com.polypote.vrptwback.service.RandomSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private RandomSolutionService randomSolutionService;

    @Autowired
    private OperatorFactory operatorFactory;

    @Autowired
    private RandomSolutionGenerator randomSolutionGenerator;
    private OperatorService operatorService;

    @PostMapping("/random")
    public ResponseEntity<Void> generateRandomSolution(@RequestBody Root root) {
        new Thread(() -> randomSolutionService.generateAndSend(root)).start();
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{operator}/{type}")
    public ResponseEntity<Void> applyIntraExchangeOperator(@PathVariable String operator, @PathVariable String type, @RequestBody Root root) {
        operatorService = new OperatorService(operatorFactory.createOperator(operator, type), randomSolutionGenerator);
        new Thread(() -> operatorService.applyOperator(root)).start();
        return ResponseEntity.ok(null);
    }
}
