--
-- PostgreSQL database dump
--

\restrict WPGQ8Yd60GfuhkaXbaC8I6OB0T6ilB1R92fYVaAQ19YgSk3uldfSsNTjRxcrM6u

-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.6

-- Started on 2025-11-18 09:57:25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 24607)
-- Name: cliente; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.cliente (
    id integer NOT NULL,
    nome character varying(30) NOT NULL,
    email character varying(50) NOT NULL,
    senha character varying(30) NOT NULL,
    telefone character varying(30),
    data_nasc character varying(30),
    id_endereco_principal integer
);


--
-- TOC entry 219 (class 1259 OID 24606)
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.cliente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4971 (class 0 OID 0)
-- Dependencies: 219
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- TOC entry 222 (class 1259 OID 24616)
-- Name: endereco; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.endereco (
    id integer NOT NULL,
    rua character varying(30) NOT NULL,
    numero character varying(30) NOT NULL,
    cidade character varying(30) NOT NULL,
    bairro character varying(30) NOT NULL,
    cep character varying(30),
    complemento character varying(30)
);


--
-- TOC entry 221 (class 1259 OID 24615)
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.endereco_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4972 (class 0 OID 0)
-- Dependencies: 221
-- Name: endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.endereco_id_seq OWNED BY public.endereco.id;


--
-- TOC entry 226 (class 1259 OID 24632)
-- Name: entregador; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.entregador (
    id integer NOT NULL,
    nome character varying(30) NOT NULL,
    telefone character varying(30),
    data_nasc character varying(30),
    email character varying(30) NOT NULL,
    senha character varying(30) NOT NULL,
    id_veiculo integer
);


--
-- TOC entry 225 (class 1259 OID 24631)
-- Name: entregador_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.entregador_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4973 (class 0 OID 0)
-- Dependencies: 225
-- Name: entregador_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.entregador_id_seq OWNED BY public.entregador.id;


--
-- TOC entry 230 (class 1259 OID 24665)
-- Name: itens_pedido; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.itens_pedido (
    id integer NOT NULL,
    id_pedido integer NOT NULL,
    id_produto integer NOT NULL,
    quantidade integer NOT NULL,
    preco_unitario_historico numeric(10,2) NOT NULL
);


--
-- TOC entry 229 (class 1259 OID 24664)
-- Name: itens_pedido_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.itens_pedido_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4974 (class 0 OID 0)
-- Dependencies: 229
-- Name: itens_pedido_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.itens_pedido_id_seq OWNED BY public.itens_pedido.id;


--
-- TOC entry 228 (class 1259 OID 24648)
-- Name: pedido; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.pedido (
    id integer NOT NULL,
    id_cliente integer NOT NULL,
    id_endereco integer NOT NULL,
    status character varying(30) NOT NULL,
    valor_total numeric(10,2) NOT NULL,
    forma_pagamento character varying(30)
);


--
-- TOC entry 227 (class 1259 OID 24647)
-- Name: pedido_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.pedido_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4975 (class 0 OID 0)
-- Dependencies: 227
-- Name: pedido_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.pedido_id_seq OWNED BY public.pedido.id;


--
-- TOC entry 218 (class 1259 OID 24600)
-- Name: produto; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.produto (
    id integer NOT NULL,
    nome character varying(30) NOT NULL,
    preco numeric(10,2) NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 24599)
-- Name: produto_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.produto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4976 (class 0 OID 0)
-- Dependencies: 217
-- Name: produto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.produto_id_seq OWNED BY public.produto.id;


--
-- TOC entry 224 (class 1259 OID 24623)
-- Name: veiculo; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.veiculo (
    id integer NOT NULL,
    placa character varying(30) NOT NULL,
    ano character varying(30),
    modelo character varying(30)
);


--
-- TOC entry 223 (class 1259 OID 24622)
-- Name: veiculo_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.veiculo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 4977 (class 0 OID 0)
-- Dependencies: 223
-- Name: veiculo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.veiculo_id_seq OWNED BY public.veiculo.id;


--
-- TOC entry 4773 (class 2604 OID 24610)
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- TOC entry 4774 (class 2604 OID 24619)
-- Name: endereco id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.endereco ALTER COLUMN id SET DEFAULT nextval('public.endereco_id_seq'::regclass);


--
-- TOC entry 4776 (class 2604 OID 24635)
-- Name: entregador id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.entregador ALTER COLUMN id SET DEFAULT nextval('public.entregador_id_seq'::regclass);


--
-- TOC entry 4778 (class 2604 OID 24668)
-- Name: itens_pedido id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.itens_pedido ALTER COLUMN id SET DEFAULT nextval('public.itens_pedido_id_seq'::regclass);


