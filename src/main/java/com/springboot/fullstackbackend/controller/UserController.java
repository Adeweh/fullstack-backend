package com.springboot.fullstackbackend.controller;

import com.springboot.fullstackbackend.exception.UserNotFoundException;
import com.springboot.fullstackbackend.model.User;
import com.springboot.fullstackbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User newUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(newUser));
    }

    @GetMapping("view")
    public ResponseEntity<?> getAllUsers(){

        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.findById(id)
                        .map(user -> {
                            user.setUsername(newUser.getUsername());
                            user.setFirstname(newUser.getFirstname());
                            user.setLastname(newUser.getLastname());
                            user.setEmail(newUser.getEmail());

                            return userRepository.save(user);
                        })
                .orElseThrow(() -> new UserNotFoundException(id)));
    }






}
