package io.github.gabznavas.picpay.service;

import io.github.gabznavas.picpay.controller.dto.TransfeDto;
import io.github.gabznavas.picpay.entity.Transfer;
import io.github.gabznavas.picpay.entity.Wallet;
import io.github.gabznavas.picpay.exception.*;
import io.github.gabznavas.picpay.repository.TransferRepository;
import io.github.gabznavas.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private WalletRepository walletRepository;


    @Autowired
    private AuthorizationService authorizationService;


    @Autowired
    private NotificationService notificationService;


    @Transactional
    public Transfer transfer(@Valid TransfeDto transferDto) {
        Wallet sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));
        Wallet receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        Transfer transfer = new Transfer(sender, receiver, transferDto.value());

        validateTransfer(transfer, sender);

        transfer.getSender().debit(transferDto.value());
        transfer.getReceiver().credit(transferDto.value());

        walletRepository.save(transfer.getReceiver());
        walletRepository.save(transfer.getSender());

        transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transfer));

        return transfer;
    }

    private void validateTransfer(@Valid Transfer transfer, Wallet sender) {
        if (transfer.getReceiver().getId().equals(transfer.getSender().getId())) {
            throw new TransferNotAllowedToSelfException();
        }

        if (!sender.isTransferAllowerdForWalletType()) {
            throw new TransaferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalancerEqualOrGreaterThan(transfer.getValue())) {
            throw new InsufficientBalanceException();

        }

        if (!authorizationService.isAuthorized(transfer)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
