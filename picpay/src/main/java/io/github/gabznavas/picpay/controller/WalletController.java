package io.github.gabznavas.picpay.controller;

import io.github.gabznavas.picpay.controller.dto.CreateWalletDto;
import io.github.gabznavas.picpay.entity.Wallet;
import io.github.gabznavas.picpay.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto) {
        final Wallet body = walletService.createWallet(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
