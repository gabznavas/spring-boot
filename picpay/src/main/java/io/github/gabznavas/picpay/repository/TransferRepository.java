package io.github.gabznavas.picpay.repository;


import io.github.gabznavas.picpay.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
