package com.polypote.vrptwback.controller;

import com.polypote.vrptwback.Utils;
import com.polypote.vrptwback.generator.RandomSolutionGenerator;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.operators.Abstractions.Operator;
import com.polypote.vrptwback.operators.OperatorFactory;
import com.polypote.vrptwback.service.OperatorService;
import com.polypote.vrptwback.service.RandomSolutionService;
import com.polypote.vrptwback.service.SimulatedAnnealingService;
import com.polypote.vrptwback.service.TabouAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private RandomSolutionService randomSolutionService;

    @Autowired
    private RandomSolutionGenerator randomSolutionGenerator;
    private OperatorService operatorService;

    private TabouAlgorithmService tabouAlgorithmService;

    private SimulatedAnnealingService simulatedAnnealingService;

    @PostMapping("/random")
    public ResponseEntity<Void> generateRandomSolution(@RequestBody Root root) {
        new Thread(() -> randomSolutionService.generateAndSend(root)).start();
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{operator}/{type}")
    public ResponseEntity<Void> applyIntraExchangeOperator(@PathVariable String operator, @PathVariable String type, @RequestBody Root root) {
        operatorService = new OperatorService(OperatorFactory.getInstance().createOperator(operator, type), randomSolutionGenerator);
        new Thread(() -> operatorService.applyOperator(root)).start();
        return ResponseEntity.ok(null);
    }

    @PostMapping("/tabouSearch")
    public ResponseEntity<Void> tabouSearch(@RequestBody Root root, @RequestParam int nbIter, @RequestParam int tabouSize) {
        //operatorService = new OperatorService(operatorFactory.createOperator(root.getMethods(), randomSolutionGenerator));
        List<Operator> operatorList = Utils.fromStringList(root.getMethods());
        tabouAlgorithmService = new TabouAlgorithmService(operatorList, randomSolutionGenerator);
        new Thread(() -> tabouAlgorithmService.callTabouSearch(root, nbIter, tabouSize)).start();
        return ResponseEntity.ok(null);
    }

    @PostMapping("/annealing")
    public ResponseEntity<Void> annealingCall(@RequestBody Root root) {
        List<Operator> operatorList = Utils.fromStringList(root.getMethods());
        simulatedAnnealingService = new SimulatedAnnealingService(operatorList, randomSolutionGenerator);
        new Thread(() -> simulatedAnnealingService.callSimulatedAnnealing(root)).start();
        return ResponseEntity.ok(null);
    }
}
