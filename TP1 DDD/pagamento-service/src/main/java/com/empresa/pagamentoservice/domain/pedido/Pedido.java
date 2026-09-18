package com.empresa.pagamentoservice.domain.pedido;

import java.util.UUID;

public class Pedido {
    private final UUID id;
    private UUID pagamentoId; //referencia por ID, não pelo objeto Pagamento
    private StatusPedido status;

    public Pedido(UUID id) {
        this.id = id;
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
    }

    public void vincularPagamento(UUID pagamentoId) {
        this.pagamentoId = pagamentoId;
    }
}
