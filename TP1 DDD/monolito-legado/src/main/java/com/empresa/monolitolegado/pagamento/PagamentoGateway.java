package com.empresa.monolitolegado.pagamento;

import java.math.BigDecimal;
import java.util.UUID;

public interface PagamentoGateway {
    void processarPagamento(UUID pedidoId, BigDecimal quantia, String moeda);
}