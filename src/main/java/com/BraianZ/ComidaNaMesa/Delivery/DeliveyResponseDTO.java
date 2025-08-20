package com.BraianZ.ComidaNaMesa.Delivery;

public record DeliveyResponseDTO(Long id,
                                 String name,
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
