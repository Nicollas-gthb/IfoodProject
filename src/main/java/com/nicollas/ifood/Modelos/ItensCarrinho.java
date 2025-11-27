package com.nicollas.ifood.Modelos;

public class ItensCarrinho {
    private int id;
    private Produto produto;
    private int quantidade;

    public ItensCarrinho(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItensCarrinho(int id, Produto produto, int quantidade){
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Produto getProduto() {
        return produto;
    }
    public int getQuantidade() {
        return quantidade;
    }
}
