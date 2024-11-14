
# API Votos

API para gerenciar eleições, eleitores, candidatos, cargos e votos.

## Sumário

- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
- [Execução](#execução)
- [Ordem de Requisições](#ordem-de-requisições)
- [Testes no Postman](#testes-no-postman)
- [Documentação](#documentação)

---

## Pré-requisitos

- **Java 17** ou superior
- **Maven** (para gerenciamento de dependências)
- **PostgreSQL** (banco de dados)

## Instalação

1. **Clonar o repositório**:
   ```bash
   git clone <URL_DO_REPOSITORIO_GIT>
   cd api_votos
   ```

2. **Instalar dependências**:
   ```bash
   mvn clean install
   ```

## Configuração do Banco de Dados

1. Inicie o PostgreSQL e crie o banco de dados:
   ```sql
   CREATE DATABASE api_votos;
   ```

2. (Opcional) Crie um usuário específico para o projeto:
   ```sql
   CREATE USER api_votos_user WITH PASSWORD 'sua_senha';
   ALTER DATABASE api_votos OWNER TO api_votos_user;
   ```

3. Configure as credenciais no arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/api_votos
   spring.datasource.username=api_votos_user
   spring.datasource.password=sua_senha
   ```

## Execução

Para rodar o projeto, execute o seguinte comando no terminal:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`.

---

## Ordem de Requisições

Para evitar erros de relacionamento ao usar a API, siga esta ordem para as requisições POST:

1. **Cargo**:
   - Crie os cargos primeiro, pois os candidatos irão referenciar um cargo.
   ```json
   {
     "nome": "Presidente",
     "descricao": "Cargo de presidente da república"
   }
   ```

2. **Candidato**:
   - Crie os candidatos após os cargos, associando cada candidato a um cargo.
   ```json
   {
     "nome": "João da Silva",
     "partido": "Partido X",
     "cargo": { "id": 1 }
   }
   ```

3. **Eleição**:
   - Crie as eleições e associe os candidatos a elas.
   ```json
   {
     "titulo": "Eleições Municipais 2024",
     "descricao": "Eleições para prefeitos",
     "dataInicio": "2024-01-01",
     "dataFim": "2024-01-30",
     "candidatos": [ { "id": 1 } ]
   }
   ```

4. **Eleitor**:
   - Crie eleitores, pois eles irão votar em candidatos nas eleições.
   ```json
   {
     "nome": "Carlos Almeida",
     "cpf": "12345678901",
     "email": "carlos.almeida@example.com"
   }
   ```

5. **Voto**:
   - Registre os votos após os eleitores, candidatos e eleições estarem criados.
   ```json
   {
     "eleitor": { "id": 1 },
     "candidato": { "id": 1 },
     "eleicao": { "id": 1 }
   }
   ```

---

## Testes no Postman

1. Importe a documentação da API no Postman usando a URL do OpenAPI (Swagger):
   ```
   http://localhost:8080/api-docs.json
   ```

2. Configure a variável `{{base_url}}` como `http://localhost:8080` para facilitar o uso dos endpoints.

3. Execute as requisições seguindo a ordem definida acima para evitar erros de relacionamento.

---

## Documentação

A documentação completa da API é gerada automaticamente pelo Swagger e pode ser acessada em:
```
http://localhost:8080/docs
```

---
