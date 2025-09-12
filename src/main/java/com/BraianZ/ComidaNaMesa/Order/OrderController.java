package com.BraianZ.ComidaNaMesa.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to orders";
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.create(orderRequestDTO);
        return ResponseEntity.ok(orderResponseDTO);
    }

    public ResponseEntity<?> getOrderById() {
        return ResponseEntity.ok("Order by id");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        List<OrderResponseDTO> orderResponseDTO = orderService.getAll();
        return ResponseEntity.ok(orderResponseDTO);
    }


    public ResponseEntity<?> updateOrder() {
        return ResponseEntity.ok("Order updated");
    }


}
