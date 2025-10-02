package com.quickbite_backend.quickbite_backend.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    private final RazorpayClient razorpayClient;

    public PaymentController() throws RazorpayException {
        // Replace with your actual Razorpay Key ID and Secret
        this.razorpayClient = new RazorpayClient("YOUR_KEY_ID", "YOUR_KEY_SECRET");
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestParam double amount) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", amount * 100); // amount in paise
        options.put("currency", "INR");
        options.put("receipt", "order_rcptid_11");

        // Correct way: use orders.create() instead of Orders.create()
        Order order = razorpayClient.orders.create(options);

        return order.toString(); // return order details as JSON
    }
}
 