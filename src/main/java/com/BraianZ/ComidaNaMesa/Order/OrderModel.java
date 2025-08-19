package com.BraianZ.ComidaNaMesa.Order;

import com.BraianZ.ComidaNaMesa.Customer.CustomerModel;
import com.BraianZ.ComidaNaMesa.Delivery.DeliveryModel;
import com.BraianZ.ComidaNaMesa.Product.ProductModel;
import com.BraianZ.ComidaNaMesa.Store.StoreModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreModel store;

    @ManyToMany
    @JoinTable(
            name = "order_product", // Nombre de la tabla de enlace
            joinColumns = @JoinColumn(name = "order_id"), // Clave foránea de Pedido
            inverseJoinColumns = @JoinColumn(name = "product_id") // Clave foránea de Producto
    )
    private List<ProductModel> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", nullable = false)
    private DeliveryModel delivery;

    @Column(nullable = false, name = "tax_platform")
    private BigDecimal taxPlataform;

    @Column(nullable = false, name = "delivery_time")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime deliveryTime;

    @Column(nullable = false)
    private String status; // "PENDING", "IN_PROGRESS", "DELIVERED", "CANCELED"

    @Column(nullable = false)
    private BigDecimal totalPrice;

}
