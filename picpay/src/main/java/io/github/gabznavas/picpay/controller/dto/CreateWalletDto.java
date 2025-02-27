package io.github.gabznavas.picpay.controller.dto;

import io.github.gabznavas.picpay.entity.Wallet;
import io.github.gabznavas.picpay.entity.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletDto(
        @NotBlank
        String fullname,
        @NotBlank
        String cpfCnpj,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        WalletType.Enum walletType
) {

    public Wallet toWallet() {
        return new Wallet(
                fullname,
                cpfCnpj,
                email,
                password,
                walletType.get()
        );
    }

}
