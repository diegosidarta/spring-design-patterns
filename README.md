# 🚀 Spring Boot - Design Patterns (GoF)

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-blue)](https://swagger.io/)

API REST desenvolvida em Java com Spring Boot, focada na exploração e implementação prática de **Padrões de Projeto (Design Patterns - GoF)**.

Este projeto teve origem em um laboratório da DIO (Digital Innovation One) no Bootcamp Santander 2026, mas foi **evoluído e refatorado** para incluir práticas de mercado, como validações Fail-Fast, tratamento de exceções semânticas (HTTP 400 e 404) e documentação interativa com OpenAPI.

##  Padrões de Projeto Aplicados

Durante o desenvolvimento, o ecossistema do Spring foi utilizado para aplicar os seguintes padrões:
- **Singleton:** Garantido pelo próprio container de injeção de dependências do Spring (`@Autowired`), assegurando uma única instância global dos componentes.
- **Strategy:** Aplicado na interface `ClienteService`, permitindo múltiplas implementações de regras de negócio sem alterar o domínio.
- **Facade:** Aplicado na classe `ClienteServiceImpl`, criando uma "fachada" que abstrai a complexidade das integrações (Banco de Dados + API externa ViaCEP).

##  Tecnologias e Ferramentas

- **Java 21**
- **Spring Boot** (Web, Data JPA)
- **Spring Cloud OpenFeign** (Integração com a API ViaCEP)
- **H2 Database** (Banco de dados em memória para testes ágeis)
- **Springdoc OpenAPI / Swagger UI** (Documentação interativa da API)
- **Maven** (Gerenciamento de dependências)

##  Evoluções Implementadas (Diferencial)

Além do escopo original, a arquitetura foi aprimorada com as seguintes implementações:
1. **Separação de Responsabilidades:** Criação do pacote `repository`, isolando a camada de persistência.
2. **Defesa de Domínio (Fail-Fast):** Implementação de validações na Service para barrar a inserção de clientes sem Nome ou CEP (Retorno: `400 Bad Request`).
3. **Tratamento de Exceções:** Uso de `.orElseThrow()` com `ResponseStatusException` para evitar erros internos (500) ao buscar ou deletar IDs inexistentes (Retorno: `404 Not Found`).
4. **Correção de Duplicidade:** Correção de um bug silencioso no método `PUT`, forçando o ID da URL na entidade antes do `save()` do JPA.

##  Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone [https://github.com/diegosidarta/spring-design-patterns.git](https://github.com/diegosidarta/spring-design-patterns.git)