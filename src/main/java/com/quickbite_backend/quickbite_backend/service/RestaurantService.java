package com.quickbite_backend.quickbite_backend.service;

import com.quickbite_backend.quickbite_backend.model.Restaurant;
import com.quickbite_backend.quickbite_backend.model.Seller;
import com.quickbite_backend.quickbite_backend.repository.RestaurantRepository;
import com.quickbite_backend.quickbite_backend.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final SellerRepository sellerRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, SellerRepository sellerRepository) {
        this.restaurantRepository = restaurantRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Restaurant> getRestaurantsBySeller(Seller seller) {
        return restaurantRepository.findBySeller(seller);
    }

    public Restaurant saveOrUpdate(Restaurant restaurant, Seller seller) {
        restaurant.setSeller(seller);
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }

    // ✅ Helper method
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    public List<Restaurant> getAllRestaurants() {
    return restaurantRepository.findAll();
}

}
