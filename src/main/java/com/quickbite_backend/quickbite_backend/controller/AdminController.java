package com.quickbite_backend.quickbite_backend.controller;

import com.quickbite_backend.quickbite_backend.model.Order;
import com.quickbite_backend.quickbite_backend.model.Seller;
import com.quickbite_backend.quickbite_backend.model.User;
import com.quickbite_backend.quickbite_backend.repository.OrderRepository;
import com.quickbite_backend.quickbite_backend.repository.SellerRepository;
import com.quickbite_backend.quickbite_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SellerRepository sellerRepository;

    // 1. Get total counts for dashboard
  @GetMapping("/stats")
public Map<String, Long> getStats() {
    long users = userRepository.count();       // total users
    long sellers = sellerRepository.count();   // total sellers from Seller table
    long orders = orderRepository.count();     // total orders

    Map<String, Long> stats = new HashMap<>();
    stats.put("totalUsers", users);
    stats.put("totalSellers", sellers);       // <-- correct repository
    stats.put("totalOrders", orders);

    return stats;
} 



    // 2. Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findByRoleIgnoreCase("USER");
    }

    // 3. Get all sellers
    @GetMapping("/sellers")
        public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @PostMapping("/sellers")
public Seller createSeller(@RequestBody Seller seller) {
    if (seller.getRole() == null || seller.getRole().isEmpty()) {
        seller.setRole("SELLER");  // ensure role is always set
    }
    return sellerRepository.save(seller);
}



    // 4. Get all orders
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 5. Simple admin login (static username/password)
    @PostMapping("/login")
public Map<String, String> adminLogin(@RequestParam String username, @RequestParam String password) {
    Map<String, String> response = new HashMap<>();
    if (username.equals("varsha") && password.equals("varsha13")) {
        response.put("status", "success");
        response.put("message", "Login successful");
    } else {
        response.put("status", "error");
        response.put("message", "Invalid credentials");
    }
    return response;
}

}
