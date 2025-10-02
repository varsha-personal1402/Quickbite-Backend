package com.quickbite_backend.quickbite_backend.controller;

import com.quickbite_backend.quickbite_backend.model.User;
import com.quickbite_backend.quickbite_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Signup
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        User savedUser = userService.signup(user);
        if (savedUser != null) {
            return ResponseEntity.ok(Map.of(
                    "message", "Signup Successful",
                    "user", savedUser
            ));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Email already exists"));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userService.login(user.getEmail(), user.getPassword());
        if (existingUser != null) {
            return ResponseEntity.ok(Map.of(
                    "message", "Login Successful",
                    "user", existingUser
            ));
        }
        return ResponseEntity.status(401).body(Map.of("message", "Invalid Email or Password"));
    }
}
