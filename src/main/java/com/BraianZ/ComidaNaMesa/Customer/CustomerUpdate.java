package com.BraianZ.ComidaNaMesa.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerUpdate {

    void updateCustomer(CustomerRequestDTO dto, @MappingTarget CustomerModel customer);
}
