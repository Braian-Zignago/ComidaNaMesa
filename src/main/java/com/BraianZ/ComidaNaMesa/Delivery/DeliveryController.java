package com.BraianZ.ComidaNaMesa.Delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public String welcome() {
        return "Welcome to Delivery Service";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody DeliveyRequestDTO deliveyRequestDTO) {
        DeliveyResponseDTO deliveyResponseDTO = deliveryService.register(deliveyRequestDTO);
        if (deliveyResponseDTO == null) {
            return ResponseEntity.internalServerError().body("Error registering delivery");
        }
        return ResponseEntity.ok(deliveyResponseDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        List<DeliveyResponseDTO> deliveyResponseDTOList = deliveryService.getAll();
        if (deliveyResponseDTOList == null) {
            return ResponseEntity.internalServerError().body("Error getting deliveries");
        }
        return ResponseEntity.ok(deliveyResponseDTOList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        DeliveyResponseDTO deliveyResponseDTO = deliveryService.getById(id);
        if (deliveyResponseDTO == null) {
            return ResponseEntity.status(404).body("Delivery with id: " + id + " not found");
        }
        return ResponseEntity.status(302).body(deliveyResponseDTO);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        DeliveyResponseDTO deliveyResponseDTO = deliveryService.deleteById(id);
        if (deliveyResponseDTO == null) {
            return ResponseEntity.status(404).body("It was not possible to eliminate delivery with id: " + id + " not found");
        }
        return ResponseEntity.ok("Delivery: " + deliveyResponseDTO.name() + " with id: " + id + " was eliminated");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateById(@RequestBody DeliveyRequestDTO deliveyRequestDTO, @RequestParam Long id) {
        DeliveyResponseDTO deliveyResponseDTO = deliveryService.updateById(deliveyRequestDTO, id);
        if (deliveyResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("It was not possible to update delivery with id: " + id + " not found");
        }
        return ResponseEntity.ok(deliveyResponseDTO);
    }
}
