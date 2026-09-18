# TP1 e TP2 - DDD e Arquitetura de Softwares Escaláveis

Repositório com a evolução de um sistema monolítico de e-commerce para uma arquitetura orientada a DDD, cobrindo dois trabalhos:

- **TP1**: extração do contexto de **Pagamento** para um microsserviço, aplicando o padrão de migração **Strangler Fig**.
- **TP2**: introdução de um segundo Aggregate (`Pedido`) e comunicação entre agregados via **eventos de domínio**, eliminando o acoplamento síncrono entre eles.

## Estrutura do repositório

- **`pagamento-service/`** — microsserviço com todo o domínio de Pagamento e Pedido:
  - `domain/` — `Pagamento` (Aggregate Root) e `Valor` (Value Object), do TP1.
  - `domain/pedido/` — `Pedido` (Aggregate Root) e `StatusPedido`, do TP2. Referencia `Pagamento` apenas pelo ID.
  - `domain/evento/` — `DomainEvent` (abstração) e `PagamentoConfirmadoEvent` (evento de domínio concreto), do TP2.
- **`monolito-legado/`** — representa o sistema monolítico legado (simulado), do TP1. Contém a interface `PagamentoGateway` e sua implementação `PagamentoGatewayHttp`, responsáveis por comunicar o monólito com o novo microsserviço de forma desacoplada.

## Pré-requisitos

- **`pagamento-service`**: JDK 21, Maven
- **`monolito-legado`**: JDK 17, Gradle Wrapper já incluso (não precisa instalar nada)
- IntelliJ IDEA (recomendado, mas não obrigatório)

## Como baixar e rodar

### 1. Clonar o repositório

```bash
git clone https://github.com/LarissaS10/Domain-Driven-Design-DDD-e-Arquitetura-de-Softwares-Escal-veis-com-Java.git
cd Domain-Driven-Design-DDD-e-Arquitetura-de-Softwares-Escal-veis-com-Java
```

### 2. Rodar o `pagamento-service`

```bash
cd pagamento-service
mvn spring-boot:run
```

Ou, pelo IntelliJ: abra a pasta `pagamento-service` como projeto e rode a classe `PagamentoServiceApplication`.

### 3. Rodar o `monolito-legado`

```bash
cd monolito-legado
./gradlew bootRun
```

Ou, pelo IntelliJ: abra a pasta `monolito-legado` como projeto e rode a classe `MonolitoLegadoApplication`.

> **Observação:** os dois projetos usam a porta 8080 por padrão. Para rodar os dois ao mesmo tempo, pare um antes de rodar o outro, ou altere a porta de um deles em `application.properties` (`server.port=8081`).

## Conceitos de DDD aplicados

### TP1
- **Aggregate Root** (`Pagamento`): protege as invariantes do domínio através de comportamento (`confirmar()`), em vez de expor setters genéricos.
- **Value Object** (`Valor`): imutável, validado no próprio construtor.
- **Bounded Context**: o pacote `com.empresa.pagamentoservice` isola a linguagem e as regras do contexto de Pagamento.
- **Interface de integração / Anti-Corruption Layer** (`PagamentoGateway`): garante baixo acoplamento entre o monólito legado e o novo microsserviço.

### TP2
- **Segundo Aggregate** (`Pedido`): referencia `Pagamento` apenas pelo ID (`pagamentoId`), nunca pelo objeto inteiro, mantendo os dois agregados independentes.
- **Evento de domínio** (`PagamentoConfirmadoEvent`): representa, de forma imutável, o fato já ocorrido de um pagamento ter sido confirmado.
- **Abstração de evento** (`DomainEvent`): contrato comum que todo evento de domínio do projeto implementa.
- **Registro de eventos no Agregado**: o método `Pagamento.confirmar()` valida a invariante (só confirma se estiver `PENDENTE`), muda o estado e registra o evento numa lista interna, eliminando a necessidade de chamar diretamente o serviço de outro agregado.
- **Arquitetura de publicação de eventos**: padrão Outbox (grava pagamento + evento na mesma transação) combinado com um tópico Kafka, permitindo múltiplos consumidores independentes reagirem ao mesmo evento.
