package com.BraianZ.ComidaNaMesa.Delivery;

public record DeliveyRequestDTO(String name,
                                String cpf,
                                Integer age,
                                String phone,
                                String email,
                                String imgUrl,
                                String vehicle,
                                Integer evaluation,
                                Double deliveryFee,
                                String deliveryStatus) {
}
