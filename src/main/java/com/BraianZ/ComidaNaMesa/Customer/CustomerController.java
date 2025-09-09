package com.BraianZ.ComidaNaMesa.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public String welcome() {
        return "Welcome";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customerResponseDTO = customerService.register(customerRequestDTO);
        if (customerResponseDTO == null) {
            return ResponseEntity.internalServerError().body("Error registering customer");
        } else {
            return ResponseEntity.ok(customerResponseDTO);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        List<CustomerResponseDTO> customerResponseDTOList = customerService.getAll();
        if (customerResponseDTOList == null) {
            return ResponseEntity.internalServerError().body("Error getting customers");
        } else {
            return ResponseEntity.ok(customerResponseDTOList);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
         CustomerResponseDTO customerResponseDTO = customerService.getById(id);
        if (customerResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with id: " + id + "not found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(customerResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        CustomerResponseDTO customerResponseDTO = customerService.deleteById(id);
        if (customerResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("It was not possible to eliminate customer with id: " + id + "not found");
        }
        return ResponseEntity.ok("Customer: " + customerResponseDTO.name() + " with id: " + id + "was eliminated");
    }

    @PutMapping
    public ResponseEntity<?> updateById(@RequestBody CustomerRequestDTO customerRequestDTO, @RequestParam("id") Long id) {
        CustomerResponseDTO customerResponseDTO = customerService.updateById(customerRequestDTO, id);
        if (customerResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("It was not possible to update customer with id: " + id + "not found");
        }
        return ResponseEntity.ok(customerResponseDTO);
    }
}
