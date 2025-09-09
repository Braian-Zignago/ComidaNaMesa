package com.BraianZ.ComidaNaMesa.Store;

import com.BraianZ.ComidaNaMesa.Customer.CustomerModel;

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
                              String cep,
                              String state,
                              String city,
                              String neighborhood,
                              String street,
                              Long number,
                              String complement,
                              String referencePoint
                              ) {
}
