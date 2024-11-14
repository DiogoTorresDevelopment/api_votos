API Votos
API para gerenciar eleições, eleitores, candidatos, cargos e votos.

Sumário
Pré-requisitos
Instalação
Configuração do Banco de Dados
Execução
Ordem de Requisições
Testes no Postman
Documentação
Pré-requisitos
Java 17 ou superior
Maven (para gerenciamento de dependências)
PostgreSQL (banco de dados)
Instalação
Clonar o repositório:

bash
Copiar código
git clone <URL_DO_REPOSITORIO_GIT>
cd api_votos
Instalar dependências:

bash
Copiar código
mvn clean install
Configuração do Banco de Dados
Inicie o PostgreSQL e crie o banco de dados:

sql
Copiar código
CREATE DATABASE api_votos;
(Opcional) Crie um usuário específico para o projeto:

sql
Copiar código
CREATE USER api_votos_user WITH PASSWORD 'sua_senha';
ALTER DATABASE api_votos OWNER TO api_votos_user;
Configure as credenciais no arquivo src/main/resources/application.properties:

properties
Copiar código
spring.datasource.url=jdbc:postgresql://localhost:5432/api_votos
spring.datasource.username=api_votos_user
spring.datasource.password=sua_senha
Execução
Para rodar o projeto, execute o seguinte comando no terminal:

bash
Copiar código
mvn spring-boot:run
A aplicação estará disponível em: http://localhost:8080.

Ordem de Requisições
Para evitar erros de relacionamento ao usar a API, siga esta ordem para as requisições POST:

Cargo:

Crie os cargos primeiro, pois os candidatos irão referenciar um cargo.
json
Copiar código
{
  "nome": "Presidente",
  "descricao": "Cargo de presidente da república"
}
Candidato:

Crie os candidatos após os cargos, associando cada candidato a um cargo.
json
Copiar código
{
  "nome": "João da Silva",
  "partido": "Partido X",
  "cargo": { "id": 1 }
}
Eleição:

Crie as eleições e associe os candidatos a elas.
json
Copiar código
{
  "titulo": "Eleições Municipais 2024",
  "descricao": "Eleições para prefeitos",
  "dataInicio": "2024-01-01",
  "dataFim": "2024-01-30",
  "candidatos": [ { "id": 1 } ]
}
Eleitor:

Crie eleitores, pois eles irão votar em candidatos nas eleições.
json
Copiar código
{
  "nome": "Carlos Almeida",
  "cpf": "12345678901",
  "email": "carlos.almeida@example.com"
}
Voto:

Registre os votos após os eleitores, candidatos e eleições estarem criados.
json
Copiar código
{
  "eleitor": { "id": 1 },
  "candidato": { "id": 1 },
  "eleicao": { "id": 1 }
}
Testes no Postman
Importe a documentação da API no Postman usando a URL do OpenAPI (Swagger):

bash
Copiar código
http://localhost:8080/api-docs.json
Configure a variável {{base_url}} como http://localhost:8080 para facilitar o uso dos endpoints.

Execute as requisições seguindo a ordem definida acima para evitar erros de relacionamento.

Documentação
A documentação completa da API é gerada automaticamente pelo Swagger e pode ser acessada em:

bash
Copiar código
http://localhost:8080/docs
Este README.md fornece todas as informações essenciais para instalar, configurar e utilizar a API api_votos, com um guia para as requisições e uma introdução sobre como realizar testes no Postman.
