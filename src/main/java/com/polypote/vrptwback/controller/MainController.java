package com.polypote.vrptwback.controller;

import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.service.RandomSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    public RandomSolutionService randomSolutionService;

    @PostMapping("/random")
    public ResponseEntity<Void> generateRandomSolution(@RequestBody Root root) {
        new Thread(() -> {
            randomSolutionService.generateAndSend(root);
        }).start();
        return ResponseEntity.ok(null);
    }
}
