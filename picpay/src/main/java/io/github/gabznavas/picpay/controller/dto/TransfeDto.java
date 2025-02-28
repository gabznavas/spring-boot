package io.github.gabznavas.picpay.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransfeDto(
        @DecimalMin("0.1")
        BigDecimal value,
        @NotNull
        Long payer,
        @NotNull
        Long payee
) {
}
