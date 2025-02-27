package io.github.gabznavas.picpay.config;

import io.github.gabznavas.picpay.entity.WalletType;
import io.github.gabznavas.picpay.repository.WalletTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {


    @Autowired
    private WalletTypeRepository walletTypeRepository;

    @Override
    public void run(String... args) {
        Arrays.stream(WalletType.Enum.values())
                .forEach(walletTypeEnum -> {
                    WalletType walletType = walletTypeEnum.get();
                    if (!walletTypeRepository.existsByDescription(walletType.getDescription())) {
                        walletType.setId(null);
                        walletTypeRepository.save(walletType);
                    }
                });
    }
}
