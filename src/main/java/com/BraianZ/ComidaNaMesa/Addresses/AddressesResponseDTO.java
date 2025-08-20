package com.BraianZ.ComidaNaMesa.Addresses;

public record AddressesResponseDTO(Long id,
                                   String cep,
                                   String state,
                                   String city,
                                   String neighborhood,
                                   String street,
                                   Long number,
                                   String complement,
                                   String referencePoint) {
}
