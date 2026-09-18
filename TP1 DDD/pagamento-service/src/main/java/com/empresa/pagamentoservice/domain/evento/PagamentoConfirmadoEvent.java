package com.empresa.pagamentoservice.domain.evento;

import java.time.Instant;
import java.util.UUID;

public class PagamentoConfirmadoEvent implements DomainEvent {
    private final UUID pagamentoId;
    private final UUID pedidoId;
    private final Instant ocorreuEm;

    public PagamentoConfirmadoEvent(UUID pagamentoId, UUID pedidoId) {
        this.pagamentoId = pagamentoId;
        this.pedidoId = pedidoId;
        this.ocorreuEm = Instant.now();
    }

    @Override
    public Instant ocorreuEm() {
        return ocorreuEm;
    }

    public UUID pagamentoId() {
        return pagamentoId;
    }

    public UUID pedidoId() {
        return pedidoId;
    }
}
