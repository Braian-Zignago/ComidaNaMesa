package com.BraianZ.ComidaNaMesa.platform;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRespository walletRespository;
    private final WalletMapper walletMapper;

    public WalletResponseDTO create(WalletRequestDTO walletRequestDTO){
        WalletModel walletModel = walletMapper.forWalletModel(walletRequestDTO);
        WalletResponseDTO walletResponseDTO = null;
        try {
            walletResponseDTO = walletMapper.forWalletResponseDTO(walletRespository.save(walletModel));
        } catch (Exception e) {
            throw new RuntimeException("Error saving wallet: " + e.getMessage());
        }
        return walletResponseDTO;
    }

    public WalletResponseDTO getById(Long id){
        WalletModel walletModel = walletRespository.findById(id).orElse(null);
        if (walletModel == null) {
            return null;
        }
        return walletMapper.forWalletResponseDTO(walletModel);
    }

    public WalletResponseDTO deleteById(Long id){
        WalletResponseDTO walletResponseDTO = getById(id);
        if (walletResponseDTO == null) {
            return null;
        }
        walletRespository.deleteById(id);
        return walletResponseDTO;
    }

    public BigDecimal calcTaxPlataform(BigDecimal totalPriceProducts){
        BigDecimal taxRate = new BigDecimal("0.10"); // 10% tax
        return totalPriceProducts.multiply(taxRate);
    }

    public WalletModel sendWallet(){
        return walletRespository.findAll().get(0);
    }

}
