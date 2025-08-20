package com.BraianZ.ComidaNaMesa.Product;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    ProductModel forProductModel(ProductRequestDTO productRequestDTO);

    ProductResponseDTO forProductResponseDTO(ProductModel productModel);

    List<ProductResponseDTO> forProductResponseDTOList(List<ProductModel> productModelList);
}
