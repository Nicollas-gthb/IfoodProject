# 🚚🍔 Projeto: Sistema de Simulação de App de Delivery em Console 
Um projeto acadêmico desenvolvido em **Java puro + PostgreSQL**, simulando o fluxo real de um sistema de delivery, com operações distintas para **Cliente**, **Comerciante** e **Entregador**.


###
📌 Visão Geral do Projeto 

Este sistema foi desenvolvido com o objetivo de **simular um fluxo completo de delivery**, desde o cadastro do cliente até a entrega do pedido.  
Ele contempla três perfis distintos:

#

 👤 Cliente  
- Criar conta  
- Visualizar cardápio  
- Adicionar/Remover itens no carrinho  
- Finalizar pedido (endereço + pagamento)  
- Acompanhar status da entrega  

 🏪 Comerciante  
- Gerenciar cardápio (Adicionar / Remover / Atualizar produtos)  
- Aprovar ou recusar pedidos pendentes  

 🏍️ Entregador  
- Cadastrar veículo  
- Aceitar/Recusar pedidos aprovados  
- Marcar pedido como **entregue**  

Toda a persistência dos dados é feita através do **PostgreSQL**, por meio de DAOs e conexões manuais utilizando JDBC.


#
📥 Como Clonar o Repositório

No terminal:

```bash
git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git
```
Depois entre na pasta do projeto:

```
cd SEU_REPOSITORIO
```
⚙️ Pré-requisitos
Antes de executar o projeto, certifique-se de ter instalado:

🧩 Java
JDK 17+

Para verificar no terminal:

```
java -version
```
#
🐘 PostgreSQL

Versão recomendada: PostgreSQL 14+

Criar um usuário com permissão para criar tabelas

🧰 IntelliJ IDEA (recomendado)
Você pode usar outras IDEs, mas o projeto foi estruturado com o IntelliJ, que facilita muito a execução.
#

🐘 Configurando o Banco de Dados

O projeto utiliza o banco:

```
app_delivery_db
```
1️⃣ Criar o banco no PostgreSQL

Abra o pgAdmin → Query Tool → execute:

```
CREATE DATABASE app_delivery_db;
```
2️⃣ Executar o schema.sql

No pgAdmin → selecione o banco app_delivery_db → Query Tool:

Ja no terminal do Query Tool clique para escolher arquivo e vá pelo caminho descrito abaixo e selecione o arquivo schema.sql:

```
src/main/resources/sql/schema.sql
```


E execute.

Isso criará:

✔ tabelas

✔ sequências

✔ chaves primárias

✔ chaves estrangeiras

✔ constraints

Sem dados iniciais (o sistema criará conforme você usar).

3️⃣ Ajuste da conexão no arquivo Java

O arquivo responsável pela conexão está em:

```
src/main/java/conexao/ConnectionFactory.java
```
Certifique-se de que as credenciais estão corretas:

```
private static final String URL = "jdbc:postgresql://localhost:5432/app_delivery_db";
private static final String USER = "postgres";
private static final String PASSWORD = "SUA_SENHA_AQUI";
```
#

📝 IMPORTANTE:

Troque SUA_SENHA_AQUI pela senha real do seu PostgreSQL.
#

▶️ Como Executar o Projeto

Após configurar o banco e abrir o projeto no IntelliJ:

1️⃣ Abra o IntelliJ

File → Open → selecione a pasta do projeto.

2️⃣ Espere o IntelliJ baixar dependências (Maven)

Isso acontece automaticamente.

3️⃣ Execute a classe principal

Normalmente está em:

```
src/main/java/Main.java
```
Clique em Run ▶️.

🧪 Testando o Funcionamento

Ao iniciar o programa você poderá:

👤 Criar um cliente

🛒 Acessar o cardápio

🧺 Montar um carrinho

💳 Finalizar pedidos

🏪 Entrar como comerciante e gerenciar pedidos

🏍️ Entrar como entregador e aceitar entregas

O fluxo completo pode ser testado pelo terminal.

#

Esperamos que o projeto atenda aos requisitos propostos!
###




![Logo](https://www.vectorlogo.zone/logos/java/java-ar21~bgwhite.svg)   
 
![Logo](https://www.vectorlogo.zone/logos/postgresql/postgresql-ar21~bgwhite.svg)
#

