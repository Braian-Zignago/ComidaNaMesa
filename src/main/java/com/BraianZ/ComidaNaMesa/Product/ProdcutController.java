package com.BraianZ.ComidaNaMesa.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProdcutController {

    private final ProductService productService;

    @GetMapping
    public String welcome() {
        return "Welcome to Product Service";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.register(productRequestDTO);
        if (productResponseDTO == null) {
            return ResponseEntity.internalServerError().body("Error registering product");
        }
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        List<ProductResponseDTO> productResponseDTO = productService.getAll();
        if (productResponseDTO == null) {
            return ResponseEntity.internalServerError().body("Error getting products");
        }
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ProductResponseDTO productResponseDTO = productService.getById(id);
        if (productResponseDTO == null) {
            return ResponseEntity.status(404).body("Product with id: " + id + " not found");
        }
        return ResponseEntity.status(302).body(productResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        ProductResponseDTO productResponseDTO = productService.deleteById(id);
        if (productResponseDTO == null) {
            return ResponseEntity.status(404).body("It was not possible to eliminate product with id: " + id + " not found");
        }
        return ResponseEntity.ok("Product: " + productResponseDTO.name() + " with id: " + id + " was eliminated");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateById(@RequestBody ProductRequestDTO productRequestDTO, @RequestParam Long id) {
        ProductResponseDTO productResponseDTO = productService.updateById(productRequestDTO, id);
        if (productResponseDTO == null) {
            return ResponseEntity.status(404).body("It was not possible to update product with id: " + id + " not found");
        }
        return ResponseEntity.ok(productResponseDTO);
    }
}
