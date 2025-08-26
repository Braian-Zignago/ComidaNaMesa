package com.BraianZ.ComidaNaMesa.Addresses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressesController {

    private final AddressesService addressesService;

    @GetMapping
    public String welcome() {
        return "Welcome Addresses EndPoints";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AddressesRequestDTO addressesRequestDTO) {
        AddressesResponseDTO addressesResponseDTO = addressesService.register(addressesRequestDTO);
        if (addressesResponseDTO == null) {
            return ResponseEntity.internalServerError().body("Error registering Address");
        }
        return ResponseEntity.ok(addressesResponseDTO);

    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        List<AddressesResponseDTO> addressesResponseDTO = addressesService.getAll();
        if (addressesResponseDTO == null) {
            return ResponseEntity.internalServerError().body("Error retrieving Addresses");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(addressesResponseDTO);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        AddressesResponseDTO addressesResponseDTO = addressesService.getById(id);
        if (addressesResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(addressesResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        AddressesResponseDTO addressesResponseDTO = addressesService.deleteById(id);
        if (addressesResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("It was not possible to eliminate Address with id: " + id + " not found");
        }
        return ResponseEntity.ok("Address: " + addressesResponseDTO.street() + " with id:" + id + " was eliminated ");
    }


}
