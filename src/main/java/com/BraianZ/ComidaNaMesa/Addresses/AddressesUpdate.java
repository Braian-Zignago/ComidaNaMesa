package com.BraianZ.ComidaNaMesa.Addresses;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressesUpdate {

    void updateAddresses(AddressesRequestDTO dto, @MappingTarget AddressesModel addressesModel);
}
