package com.BraianZ.ComidaNaMesa.Delivery;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRespository extends JpaRepository<DeliveryModel, Long> {

    List<DeliveryModel> findByDeliveryStatus(String status);
}
