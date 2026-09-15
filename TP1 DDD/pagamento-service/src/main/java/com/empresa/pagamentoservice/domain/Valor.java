package com.empresa.pagamentoservice.domain;

import java.math.BigDecimal;

public record Valor(BigDecimal quantia, String moeda) {
    public Valor {
        if (quantia.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Valor deve ser positivo"
            );
        }
    }
}