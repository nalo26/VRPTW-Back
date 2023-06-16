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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class MainController {

    @Autowired
    private RandomSolutionService randomSolutionService;

    @Autowired
    private RandomSolutionGenerator randomSolutionGenerator;
    private OperatorService operatorService;

    private TabouAlgorithmService tabouAlgorithmService;

    private SimulatedAnnealingService simulatedAnnealingService;

    private Set<Thread> threads = new HashSet<>();

    @PostMapping("/random")
    public ResponseEntity<Void> generateRandomSolution(@RequestBody Root root) {
        stopAllThreads();
        Thread t = new Thread(() -> randomSolutionService.generateAndSend(root));
        t.start();
        threads.add(t);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{operator}/{type}")
    public ResponseEntity<Void> applyIntraExchangeOperator(@PathVariable String operator, @PathVariable String type, @RequestBody Root root) {
        stopAllThreads();
        operatorService = new OperatorService(OperatorFactory.getInstance().createOperator(operator, type), randomSolutionGenerator);
        Thread t = new Thread(() -> operatorService.applyOperator(root));
        t.start();
        threads.add(t);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/tabouSearch")
    public ResponseEntity<Void> tabouSearch(@RequestBody Root root, @RequestParam int nbIter, @RequestParam int tabouSize) {
        stopAllThreads();
        List<Operator> operatorList = Utils.fromStringList(root.getMethods());
        tabouAlgorithmService = new TabouAlgorithmService(operatorList, randomSolutionGenerator);
        Thread t = new Thread(() -> tabouAlgorithmService.callTabouSearch(root, nbIter, tabouSize));
        t.start();
        threads.add(t);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/annealing")
    public ResponseEntity<Void> annealingCall(@RequestBody Root root) {
        stopAllThreads();
        List<Operator> operatorList = Utils.fromStringList(root.getMethods());
        simulatedAnnealingService = new SimulatedAnnealingService(operatorList, randomSolutionGenerator);
        Thread t = new Thread(() -> simulatedAnnealingService.callSimulatedAnnealing(root));
        t.start();
        threads.add(t);
        return ResponseEntity.ok(null);
    }

    private void stopAllThreads() {
        for (Thread t : threads) {
            t.interrupt();
        }
        threads.clear();
    }
}
