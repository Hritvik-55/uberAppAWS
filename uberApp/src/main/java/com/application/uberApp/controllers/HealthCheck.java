package com.application.uberApp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping
    public ResponseEntity<String> healthCheckController(){
        return ResponseEntity.ok("OK");
    }
}
