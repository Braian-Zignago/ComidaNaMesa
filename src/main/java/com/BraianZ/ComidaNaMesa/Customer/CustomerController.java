package com.BraianZ.ComidaNaMesa.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public String welcome(){
        return "Welcome";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO customerResponseDTO = customerService.register(customerRequestDTO);
        if(customerResponseDTO == null){
            return ResponseEntity.internalServerError().body("Error registering customer");
        } else {
            return ResponseEntity.ok(customerResponseDTO);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll(){
        List<CustomerResponseDTO> customerResponseDTOList = customerService.getAll();
        if(customerResponseDTOList == null){
            return ResponseEntity.internalServerError().body("Error getting customers");
        } else {
            return ResponseEntity.ok(customerResponseDTOList);
        }


    }
}
