package com.nicollas.ifood.Modelos;

import java.util.Objects;

public class Produto {
    private Integer id;
    private String nome;
    private Double preco;

    public Produto(Integer id, String nome, Double preco)
    {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(String nome, Double preco)
    {
        this.nome = nome;
        this.preco = preco;
    }

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer i)
    {
        this.id = i;
    }

    public String getNome()
    {
        return nome;
    }
    public void setNome(String n)
    {
        this.nome = n;
    }

    public Double getPreco()
    {
        return preco;
    }
    public void setPreco(Double p)
    {
        this.preco = p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        // A unicidade é definida pelo ID
        return Objects.equals(id, produto.id); 
    }

    @Override
    public int hashCode() {
        // Gera o código hash baseado no ID
        return Objects.hash(id); 
    }
}