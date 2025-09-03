package com.BraianZ.ComidaNaMesa.Store;

import com.BraianZ.ComidaNaMesa.Order.OrderModel;
import com.BraianZ.ComidaNaMesa.Product.ProductModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_store")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String cnpj;

    @Column(length = 30, nullable = false)
    private String phone;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(name = "img_url", length = 500)
    private String imgUrl;

    @Column(name = "is_open", nullable = false, length = 10)
    private String isOpen; // "OPEN" , "CLOSED"

    @Column(name = "specialty", length = 50, nullable = false)
    private String specialty;

    private Integer evaluaton;

    @Column(name = "wallet")
    private BigDecimal wallet;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductModel> products;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderModel> orders;

    @Column(length = 10, nullable = false)
    private String cep;

    @Column(length = 50, nullable = false)
    private String state;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 100, nullable = false)
    private String neighborhood;

    @Column(length = 100, nullable = false)
    private String street;

    @Column(nullable = false)
    private Long number;

    @Column(length = 50)
    private String complement;

    @Column(length = 100, name = "reference_point")
    private String referencePoint;

}
