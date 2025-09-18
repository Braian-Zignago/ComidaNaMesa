package com.BraianZ.ComidaNaMesa.Store;

import org.mapstruct.Mapper;

import java.util.List;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "orders", ignore = true)
    StoreModel forStoreModel(StoreRequestDTO storeRequestDTO);

    StoreModel forStoreModel(StoreResponseDTO storeResponseDTO);

    StoreResponseDTO forStoreResponseDTO(StoreModel storeModel);

    List<StoreResponseDTO> forStoreResponseDTOList(List<StoreModel> storeModelList);
}
