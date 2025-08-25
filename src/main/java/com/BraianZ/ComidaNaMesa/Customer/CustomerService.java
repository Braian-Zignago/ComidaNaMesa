package com.BraianZ.ComidaNaMesa.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerResponseDTO register(CustomerRequestDTO customerRequestDTO){
        CustomerModel customerModel = customerMapper.forCustumerModel(customerRequestDTO);
        CustomerResponseDTO customerResponseDTO = null;
        try {
           customerResponseDTO = customerMapper.forCustomerResponseDTO(customerRepository.save(customerModel));
        } catch (Exception e) {
            throw new RuntimeException("Error saving customer: " + e.getMessage());
        }
        return customerResponseDTO;
    }

    public List<CustomerResponseDTO> getAll(){
        List<CustomerModel> customerModel = customerRepository.findAll();
        return customerMapper.forCustomerResponseDTOList(customerModel);
    }
}
