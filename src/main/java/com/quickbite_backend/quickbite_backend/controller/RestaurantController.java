package com.quickbite_backend.quickbite_backend.controller;

import com.quickbite_backend.quickbite_backend.model.Restaurant;
import com.quickbite_backend.quickbite_backend.model.Seller;
import com.quickbite_backend.quickbite_backend.service.RestaurantService;
import com.quickbite_backend.quickbite_backend.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final SellerService sellerService;

    public RestaurantController(RestaurantService restaurantService, SellerService sellerService) {
        this.restaurantService = restaurantService;
        this.sellerService = sellerService;
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Restaurant>> getRestaurants(@PathVariable Long sellerId) {
        Seller seller = sellerService.getSellerById(sellerId);
        return ResponseEntity.ok(restaurantService.getRestaurantsBySeller(seller));
    }

    @PostMapping("/seller/{sellerId}")
    public ResponseEntity<Restaurant> addOrUpdateRestaurant(@PathVariable Long sellerId, @RequestBody Restaurant restaurant) {
        Seller seller = sellerService.getSellerById(sellerId);
        Restaurant saved = restaurantService.saveOrUpdate(restaurant, seller);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{restaurantId}/seller/{sellerId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long restaurantId, @PathVariable Long sellerId) {
        Seller seller = sellerService.getSellerById(sellerId);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        if (!restaurant.getSeller().getId().equals(seller.getId())) {
            return ResponseEntity.status(403).body("Unauthorized");
        }

        restaurantService.deleteRestaurant(restaurant);
        return ResponseEntity.ok("Deleted successfully");
    }

    @GetMapping
public ResponseEntity<List<Restaurant>> getAllRestaurants() {
    List<Restaurant> restaurants = restaurantService.getAllRestaurants();
    return ResponseEntity.ok(restaurants);
}

}
