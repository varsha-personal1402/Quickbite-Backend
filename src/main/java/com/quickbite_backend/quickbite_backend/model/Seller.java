package com.quickbite_backend.quickbite_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;       // Owner's Name
    private String restaurantName;  // Restaurant Name
    private String email;
    private String password;
    private String role = "SELLER"; // Default role

    public Seller() {}

    public Seller(String ownerName, String restaurantName, String email, String password) {
        this.ownerName = ownerName;
        this.restaurantName = restaurantName;
        this.email = email;
        this.password = password;
        this.role = "SELLER";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
