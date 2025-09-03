package com.BraianZ.ComidaNaMesa.Order;

import com.BraianZ.ComidaNaMesa.Delivery.DeliveryService;
import com.BraianZ.ComidaNaMesa.Product.ProductService;
import com.BraianZ.ComidaNaMesa.platform.WalletModel;
import com.BraianZ.ComidaNaMesa.platform.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final WalletService walletService;
    private final ProductService productService;
    private final DeliveryService deliveryService;

    public OrderResponseDTO create(OrderRequestDTO orderRequestDTO){
        OrderModel orderModel = orderMapper.forOrderModel(orderRequestDTO);
        orderModel.setStatus("PENDING");
        BigDecimal totalPriceProducts = productService
                .calcTotalPrice(orderModel.getProducts());
        orderModel.setTaxPlataform(walletService
                .calcTaxPlataform(totalPriceProducts));
        orderModel.setDelivery(deliveryService.setDeliveryMan());
        orderModel.setDeliveryTime(deliveryService.setDeliveryTime());
        orderModel.setTotalPrice(calcTotalOrderPrice(orderModel));
        if (!distributionMoney(orderModel)){
            throw new RuntimeException("Insufficient funds in customer's wallet.");
        }
        return orderMapper.forOrderResponseDTO(orderModel);
    }

    public BigDecimal calcTotalOrderPrice(OrderModel orderModel){
        BigDecimal totalPrice = productService
                .calcTotalPrice(orderModel.getProducts());
        return totalPrice.add(orderModel.getDelivery().getDeliveryFee());
    }

    private boolean distributionMoney(OrderModel orderModel){
        BigDecimal totalPrice = orderModel.getTotalPrice();
        BigDecimal taxPlataform = orderModel.getTaxPlataform();
        BigDecimal deliveryFee = orderModel.getDelivery().getDeliveryFee();

        BigDecimal currentDeliveryWallet = orderModel.getDelivery().getWallet();
        BigDecimal newDeliveryWallet = currentDeliveryWallet.add(deliveryFee);

        BigDecimal currentStoreWallet = orderModel.getStore().getWallet();
        BigDecimal storeEarnings = totalPrice.subtract(taxPlataform).subtract(deliveryFee);
        BigDecimal newStoreWallet = currentStoreWallet.add(storeEarnings);

        BigDecimal currentPlatformWallet = walletService.sendWallet().getTotal();
        BigDecimal newPlatformWallet = currentPlatformWallet.add(taxPlataform);


        BigDecimal currentCustomerWallet = orderModel.getCustomer().getWallet();
        if (currentCustomerWallet.compareTo(totalPrice) < 0) {
            return false; // Insufficient funds
        }

        BigDecimal newCustomerWallet = currentCustomerWallet.subtract(totalPrice);
        orderModel.getCustomer().setWallet(newCustomerWallet);
        orderModel.getDelivery().setWallet(newDeliveryWallet);
        orderModel.getStore().setWallet(newStoreWallet);
        walletService.sendWallet().setTotal(newPlatformWallet);
        return true;
    }
}
