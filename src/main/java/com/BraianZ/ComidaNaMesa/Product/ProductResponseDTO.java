package com.BraianZ.ComidaNaMesa.Product;

import com.BraianZ.ComidaNaMesa.Store.StoreModel;

import java.math.BigDecimal;

public record ProductResponseDTO(Long id,
                                 String name,
                                 String description,
                                 String imgUrl,
                                 BigDecimal price,
                                 String isAvailable,
                                 StoreModel store) {
}
