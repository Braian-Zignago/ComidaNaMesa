package com.BraianZ.ComidaNaMesa.Store;

import com.BraianZ.ComidaNaMesa.Addresses.AddressesModel;
import com.BraianZ.ComidaNaMesa.Order.OrderModel;
import com.BraianZ.ComidaNaMesa.Product.ProductModel;
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
    private List<ProductModel> products;

    @Embedded
    private StoreAddress address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<OrderModel> Orders;
}
