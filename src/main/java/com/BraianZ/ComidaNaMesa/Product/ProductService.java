package com.BraianZ.ComidaNaMesa.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductUpdate productUpdate;

    public ProductResponseDTO register(ProductRequestDTO productRequestDTO) {
        ProductModel productModel = productMapper.forProductModel(productRequestDTO);
        ProductResponseDTO productResponseDTO = null;
        try {
            productResponseDTO = productMapper.forProductResponseDTO(productRepository.save(productModel));
        } catch (Exception e) {
            throw new RuntimeException("Error saving product: " + e.getMessage());
        }
        return productResponseDTO;
    }

    public ProductResponseDTO getById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::forProductResponseDTO)
                .orElse(null);
    }

    public List<ProductResponseDTO> getAll(){
        List<ProductModel> productModelList = productRepository.findAll();
        return productMapper.forProductResponseDTOList(productModelList);
    }

    public ProductResponseDTO deleteById(Long id) {
        ProductResponseDTO productResponseDTO = getById(id);
        if (productResponseDTO == null) {
            return null;
        }
        productRepository.deleteById(id);
        return productResponseDTO;
    }

    public ProductResponseDTO updateById(ProductRequestDTO productRequestDTO, Long id) {
        ProductModel productModel = productRepository.findById(id).orElse(null);
        if (productModel == null) {
            return null;
        }
        productUpdate.updateProduct(productRequestDTO, productModel);
        return productMapper.forProductResponseDTO(productRepository.save(productModel));
    }

    public BigDecimal calcTotalPrice(List<ProductModel> products) {
        if (products == null || products.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return products.stream()
                .filter(Objects::nonNull) // 1. Filtra objetos ProductModel nulos
                .map(ProductModel::getPrice)
                .filter(Objects::nonNull) // 2. Filtra precios nulos
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
