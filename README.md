
📦 Projeto: Sistema de Simulação de App de Delivery em Console
------
📝 1. Introdução
Este projeto simula o fluxo de negócios de um aplicativo de delivery, com foco na interação entre três perfis principais: Cliente, Comerciante e Entregador.

O sistema foi desenvolvido em Java e utiliza o PostgreSQL como SGBD para persistência de dados. A interação com o banco de dados é gerenciada por classes DAO (Data Access Object), que garantem a separação de responsabilidades e a segurança nas operações JDBC.

Funcionalidades Chave:
Cliente: Cadastrar, ver cardápio, adicionar/remover itens do carrinho, finalizar pedido (com endereço e método de pagamento) e visualizar status.

Comerciante: Gerenciar pedidos pendentes (Aprovar/Recusar), e gerenciar o cardápio (Adicionar/Remover/Atualizar produtos).

Entregador: Cadastrar veículo e aceitar/recusar pedidos aprovados, marcando-os como entregues.

🛠️ 2. Pré-requisitos
Para rodar o projeto, você precisará dos seguintes softwares instalados e configurados em sua máquina:

☕Java Development Kit (JDK): Versão 17 ou superior.

🐘PostgreSQL: Servidor de banco de dados rodando na porta padrão (5432).

Driver JDBC do PostgreSQL: O arquivo .jar do driver deve ser adicionado ao classpath do projeto. (Se estiver usando uma IDE moderna como IntelliJ ou VS Code, geralmente basta adicionar o .jar na pasta lib/ e configurar o Build Path, (tópico 4))

⚙️ 3. Configuração do Banco de Dados (Passos para o Avaliador)
⚠️ ATENÇÃO: Este é o passo mais importante para que a aplicação consiga se conectar e funcionar corretamente.

A. Ajuste de Credenciais (Primeiro Passo)
Edite a classe DAO/ConexaoDB.java e ajuste os valores de USER e PASSWORD para as credenciais do seu PostgreSQL local. (Exemplo de código em Java com os campos a serem alterados)

B. Criação do Banco de Dados
Certifique-se de que o servidor PostgreSQL está rodando.

No pgAdmin, crie um novo banco de dados com o nome exato: app_delivery_db.

C. Execução do Script SQL (Criação de Tabelas e Dados)
Para que o banco de dados funcione, você deve executar o script que criará todas as tabelas e dados iniciais.

Localize o Arquivo: Encontre o arquivo sql/schema_e_dados.sql dentro da pasta do projeto.

Abra o pgAdmin: Conecte-se ao seu servidor.

Abra a Query Tool:

Clique com o botão direito no banco de dados app_delivery_db que você acabou de criar.

Selecione Query Tool.

Execute o Script:

Na barra de ferramentas da Query Tool, clique no ícone de "Open File" (Geralmente um ícone de pasta).

Navegue até a pasta do projeto e selecione o arquivo sql/schema_e_dados.sql.

O conteúdo completo do script (comandos DROP, CREATE e INSERT) será carregado na janela da Query Tool.

Clique no botão "Execute/Play" (Geralmente um ícone de seta verde) para rodar todos os comandos.

Verificação: O painel de Messages (Mensagens) deve exibir que os comandos foram executados com sucesso (ex: "Query returned successfully in ... ms").

Após executar o script, o banco de dados app_delivery_db estará pronto, populado e a aplicação Java conseguirá se conectar e funcionar corretamente.

▶️ 4. Como Rodar o Projeto
Clone ou Descompacte:

Clone o repositório do GitHub ou descompacte o arquivo .zip para uma pasta local.

Abrir na IDE:

Abra o projeto na sua IDE (IntelliJ IDEA (recomendado), VS Code, etc.).

Configurar o Driver JDBC:

Certifique-se de que o driver JDBC do PostgreSQL (o arquivo .jar) está no Build Path ou na pasta de dependências do projeto (pasta 'lib').
Pelo IntelliJ, clique com botão direito na pasta Project > Open Module Settings > Dependencies, clique no icone de '+' e va até a pasta 'lib' IfoodProject > Project > lib > selecione o arquivo .jar, Apply e depois Ok.

Executar:

Execute a classe principal: Main.java.

O programa deve iniciar com uma mensagem de teste de conexão bem-sucedida e apresentar o menu de seleção de perfil.

🔑 5. Perfis e Dados Iniciais
Ao iniciar o programa, o Main.java chamará ConexaoDB.testarConexao().

Para testar as funcionalidades, comece pelos perfis Cliente ou Entregador para realizar o cadastro inicial. O perfil Comerciante acessa diretamente as funcionalidades do sistema.

Esperamos que o projeto atenda aos requisitos propostos!



![Logo](https://brandlogovector.com/wp-content/uploads/2020/07/Java-Logo.png)
![Logo](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsC9Zl9jYsLYXA9lhxDCiJD0Y_PQakXzpzMA&s)
