package com.BraianZ.ComidaNaMesa.Order;

import com.BraianZ.ComidaNaMesa.Customer.CustomerMapper;
import com.BraianZ.ComidaNaMesa.Customer.CustomerModel;
import com.BraianZ.ComidaNaMesa.Customer.CustomerService;
import com.BraianZ.ComidaNaMesa.Delivery.DeliveryService;
import com.BraianZ.ComidaNaMesa.Product.ProductMapper;
import com.BraianZ.ComidaNaMesa.Product.ProductModel;
import com.BraianZ.ComidaNaMesa.Product.ProductService;
import com.BraianZ.ComidaNaMesa.Store.StoreMapper;
import com.BraianZ.ComidaNaMesa.Store.StoreModel;
import com.BraianZ.ComidaNaMesa.Store.StoreService;
import com.BraianZ.ComidaNaMesa.platform.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final WalletService walletService;
    private final ProductService productService;
    private final DeliveryService deliveryService;
    private final ProductMapper productMapper;
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;
    private final StoreMapper storeMapper;
    private final StoreService storeService;

    public OrderResponseDTO create(OrderRequestDTO orderRequestDTO){
        OrderModel saveModel = orderMapper.forOrderModel(orderRequestDTO);
        saveModel.setStatus("PENDING");
        OrderModel orderModel = orderRepository.save(saveModel);

        orderModel.setProducts(addProducts(orderModel));
        orderModel.setCustomer(addCustomer(orderModel));
        orderModel.setStore(addStore(orderModel));

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
        return orderMapper.forOrderResponseDTO(orderRepository.save(orderModel));
    }

    private StoreModel addStore(OrderModel orderModel) {
        StoreModel storeReturned = storeMapper.forStoreModel(storeService.getById(orderModel.getStore().getId()));
        return storeReturned;
    }

    private CustomerModel addCustomer(OrderModel orderModel) {
        CustomerModel customerReturned = customerMapper.forCustumerModel(customerService.getById(orderModel.getCustomer().getId()));
        return customerReturned;
    }


    private List<ProductModel> addProducts(OrderModel orderModel) {
        List<Long> productIds = new ArrayList<Long>();
        List<ProductModel> products = orderModel.getProducts();
        List<ProductModel> productsReturned = new ArrayList<ProductModel>();
        for (ProductModel product : products) {
            System.out.printf("ID: %d", product.getId());
            productsReturned.add(productMapper.forProductModel(productService.getById(product.getId())));
        }
        return productsReturned;
    }

    public List<OrderResponseDTO> getAll(){
        List<OrderModel> orderModel = orderRepository.findAll();
        return orderMapper.forOrderResponseDTOList(orderModel);
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

        BigDecimal currentDeliveryWallet = Optional.ofNullable(orderModel.getDelivery().getWallet())
                .orElse(BigDecimal.ZERO);
        BigDecimal newDeliveryWallet = currentDeliveryWallet.add(deliveryFee);

        BigDecimal currentStoreWallet = Optional.ofNullable(orderModel.getStore().getWallet())
                .orElse(BigDecimal.ZERO);
        BigDecimal storeEarnings = totalPrice.subtract(taxPlataform).subtract(deliveryFee);
        BigDecimal newStoreWallet = currentStoreWallet.add(storeEarnings);

        BigDecimal currentPlatformWallet = Optional.ofNullable( walletService.getWallet().getTotal())
                .orElse(BigDecimal.ZERO);
        BigDecimal newPlatformWallet = currentPlatformWallet.add(taxPlataform);


        BigDecimal currentCustomerWallet = Optional.ofNullable(orderModel.getCustomer().getWallet())
                .orElse(BigDecimal.ZERO);
        if (currentCustomerWallet.compareTo(totalPrice) < 0) {
            return false; // Insufficient funds
        }

        BigDecimal newCustomerWallet = currentCustomerWallet.subtract(totalPrice);
        customerService.sendWallet(orderModel.getCustomer(), newCustomerWallet);
        storeService.sendWallet(orderModel.getStore(), newStoreWallet);
        orderModel.getDelivery().setWallet(newDeliveryWallet);
        walletService.sendWallet(newPlatformWallet);
        return true;
    }
}
