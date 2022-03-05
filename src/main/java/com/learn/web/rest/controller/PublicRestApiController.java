package com.learn.web.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicRestApiController {

    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("User Public Home");
    }

    @GetMapping("/signing")
    public ResponseEntity<String> signIn() {
        return ResponseEntity.ok("User SignIn");
    }

    @GetMapping("/signup")
    public ResponseEntity<String> signUp() {
        return ResponseEntity.ok("User SignUp");
    }

}
