package com.BraianZ.ComidaNaMesa.Store;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        List<StoreResponseDTO> storeResponseDTOList = storeService.getAll();
        if (storeResponseDTOList == null) {
            return ResponseEntity.internalServerError().body("Error getting stores");
        }

        return ResponseEntity.ok(storeResponseDTOList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        StoreResponseDTO storeResponseDTO = storeService.getById(id);
        if (storeResponseDTO == null) {
            return ResponseEntity.status(404).body("Store with id: " + id + " not found");
        }
        return ResponseEntity.status(302).body(storeResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        StoreResponseDTO storeResponseDTO = storeService.deleteById(id);
        if (storeResponseDTO == null) {
            return ResponseEntity.status(404).body("It was not possible to eliminate store with id: " + id + " not found");
        }
        return ResponseEntity.ok("Store: " + storeResponseDTO.name() + " with id: " + id + " was eliminated");
    }
}
