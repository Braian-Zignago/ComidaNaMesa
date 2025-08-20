package com.BraianZ.ComidaNaMesa.Addresses;

import com.BraianZ.ComidaNaMesa.Customer.CustomerModel;

public record AddressesRequestDTO(String cep,
                                  String state,
                                  String city,
                                  String neighborhood,
                                  String street,
                                  Long number,
                                  String complement,
                                  String referencePoint,
                                  CustomerModel customer) {
}
