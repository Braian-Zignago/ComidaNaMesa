package com.BraianZ.ComidaNaMesa.platform;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    WalletModel forWalletModel(WalletRequestDTO request);

    WalletResponseDTO forWalletResponseDTO(WalletModel walletModel);
}
