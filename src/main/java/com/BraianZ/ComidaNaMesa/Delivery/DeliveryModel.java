package com.BraianZ.ComidaNaMesa.Delivery;

import com.BraianZ.ComidaNaMesa.Order.OrderModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_delivery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20, nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 50, nullable = false, unique = true)
    private String phone;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 500, name = "img_url")
    private String imgUrl;

    @Column(length = 50, nullable = false)
    private String vehicle;

    @Column(nullable = false)
    private Integer evaluation;

    @Column(precision = 8, scale = 2)
    private BigDecimal wallet;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Column(length = 20, nullable = false)
    private String deliveryStatus; // "OCCUPIED", "AVAILABLE", "OFFLINE"

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderModel> orders;
}
