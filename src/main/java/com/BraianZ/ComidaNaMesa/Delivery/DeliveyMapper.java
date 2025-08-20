package com.BraianZ.ComidaNaMesa.Delivery;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    DeliveryModel forDeliveryModel(DeliveyRequestDTO deliveyRequestDTO);

    DeliveyResponseDTO forDeliveryResponseDTO(DeliveryModel deliveryModel);

    List<DeliveyResponseDTO> forDeliveryResponseDTOList(List<DeliveryModel> deliveryModelList);

}
