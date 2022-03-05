package com.learn.web.rest.controller;


import com.learn.web.rest.modules.UserModule;
import com.learn.web.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRestApiController {

    @Autowired
    private UserService userService;


    @GetMapping("/{username}")
    public ResponseEntity<Optional<UserModule>> getUserByUsername(@PathVariable final String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<UserModule>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserModule> add(@RequestBody final UserModule module, UriComponentsBuilder builder) {
        return userService.save(module).map(user -> {
            URI location = builder.replacePath("/{username}").buildAndExpand(user.getUsername()).toUri();
            return ResponseEntity.created(location).body(user);
        }).orElseThrow(() -> new RuntimeException("unable to create user due to unexpected error occurred"));
    }
}