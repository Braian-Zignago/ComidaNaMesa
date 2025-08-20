package com.BraianZ.ComidaNaMesa.Store;

import java.math.BigDecimal;

public record StoreRequestDTO(String name,
                              String cnpj,
                              String phone,
                              String email,
                              String imgUrl,
                              String isOpen, // "OPEN" , "CLOSED"
                              String specialty,
                              Integer evaluaton,
                              BigDecimal wallet,
                              StoreAddress address) {
}
