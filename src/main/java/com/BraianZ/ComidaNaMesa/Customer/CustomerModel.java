package com.BraianZ.ComidaNaMesa.Customer;

import com.BraianZ.ComidaNaMesa.Addresses.AddressesModel;
import com.BraianZ.ComidaNaMesa.Order.OrderModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 50, nullable = false, unique = true)
    private String cpf;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 30, nullable = false, unique = true)
    private String phone;

    @Column(length = 500, name = "img_url")
    private String imgUrl;

    private BigDecimal wallet;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<AddressesModel> addresses;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<OrderModel> Orders;

}
