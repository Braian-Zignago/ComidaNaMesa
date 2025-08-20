package com.BraianZ.ComidaNaMesa.platform;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_plataform")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private BigDecimal total;
}
