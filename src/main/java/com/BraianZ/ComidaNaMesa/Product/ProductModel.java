package com.BraianZ.ComidaNaMesa.Product;

import com.BraianZ.ComidaNaMesa.Store.StoreModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    @Column(length = 500, name = "img_url")
    private String imgUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "is_available", length = 3, nullable = false)
    private String isAvailable; // "YES", "NO"

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private StoreModel store;


}
