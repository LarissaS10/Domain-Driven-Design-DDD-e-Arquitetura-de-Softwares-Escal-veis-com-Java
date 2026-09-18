# TP1 - DDD e Arquitetura de Softwares Escaláveis

Repositório com a estrutura inicial da extração do contexto de **Pagamento** de um sistema monolítico de e-commerce para um microsserviço, aplicando princípios de DDD e o padrão de migração **Strangler Fig**.

## Estrutura do repositório

- **`pagamento-service/`** — novo microsserviço extraído do monólito. Contém o Aggregate Root `Pagamento` e o Value Object `Valor`, seguindo os princípios de DDD.
- **`monolito-legado/`** — representa o sistema monolítico legado (simulado). Contém a interface `PagamentoGateway` e sua implementação `PagamentoGatewayHttp`, responsáveis por comunicar o monólito com o novo microsserviço de forma desacoplada.

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

- **Aggregate Root** (`Pagamento`): protege as invariantes do domínio através de comportamento (`confirmar()`), em vez de expor setters genéricos.
- **Value Object** (`Valor`): imutável, validado no próprio construtor.
- **Bounded Context**: o pacote `com.empresa.pagamentoservice` isola a linguagem e as regras do contexto de Pagamento.
- **Interface de integração / Anti-Corruption Layer** (`PagamentoGateway`): garante baixo acoplamento entre o monólito legado e o novo microsserviço.
