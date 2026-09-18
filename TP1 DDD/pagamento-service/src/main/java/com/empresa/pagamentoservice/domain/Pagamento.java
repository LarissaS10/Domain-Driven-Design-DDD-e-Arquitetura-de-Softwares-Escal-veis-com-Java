package com.empresa.pagamentoservice.domain;

import com.empresa.pagamentoservice.domain.evento.DomainEvent;
import com.empresa.pagamentoservice.domain.evento.PagamentoConfirmadoEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pagamento {
    private final UUID id;
    private final UUID pedidoId;
    private Valor valor;
    private StatusPagamento status;
    private final List<DomainEvent> eventos = new ArrayList<>();

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
        eventos.add(new PagamentoConfirmadoEvent(this.id, this.pedidoId));
    }

    public List<DomainEvent> eventos() {
        return List.copyOf(eventos);
    }
}