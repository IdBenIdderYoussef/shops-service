package com.remote.shopsservice.controller;

import com.remote.shopsservice.model.User;
import com.remote.shopsservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            service.create(user);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
