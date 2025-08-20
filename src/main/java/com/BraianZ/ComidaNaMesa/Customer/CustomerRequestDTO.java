package com.BraianZ.ComidaNaMesa.Customer;

import java.math.BigDecimal;

public record CustomerRequestDTO(String name,
                                 Integer age,
                                 String cpf,
                                 String email,
                                 String phone,
                                 String imgUrl,
                                 BigDecimal wallet) {
}
