package com.BraianZ.ComidaNaMesa.Store;

import com.BraianZ.ComidaNaMesa.Product.ProductModel;

import java.math.BigDecimal;
import java.util.List;

public record StoreResponseDTO(Long id,
                               String name,
                               String cnpj,
                               String phone,
                               String email,
                               String imgUrl,
                               String isOpen, // "OPEN", "CLOSED"
                               String specialty,
                               Integer evaluaton,
                               BigDecimal wallet,
                               StoreAddress address,
                               List<ProductModel> products) {
}
