package com.empresa.pagamentoservice.domain;

import java.util.UUID;

public class Pagamento {
    private final UUID id;
    private final UUID pedidoId;
    private Valor valor;
    private StatusPagamento status;

    public Pagamento(UUID id, UUID pedidoId, Valor valor) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.valor = valor;
        this.status = StatusPagamento.PENDENTE;
    }

    public void confirmar() {
        if (status != StatusPagamento.PENDENTE) {
            throw new IllegalStateException(
                    "Só é possível confirmar um "
                            + "pagamento pendente"
            );
        }
        this.status = StatusPagamento.CONFIRMADO;
    }
}
