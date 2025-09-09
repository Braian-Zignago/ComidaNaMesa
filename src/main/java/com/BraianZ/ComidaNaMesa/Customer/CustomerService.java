package com.BraianZ.ComidaNaMesa.Customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final CustomerUpdate customerUpdate;

    public CustomerResponseDTO register(CustomerRequestDTO customerRequestDTO) {
        CustomerModel customerModel = customerMapper.forCustumerModel(customerRequestDTO);
        CustomerResponseDTO customerResponseDTO = null;
        try {
            customerResponseDTO = customerMapper.forCustomerResponseDTO(customerRepository.save(customerModel));
        } catch (Exception e) {
            throw new RuntimeException("Error saving customer: " + e.getMessage());
        }
        return customerResponseDTO;
    }

    public List<CustomerResponseDTO> getAll() {
        List<CustomerModel> customerModel = customerRepository.findAll();
        return customerMapper.forCustomerResponseDTOList(customerModel);
    }

    public CustomerResponseDTO getById(Long id) {
        Optional<CustomerModel> customerModel = customerRepository.findById(id);
        return customerModel.map(customerMapper::forCustomerResponseDTO).orElse(null);

    }

    public  CustomerResponseDTO deleteById(Long id) {
        CustomerResponseDTO customerResponseDTO = getById(id);
        if (customerResponseDTO == null) {
            return null;
        }
        customerRepository.deleteById(id);
        return customerResponseDTO;
    }

    public CustomerResponseDTO updateById(CustomerRequestDTO customerRequestDTO, Long id) {
        CustomerModel customerModel = customerRepository.findById(id).orElseThrow();
        customerUpdate.updateCustomer(customerRequestDTO, customerModel);
        return customerMapper.forCustomerResponseDTO(customerRepository.save(customerModel));
    }
}
