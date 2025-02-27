package io.github.gabznavas.picpay.service;

import io.github.gabznavas.picpay.controller.dto.CreateWalletDto;
import io.github.gabznavas.picpay.entity.Wallet;
import io.github.gabznavas.picpay.execption.WalletDataAlreadyExistsException;
import io.github.gabznavas.picpay.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createWallet(CreateWalletDto dto) {
        final boolean existsByEmail = walletRepository.existsByEmail(dto.email());
        if (existsByEmail) {
            throw new WalletDataAlreadyExistsException("Email already exists");
        }
        final boolean existsByCpfCnpj = walletRepository.existsByCpfCnpj(dto.cpfCnpj());
        if (existsByCpfCnpj) {
            throw new WalletDataAlreadyExistsException("CPF or CNPJ already exists");
        }
        return walletRepository.save(dto.toWallet());
    }
}
