package com.empresa.monolitolegado.pagamento;


import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.web.client.RestClient;

public class PagamentoGatewayHttp implements PagamentoGateway {

    private final RestClient restClient;

    public PagamentoGatewayHttp(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void processarPagamento(
            UUID pedidoId, BigDecimal quantia, String moeda
    ) {
        restClient.post()
                .uri("http://pagamento-service/pagamentos")
                .body(new PagamentoRequest(pedidoId, quantia, moeda))
                .retrieve()
                .toBodilessEntity();
    }
}