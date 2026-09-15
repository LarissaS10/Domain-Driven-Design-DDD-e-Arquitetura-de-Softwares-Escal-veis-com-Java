package com.empresa.monolitolegado.pagamento;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoRequest(UUID pedidoId, BigDecimal quantia, String moeda) {
}
