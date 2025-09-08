package com.BraianZ.ComidaNaMesa.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to orders";
    }

    public ResponseEntity<?> createOrder() {
        return ResponseEntity.ok("Order created");
    }

    public ResponseEntity<?> getOrderById() {
        return ResponseEntity.ok("Order by id");
    }

    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok("All orders");
    }

    public ResponseEntity<?> updateOrder() {
        return ResponseEntity.ok("Order updated");
    }


}
