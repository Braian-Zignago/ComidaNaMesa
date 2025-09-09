package com.BraianZ.ComidaNaMesa.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductUpdate {

    void updateProduct(ProductRequestDTO dto, @MappingTarget ProductModel product);
}
