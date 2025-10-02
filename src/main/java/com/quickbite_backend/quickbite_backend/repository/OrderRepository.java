package com.quickbite_backend.quickbite_backend.repository;

import com.quickbite_backend.quickbite_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByRestaurantSellerId(Long sellerId);
    List<Order> findByCustomerName(String customerName);
    // count() already available from JpaRepository
}
