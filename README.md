# Sistema de Gerenciamento de Pedidos e Pagamentos de Fornecedores

## 📋 Descrição

Este projeto é um Sistema de Gerenciamento de Pedidos e Pagamentos de Fornecedores, no qual clientes podem realizar pedidos a fornecedores com base nos produtos que estes oferecem.

O produto pode estar:

- Com valor cheio
- Com desconto aplicado diretamente
- Com desconto via cupom promocional informado pelo consumidor no momento da compra

O sistema permite:

- Cadastro e autenticação de usuários (clientes e fornecedores)
- Cadastro e exclusão (soft delete) de produtos
- Cadastro de descontos e cupons
- Adição de crédito
- Realização de pedidos e aplicação de cupons
- Pagamentos
- Visualização do histórico de pedidos e transações

---

## 🛠️ Stack Tecnológica

- **Backend:** Java Spring Boot  
- **Frontend/View:** Java Server Pages (JSP) + HTML + CSS + JavaScript  
- **Banco de Dados:** PostgreSQL (com migrações via Flyway)  

---

## 🏛 Arquitetura

O projeto segue o padrão Clean Architecture com abordagem Domain-Driven Design (DDD), o que proporciona:

## Baixo acoplamento
Graças ao Princípio da Inversão de Dependência, o núcleo do sistema (domínio e aplicação) não depende de frameworks, bancos de dados ou tecnologias externas — apenas de interfaces. Isso permite trocar implementações sem alterar a lógica de negócio.  
**Exemplo:** É possível mudar o banco de dados de PostgreSQL para MySQL alterando apenas a camada de infra.

## Alta testabilidade
Como as regras de negócio não dependem de infraestrutura, é possível criar testes unitários isolados para o domínio sem precisar subir servidor ou banco.

## Facilidade de manutenção e evolução
A separação clara entre camadas e responsabilidades torna o sistema mais legível, reduzindo riscos ao fazer alterações ou implementar novas funcionalidades, tornando o sistema altamente manutenível.

## Foco no negócio
O DDD coloca as regras de negócio como parte central do código, garantindo que a lógica seja expressa no próprio modelo de domínio, e não espalhada em controladores ou serviços de infraestrutura.

## Independência tecnológica
O sistema não fica “preso” a uma tecnologia específica, já que as decisões técnicas (frameworks, bibliotecas, ferramentas, banco de dados) ficam nas bordas da aplicação.

A estrutura de pacotes é organizada da seguinte forma:

