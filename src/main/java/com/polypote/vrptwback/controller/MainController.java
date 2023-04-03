package com.polypote.vrptwback.controller;

import com.polypote.vrptwback.model.Root;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private Root root;

    @PostMapping("/send")
    public void sendData(@RequestBody final Root _root) {
        root = _root;
    }
}
