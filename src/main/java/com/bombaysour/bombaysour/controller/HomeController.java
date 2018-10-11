package com.bombaysour.bombaysour.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(HomeController.class);

    @GetMapping("/")
    private ResponseEntity<String> home() {
        String message = "Something went wrong.";
        LOGGER.warn(">>> " + message);
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
}
