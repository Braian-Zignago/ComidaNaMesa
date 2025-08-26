package com.BraianZ.ComidaNaMesa.Store;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public String welcome() {
        return "Welcome to the Store!";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody StoreRequestDTO storeRequestDTO) {
        StoreResponseDTO storeResponseDTO = storeService.register(storeRequestDTO);
        if (storeResponseDTO == null) {
            return ResponseEntity.internalServerError().body("Error registering store");
        } else {
            return ResponseEntity.ok(storeResponseDTO);
        }

    }
}
