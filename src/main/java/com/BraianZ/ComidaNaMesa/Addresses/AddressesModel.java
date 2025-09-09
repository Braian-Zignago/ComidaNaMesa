package com.BraianZ.ComidaNaMesa.Addresses;

import com.BraianZ.ComidaNaMesa.Customer.CustomerModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private CustomerModel customer;
}
