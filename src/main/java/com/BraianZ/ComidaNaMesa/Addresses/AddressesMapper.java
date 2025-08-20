package com.BraianZ.ComidaNaMesa.Addresses;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressesMapper {

    @Mapping(target = "id", ignore = true)
    AddressesModel forAddressesModel(AddressesRequestDTO addressesRequestDTO);

    AddressesResponseDTO forAddressesResponseDTO(AddressesModel addressesModel);

    List<AddressesResponseDTO> forAddressesResponseDTOList(List<AddressesModel> addressesModelList);
}
