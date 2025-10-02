package com.quickbite_backend.quickbite_backend.controller;

import com.quickbite_backend.quickbite_backend.model.Seller;
import com.quickbite_backend.quickbite_backend.repository.SellerRepository;
import com.quickbite_backend.quickbite_backend.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/seller")
@CrossOrigin(origins = "http://localhost:3000")
public class SellerController {

    private final SellerService sellerService;

    private SellerRepository sellerRepository;

    public SellerController(SellerService sellerService, SellerRepository sellerRepository) {
        this.sellerService = sellerService;
    }

   @PostMapping("/signup")
public ResponseEntity<Map<String, Object>> registerSeller(@RequestBody Map<String, String> payload) {
    String ownerName = payload.get("ownerName");
    String restaurantName = payload.get("restaurantName");
    String email = payload.get("email");
    String password = payload.get("password");

    Seller seller = new Seller(ownerName, restaurantName, email, password);
    sellerRepository.save(seller);

    Map<String, Object> response = new HashMap<>();
    response.put("message", "Signup successful");
    response.put("seller", seller);
    response.put("role", seller.getRole());

    return ResponseEntity.ok(response);
}


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Seller seller) {
        Seller existing = sellerService.login(seller.getEmail(), seller.getPassword());
        Map<String, Object> response = new HashMap<>();

        if (existing != null) {
            response.put("message", "Login Successful");
            response.put("role", existing.getRole());
            response.put("seller", existing);
            return ResponseEntity.ok(response);
        }

        response.put("error", "Invalid Email or Password");
        return ResponseEntity.status(401).body(response);
    }
}
