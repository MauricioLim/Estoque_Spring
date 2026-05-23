# 📦 Estoque_Spring

API RESTful para controle de estoque de produtos, desenvolvida em **Java 17 com Spring Boot 3**. Oferece gerenciamento de itens, rastreamento de movimentações e autenticação segura via JWT.

---

## 🚀 Tecnologias

| Tecnologia | Uso |
|---|---|
| Java 17 | Linguagem principal |
| Spring Boot 3 | Framework da API REST |
| Spring Security | Autenticação e autorização |
| JWT | Tokens stateless de acesso |
| Spring Data JPA | Persistência e acesso ao banco |
| Jakarta Validation | Validação de campos nos DTOs |
| Multipart Upload | Envio de imagens junto com formulários |

---

## 📁 Estrutura do Projeto

```
src/main/java/com/estoque/
├── config/
├── controller/
│   ├── AuthController.java
│   ├── ItemController.java
│   └── MovimentacoesController.java
├── dto/
│   ├── request/
│   │   ├── ItemRequestDto.java
│   │   ├── LoginRequest.java
│   │   └── RegisterUserRequest.java
│   ├── response/
│   │   ├── ItemDto.java
│   │   ├── LoginResponse.java
│   │   └── RegisterUserResponse.java
│   └── ItemUptadeDto.java
└── entity/
    ├── ItemModel.java
    ├── MovimentacaoModel.java
    └── UserModel.java
```

---

## 🗄️ Entidades

### `ItemModel` → tabela `items`

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | Long | PK gerada automaticamente |
| `nome` | String | Nome do produto |
| `quantidade` | int | Quantidade em estoque |
| `valor` | double | Valor unitário |
| `imagem` | String | Caminho/URL da imagem |

### `MovimentacaoModel` → tabela `movimentacoes`

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | Long | PK gerada automaticamente |
| `quantidade` | int | Quantidade movimentada |
| `tipo` | TipoMovimentacao | `ENTRADA` ou `SAIDA` (enum) |
| `item` | ItemModel | Referência ao item (`@ManyToOne`) |
| `data` | LocalDate | Data da movimentação |

### `UserModel` → tabela de usuários

Implementa `UserDetails` (Spring Security) com os campos `id`, `name`, `email` e `password`.

---

## 🔐 Autenticação

A API utiliza **JWT stateless**. Endpoints protegidos exigem o header:

```
Authorization: Bearer <token>
```

### Fluxo de Login

```
POST /auth/login
    → valida credenciais via AuthenticationManager
    → gera JWT via TokenConfig
    → retorna { token }
```

### Fluxo de Registro

```
POST /auth/register
    → codifica senha com BCrypt (PasswordEncoder)
    → persiste usuário via UserRepository
    → retorna { name, email }
```

---

## 📡 Endpoints

### 🔓 Auth — `/auth` (público)

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/auth/login` | Autentica usuário e retorna JWT |
| `POST` | `/auth/register` | Registra novo usuário |

**`POST /auth/login`**
```json
// Request
{ "email": "user@email.com", "password": "senha123" }

// Response 200
{ "token": "eyJ..." }
```

**`POST /auth/register`**
```json
// Request
{ "name": "João Silva", "email": "joao@email.com", "password": "senha123" }

// Response 201
{ "name": "João Silva", "email": "joao@email.com" }
```

---

### 🔒 Itens — `/itens` (requer JWT)

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/itens/cadastro` | Cadastra novo item com imagem |
| `GET` | `/itens/listagem` | Lista todos os itens |
| `GET` | `/itens/buscaid?id={id}` | Busca item por ID |
| `PUT` | `/itens/{id}` | Atualiza um item existente |
| `DELETE` | `/itens/deletar/{id}` | Remove um item |

> **Nota:** Os endpoints `POST /itens/cadastro` e `PUT /itens/{id}` utilizam `Content-Type: multipart/form-data` para suporte ao upload de imagem.

**`POST /itens/cadastro`** — `multipart/form-data`
```
nome        = "Caneta Azul"    (obrigatório)
quantidade  = 100              (mínimo: 0)
valor       = 2.50             (mínimo: 0)
imagem      = [arquivo]        (opcional)
```

**`PUT /itens/{id}`** — `multipart/form-data`
```
nome        = "Caneta Vermelha"  (opcional)
quantidade  = 80                 (opcional, mínimo: 0)
valor       = 3.00               (opcional, mínimo: 0)
imagem      = [arquivo]          (opcional)
```

---

### 🔒 Movimentações — `/movimentacoes` (requer JWT)

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/movimentacoes` | Lista todas as movimentações |
| `POST` | `/movimentacoes/{itemId}` | Registra movimentação para um item |
| `GET` | `/movimentacoes/item/{itemId}` | Lista movimentações de um item |

**`POST /movimentacoes/{itemId}`**
```json
// Request body
{
  "quantidade": 20,
  "tipo": "ENTRADA",
  "data": "2026-05-21"
}
```

---

## 📦 DTOs

### Request

| Classe | Descrição |
|---|---|
| `ItemRequestDto` | Cadastro de item (multipart) |
| `LoginRequest` | Credenciais de login |
| `RegisterUserRequest` | Dados para registro de usuário |
| `ItemUptadeDto` | Atualização parcial de item (multipart) |

### Response

| Classe | Descrição |
|---|---|
| `ItemDto` | Dados do item retornados pela API |
| `LoginResponse` | Token JWT pós-login |
| `RegisterUserResponse` | Nome e e-mail do usuário registrado |

---

## ▶️ Como executar

```bash
# Clone o repositório
git clone https://github.com/MauricioLim/Estoque_Spring.git
cd Estoque_Spring

# Execute com Maven
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 📝 Licença

Este projeto está sob a licença MIT.
