package com.BraianZ.ComidaNaMesa.Order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "delivery", ignore = true)
    @Mapping(target = "taxPlataform", ignore = true)
    @Mapping(target = "deliveryTime", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    OrderModel forOrderModel(OrderRequestDTO orderRequestDTO);

    OrderModel forOrderModel(OrderResponseDTO orderResponseDTO);

    OrderResponseDTO forOrderResponseDTO(OrderModel orderModel);

    List<OrderResponseDTO> forOrderResponseDTOList(List<OrderModel> orderModelList);
}
