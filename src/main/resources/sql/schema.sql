-- ===============================================
-- SCHEMA DO BANCO DO PROJETO: app_delivery_db
-- Tabelas: cliente, endereco, veiculo, entregador,
--          produto, pedido, itens_pedido
-- ===============================================

-- CRIE O BANCO ASSIM:
-- CREATE DATABASE app_delivery_db;

-- Depois, basta rodar todo este arquivo no banco.

-- ============================
-- SEQUENCES
-- ============================

CREATE SEQUENCE cliente_id_seq START 1;
CREATE SEQUENCE endereco_id_seq START 1;
CREATE SEQUENCE veiculo_id_seq START 1;
CREATE SEQUENCE entregador_id_seq START 1;
CREATE SEQUENCE produto_id_seq START 1;
CREATE SEQUENCE pedido_id_seq START 1;
CREATE SEQUENCE itens_pedido_id_seq START 1;

-- ============================
-- TABELAS
-- ============================

CREATE TABLE cliente (
    id INTEGER PRIMARY KEY DEFAULT nextval('cliente_id_seq'),
    nome VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(30) NOT NULL,
    telefone VARCHAR(30),
    data_nasc VARCHAR(30),
    id_endereco_principal INTEGER
);

CREATE TABLE endereco (
    id INTEGER PRIMARY KEY DEFAULT nextval('endereco_id_seq'),
    rua VARCHAR(30) NOT NULL,
    numero VARCHAR(30) NOT NULL,
    cidade VARCHAR(30) NOT NULL,
    bairro VARCHAR(30) NOT NULL,
    cep VARCHAR(30),
    complemento VARCHAR(30)
);

CREATE TABLE veiculo (
    id INTEGER PRIMARY KEY DEFAULT nextval('veiculo_id_seq'),
    placa VARCHAR(30) NOT NULL UNIQUE,
    ano VARCHAR(30),
    modelo VARCHAR(30)
);

CREATE TABLE entregador (
    id INTEGER PRIMARY KEY DEFAULT nextval('entregador_id_seq'),
    nome VARCHAR(30) NOT NULL,
    telefone VARCHAR(30),
    data_nasc VARCHAR(30),
    email VARCHAR(30) NOT NULL UNIQUE,
    senha VARCHAR(30) NOT NULL,
    id_veiculo INTEGER UNIQUE
);

CREATE TABLE produto (
    id INTEGER PRIMARY KEY DEFAULT nextval('produto_id_seq'),
    nome VARCHAR(30) NOT NULL,
    preco NUMERIC(10,2) NOT NULL
);

CREATE TABLE pedido (
    id INTEGER PRIMARY KEY DEFAULT nextval('pedido_id_seq'),
    id_cliente INTEGER NOT NULL,
    id_endereco INTEGER NOT NULL,
    status VARCHAR(30) NOT NULL,
    valor_total NUMERIC(10,2) NOT NULL,
    forma_pagamento VARCHAR(30)
);

CREATE TABLE itens_pedido (
    id INTEGER PRIMARY KEY DEFAULT nextval('itens_pedido_id_seq'),
    id_pedido INTEGER NOT NULL,
    id_produto INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    preco_unitario_historico NUMERIC(10,2) NOT NULL
);

-- ============================
-- FOREIGN KEYS
-- ============================

ALTER TABLE cliente
    ADD CONSTRAINT fk_cliente_endereco_principal
    FOREIGN KEY (id_endereco_principal)
    REFERENCES endereco (id)
    ON DELETE SET NULL;

ALTER TABLE entregador
    ADD CONSTRAINT fk_entregador_veiculo
    FOREIGN KEY (id_veiculo)
    REFERENCES veiculo (id);

ALTER TABLE pedido
    ADD CONSTRAINT fk_pedido_cliente
    FOREIGN KEY (id_cliente)
    REFERENCES cliente (id);

ALTER TABLE pedido
    ADD CONSTRAINT fk_pedido_endereco
    FOREIGN KEY (id_endereco)
    REFERENCES endereco (id);

ALTER TABLE itens_pedido
    ADD CONSTRAINT fk_itens_pedido_pedido
    FOREIGN KEY (id_pedido)
    REFERENCES pedido (id);

ALTER TABLE itens_pedido
    ADD CONSTRAINT fk_itens_pedido_produto
    FOREIGN KEY (id_produto)
    REFERENCES produto (id);