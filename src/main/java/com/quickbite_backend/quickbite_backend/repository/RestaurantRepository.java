package com.quickbite_backend.quickbite_backend.repository;

import com.quickbite_backend.quickbite_backend.model.Restaurant;
import com.quickbite_backend.quickbite_backend.model.Seller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findBySeller(Seller seller);
}