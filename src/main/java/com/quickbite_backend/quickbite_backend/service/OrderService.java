package com.quickbite_backend.quickbite_backend.service;

import com.quickbite_backend.quickbite_backend.model.Order;
import com.quickbite_backend.quickbite_backend.model.OrderItem;
import com.quickbite_backend.quickbite_backend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrdersBySeller(Long sellerId) {
        return orderRepository.findByRestaurantSellerId(sellerId);
    }

    public Order saveorder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Order> getOrdersByUsername(String username) {
    return orderRepository.findByCustomerName(username);
    }

    public Order saveOrder(Order order) {
    if (order.getItems() != null) {
        for (OrderItem item : order.getItems()) {
            item.setOrder(order); // Link each item to this order
        }
    }
    return orderRepository.save(order);
}

}
