package com.empresa.pagamentoservice.domain.evento;

import java.time.Instant;

public interface DomainEvent {
    Instant ocorreuEm();
}