package com.BraianZ.ComidaNaMesa.Customer;

import com.BraianZ.ComidaNaMesa.Addresses.AddressesModel;

import java.math.BigDecimal;
import java.util.List;

public record CustomerResponseDTO(Long id,
                                String name,
                                Integer age,
                                String cpf,
                                String email,
                                String phone,
                                String imgUrl,
                                BigDecimal wallet,
                                List<AddressesModel> addresses) {
}
