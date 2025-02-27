package io.github.gabznavas.picpay.repository;

import io.github.gabznavas.picpay.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    boolean existsByEmail(String email);

    boolean existsByCpfCnpj(String email);
}
