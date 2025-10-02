package com.quickbite_backend.quickbite_backend.service;

import com.quickbite_backend.quickbite_backend.model.User;
import com.quickbite_backend.quickbite_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signup(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            user.setRole("USER");
            return userRepository.save(user);
        }
        return null; // email already exists
    }

    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
