package io.github.gabznavas.picpay.controller;

import io.github.gabznavas.picpay.controller.dto.TransfeDto;
import io.github.gabznavas.picpay.entity.Transfer;
import io.github.gabznavas.picpay.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransfeDto dto) {
        Transfer transfer = transferService.transfer(dto);
        return ResponseEntity.ok(transfer);
    }
}