```plaintext
src/main/java
└── com.dunas.desafio
    ├── component
    │   ├── client
    │   │   ├── application
    │   │   │   ├── gateways
    │   │   │   ├── mappers
    │   │   │   └── usecases
    │   │   │       ├── impl
    │   │   │       ├── inputs
    │   │   │       └── outputs
    │   │   ├── domain
    │   │   │   └── models
    │   │   ├── infra
    │   │   │   ├── config
    │   │   │   └── persistence
    │   │   │       ├── entities
    │   │   │       ├── gateways
    │   │   │       ├── mappers
    │   │   │       └── repositories
    │   │   └── web
    │   │       ├── controllers
    │   │       ├── dtos
    │   │       └── mappers
    │   ├── order
    │   │   ├── application
    │   │   │   ├── gateways
    │   │   │   ├── mappers
    │   │   │   └── usecases
    │   │   │       ├── impl
    │   │   │       ├── inputs
    │   │   │       └── outputs
    │   │   ├── domain
    │   │   │   ├── models
    │   │   │   └── services
    │   │   ├── infra
    │   │   │   ├── config
    │   │   │   └── persistence
    │   │   │       ├── entities
    │   │   │       ├── gateways
    │   │   │       ├── mappers
    │   │   │       └── repositories
    │   │   └── web
    │   │       ├── controllers
    │   │       ├── dtos
    │   │       └── mappers
    │   ├── product
    │   │   ├── application
    │   │   │   ├── gateways
    │   │   │   ├── mappers
    │   │   │   └── usecases
    │   │   │       ├── impl
    │   │   │       ├── inputs
    │   │   │       └── outputs
    │   │   ├── domain
    │   │   │   └── models
    │   │   ├── infra
    │   │   │   ├── config
    │   │   │   └── persistence
    │   │   │       ├── entities
    │   │   │       ├── gateways
    │   │   │       ├── mappers
    │   │   │       └── repositories
    │   │   └── web
    │   │       ├── controllers
    │   │       ├── dtos
    │   │       └── mappers
    │   ├── supplier
    │   │   ├── application
    │   │   │   ├── gateways
    │   │   │   ├── mappers
    │   │   │   └── usecases
    │   │   │       ├── impl
    │   │   │       ├── inputs
    │   │   │       └── outputs
    │   │   ├── domain
    │   │   │   ├── models
    │   │   │   └── services
    │   │   ├── infra
    │   │   │   ├── config
    │   │   │   └── persistence
    │   │   │       ├── entities
    │   │   │       ├── gateways
    │   │   │       ├── mappers
    │   │   │       └── repositories
    │   │   └── web
    │   │       ├── controllers
    │   │       ├── dtos
    │   │       └── mappers
    │   └── user
    │       ├── application
    │       │   ├── gateways
    │       │   ├── mappers
    │       │   └── usecases
    │       │       ├── impl
    │       │       ├── inputs
    │       │       └── outputs
    │       ├── domain
    │       │   └── models
    │       ├── infra
    │       │   ├── config
    │       │   └── persistence
    │       │       ├── entities
    │       │       ├── gateways
    │       │       ├── mappers
    │       │       └── repositories
    │       └── web
    │           ├── controllers
    │           ├── dtos
    │           └── mappers
    └── shared
        ├── audit
        ├── config
        ├── exception
        │   └── handler
        ├── response
        └── security
src/main/java
└── resources
    ├── db/migration # Scripts Flyway (funções, procedures, triggers, validações)
    └── application.yml
```

## Shared
Recursos compartilhados por todo o sistema:

- Configuração de segurança (Spring Security)
- Filtros
- Configuração CORS
- Auditoria
- Exceções globais
- Responses padronizadas

## Componentes
Cada componente é dividido em quatro camadas principais:

### Domain
- Entidades de domínio
- Regras de negócio
- Serviços de domínio

### Application
- Casos de uso
- Mappers para conversão entre objetos de aplicação e modelo de domínio
- Interfaces (gateways) que a aplicação utiliza para interagir com o mundo externo

### Infra
- Implementações dos gateways da aplicação
- Configuração de beans
- Entidades persistentes (JPA)
- Repositórios
- Mappers para conversão entre modelo de domínio e modelo de banco de dados

### Web
- Controladores para páginas JSP
- DTOs
- Mappers para conversão entre DTOs e objetos de aplicação (inputs e outputs)
  
---

## 📂 Frontend

Desenvolvido em JSP, com HTML, CSS e JavaScript.

Integração direta com controladores Spring MVC.

---

## 🗄️ Banco de Dados

- PostgreSQL  
- Migrações e versionamento de schema feitos com Flyway  
- Scripts SQL incluem:  
  - Funções  
  - Procedures  
  - Triggers  
  - Validações de integridade  

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

- Java 21+
- Maven 3+
- PostgreSQL 9.3+

### Passos

```bash
# 1. Clonar o repositório
git clone https://github.com/Samuelrcf/desafio-dunnas.git

# 2. Criar o banco de dados no PostgreSQL (via pgAdmin ou terminal)
createdb seubanco

# 3. Configurar banco de dados no application.yml
 datasource:
  url: jdbc:postgresql://localhost:5432/seubanco
  username: usuario
  password: senha

# 4. Inicializar a aplicação
mvn spring-boot:run

```

## 👥 Usuários Pré-Criados para Testes

O sistema já contém alguns usuários pré-cadastrados no banco (via migração), para facilitar testes:

| Tipo       | Usuário     | Senha     |
|------------|-------------|-----------|
| Cliente    | joao123     | 12345678  |
| Cliente    | maria123    | 12345678  |
| Fornecedor | fornecedor1 | 12345678  |
| Fornecedor | fornecedor2 | 12345678  |
