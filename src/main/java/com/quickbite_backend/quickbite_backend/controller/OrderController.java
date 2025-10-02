package com.quickbite_backend.quickbite_backend.controller;

import com.quickbite_backend.quickbite_backend.model.Order;
import com.quickbite_backend.quickbite_backend.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Get orders for a seller
    @GetMapping("/seller/{sellerId}")
    public List<Order> getOrdersBySeller(@PathVariable Long sellerId) {
        return orderService.getOrdersBySeller(sellerId);
    }

    // Place a new order
   @PostMapping
public Order placeOrder(@RequestBody Order order) {
    order.setStatus("Pending");

    // Make sure each OrderItem points back to the Order
    if (order.getItems() != null) {
        order.getItems().forEach(item -> item.setOrder(order));
    }

    return orderService.saveOrder(order);
}


    // Update order status
    @PutMapping("/{orderId}/status")
    public Order updateStatus(@PathVariable Long orderId, @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }

    // Delete an order
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/user/{username}")
public List<Order> getOrdersByUser(@PathVariable String username) {
    return orderService.getOrdersByUsername(username);
}

}
