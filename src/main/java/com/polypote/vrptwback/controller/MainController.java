package com.polypote.vrptwback.controller;

import com.polypote.vrptwback.generator.RandomSolutionGenerator;
import com.polypote.vrptwback.model.Root;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @PostMapping("/random")
    public ResponseEntity<Void> generateRandomSolution(@RequestBody Root root) {
        new Thread(() -> {
            final RandomSolutionGenerator randomSolutionGenerator = new RandomSolutionGenerator();
            randomSolutionGenerator.generate(root);
        }).start();
        return ResponseEntity.ok(null);
    }
}
