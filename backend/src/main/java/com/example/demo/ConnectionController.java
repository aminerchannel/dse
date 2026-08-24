package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/connections")
public class ConnectionController {

    private final ConnectionTester connectionTester;

    public ConnectionController(ConnectionTester connectionTester) {
        this.connectionTester = connectionTester;
    }

    @PostMapping("/test")
    public ResponseEntity<String> postConnection(@Valid @RequestBody ConnectionRequest entity) {
        boolean ok = connectionTester.test(entity);

        if(!ok){
            return new ResponseEntity<>("connection failed",HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>("connection created successfully",HttpStatus.ACCEPTED);
    }
    
    
}
