package com.BraianZ.ComidaNaMesa.Order;

import com.BraianZ.ComidaNaMesa.Customer.CustomerModel;
import com.BraianZ.ComidaNaMesa.Delivery.DeliveryModel;
import com.BraianZ.ComidaNaMesa.Product.ProductModel;
import com.BraianZ.ComidaNaMesa.Store.StoreModel;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

public record OrderResponseDTO(Long id,
                               CustomerModel customer,
                               StoreModel store,
                               List<ProductModel> products,
                               DeliveryModel delivery,
                               BigDecimal taxPlataform,
                               LocalTime deliveryTime,
                               String status,
                               BigDecimal totalPrice) {
}