--
-- TOC entry 4777 (class 2604 OID 24651)
-- Name: pedido id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.pedido ALTER COLUMN id SET DEFAULT nextval('public.pedido_id_seq'::regclass);


--
-- TOC entry 4772 (class 2604 OID 24603)
-- Name: produto id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.produto ALTER COLUMN id SET DEFAULT nextval('public.produto_id_seq'::regclass);


--
-- TOC entry 4775 (class 2604 OID 24626)
-- Name: veiculo id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.veiculo ALTER COLUMN id SET DEFAULT nextval('public.veiculo_id_seq'::regclass);


--
-- TOC entry 4955 (class 0 OID 24607)
-- Dependencies: 220
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.cliente VALUES (1, 'Nicollas', 'nicollas.melo@a.ucb.br', 'nickplays3', '38999709966', '19/01/2006', NULL);
INSERT INTO public.cliente VALUES (2, 'Nicollas', 'micollas.melo@', 'nick', '38', '19/01', NULL);
INSERT INTO public.cliente VALUES (3, 'Nick', 'nicollas.melo', 'nicollas2025', '38999', '19/01', 1);
INSERT INTO public.cliente VALUES (5, 'n', 'n', 'n', 'n', 'n', 3);


--
-- TOC entry 4957 (class 0 OID 24616)
-- Dependencies: 222
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.endereco VALUES (1, '1', '6', 'SHSN', 'SHSN', '72000', 'nenhum');
INSERT INTO public.endereco VALUES (2, '1', '6', 'SHSN', 'SHSN', '72000', 'nenhum');
INSERT INTO public.endereco VALUES (3, 'n', 'n', 'n', 'n', 'n', 'n');
INSERT INTO public.endereco VALUES (4, 'n', 'n', 'n', 'n', 'n', 'n');


--
-- TOC entry 4961 (class 0 OID 24632)
-- Dependencies: 226
-- Data for Name: entregador; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.entregador VALUES (1, 'e', 'e', 'e', 'e', 'e', 1);


--
-- TOC entry 4965 (class 0 OID 24665)
-- Dependencies: 230
-- Data for Name: itens_pedido; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.itens_pedido VALUES (1, 1, 1, 2, 19.90);
INSERT INTO public.itens_pedido VALUES (2, 1, 2, 4, 25.49);
INSERT INTO public.itens_pedido VALUES (3, 2, 1, 1, 19.90);
INSERT INTO public.itens_pedido VALUES (4, 2, 2, 5, 25.49);


--
-- TOC entry 4963 (class 0 OID 24648)
-- Dependencies: 228
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.pedido VALUES (1, 3, 2, 'Pendente', 141.76, 'Pix');
INSERT INTO public.pedido VALUES (2, 5, 4, 'Entregue', 147.35, 'Pix');


--
-- TOC entry 4953 (class 0 OID 24600)
-- Dependencies: 218
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.produto VALUES (1, 'Hamburguer Clássico', 19.90);
INSERT INTO public.produto VALUES (2, 'Pizza Frango', 25.49);


--
-- TOC entry 4959 (class 0 OID 24623)
-- Dependencies: 224
-- Data for Name: veiculo; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.veiculo VALUES (1, 'e', 'e', 'e');


--
-- TOC entry 4978 (class 0 OID 0)
-- Dependencies: 219
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.cliente_id_seq', 5, true);


--
-- TOC entry 4979 (class 0 OID 0)
-- Dependencies: 221
-- Name: endereco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.endereco_id_seq', 4, true);


--
-- TOC entry 4980 (class 0 OID 0)
-- Dependencies: 225
-- Name: entregador_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.entregador_id_seq', 1, true);


--
-- TOC entry 4981 (class 0 OID 0)
-- Dependencies: 229
-- Name: itens_pedido_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.itens_pedido_id_seq', 4, true);


--
-- TOC entry 4982 (class 0 OID 0)
-- Dependencies: 227
-- Name: pedido_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.pedido_id_seq', 2, true);


--
-- TOC entry 4983 (class 0 OID 0)
-- Dependencies: 217
-- Name: produto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.produto_id_seq', 2, true);


--
-- TOC entry 4984 (class 0 OID 0)
-- Dependencies: 223
-- Name: veiculo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.veiculo_id_seq', 1, true);


-- Completed on 2025-11-18 09:57:26

--
-- PostgreSQL database dump complete
--

\unrestrict WPGQ8Yd60GfuhkaXbaC8I6OB0T6ilB1R92fYVaAQ19YgSk3uldfSsNTjRxcrM6u

