package com.BraianZ.ComidaNaMesa.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "orders", ignore = true)
    CustomerModel forCustumerModel(CustomerRequestDTO customerRequestDTO);

    CustomerModel forCustumerModel(CustomerResponseDTO customerResponseDTO);

    CustomerResponseDTO forCustomerResponseDTO(CustomerModel customerModel);

    List<CustomerResponseDTO> forCustomerResponseDTOList(List<CustomerModel> customerModelList);
}
