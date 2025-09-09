package com.BraianZ.ComidaNaMesa.Delivery;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DeliveryUpdate {

    void updateDelivery(DeliveyRequestDTO dto, @MappingTarget DeliveryModel delivery);
}
