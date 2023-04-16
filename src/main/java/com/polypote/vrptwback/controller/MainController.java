package com.polypote.vrptwback.controller;

import com.polypote.vrptwback.generator.RandomSolutionGenerator;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class MainController {

    private static final String FRONT_URL = "http://localhost:8050";

    @PostMapping("/send")
    public ResponseEntity<Solution> sendDataToFront(@RequestBody final Solution result) {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).location(new URI(FRONT_URL + "/update_graph")).body(result);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/random")
    public ResponseEntity<Void> generateRandomSolution(@RequestBody Root root) {
        new Thread(() -> {
            final RandomSolutionGenerator randomSolutionGenerator = new RandomSolutionGenerator();
            randomSolutionGenerator.generate(root);
        }).start();
        return ResponseEntity.ok(null);
    }
}
