package io.github.gabznavas.picpay.repository;

import io.github.gabznavas.picpay.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
    boolean existsByDescription(String description);
}
