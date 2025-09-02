package com.BraianZ.ComidaNaMesa.platform;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platform/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping
    public String welcome() {
        return "Welcome to Wallet Service";
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(WalletRequestDTO walletRequestDTO){
        WalletResponseDTO walletResponseDTO = walletService.create(walletRequestDTO);
        if (walletResponseDTO == null) {
            return ResponseEntity.internalServerError().body("Error creating wallet");
        }
        return ResponseEntity.ok(walletResponseDTO);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(Long id){
        WalletResponseDTO walletResponseDTO = walletService.getById(id);
        if (walletResponseDTO == null) {
            return ResponseEntity.status(404).body("Wallet with id: " + id + " not found");
        }
        return ResponseEntity.ok(walletResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(Long id){
        WalletResponseDTO walletResponseDTO = walletService.deleteById(id);
        if (walletResponseDTO == null) {
            return ResponseEntity.status(404).body("It was not possible to eliminate wallet with id: " + id + " not found");
        }
        return ResponseEntity.ok("Wallet with id: " + id + " was eliminated");
    }


}
