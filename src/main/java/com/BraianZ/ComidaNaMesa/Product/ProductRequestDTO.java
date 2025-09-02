package com.BraianZ.ComidaNaMesa.Product;

import com.BraianZ.ComidaNaMesa.Store.StoreModel;

import java.math.BigDecimal;

public record ProductRequestDTO(String name,
                                String description,
                                String imgUrl,
                                Integer quantity,
                                BigDecimal price,
                                String isAvailable,
                                StoreModel store) {
}
