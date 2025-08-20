package com.BraianZ.ComidaNaMesa.Order;

import com.BraianZ.ComidaNaMesa.Customer.CustomerModel;
import com.BraianZ.ComidaNaMesa.Product.ProductModel;
import com.BraianZ.ComidaNaMesa.Store.StoreModel;

import java.util.List;

public record OrderRequestDTO(CustomerModel customer,
                              StoreModel store,
                              List<ProductModel> products
                              ) {
}
