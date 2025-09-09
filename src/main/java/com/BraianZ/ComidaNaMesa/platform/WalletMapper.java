package com.BraianZ.ComidaNaMesa.platform;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    @Mapping(target = "id", ignore = true)
    WalletModel forWalletModel(WalletRequestDTO request);

    WalletResponseDTO forWalletResponseDTO(WalletModel walletModel);
}
