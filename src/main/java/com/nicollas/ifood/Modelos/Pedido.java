package com.nicollas.ifood.Modelos;

import java.util.List;
import java.util.Objects;

public class Pedido {

    private int id;
    private List<ItensCarrinho> itensCarrinho;
    private double valorTotal;
    private String nomeCliente;
    private String formaPagamento;
    private boolean concluido = false;
    private boolean entregue = false;
    private Endereco endereco;

    private String status;

    public Pedido(List<ItensCarrinho> itens,  double valorTotal, String nomeCliente, String formaPagamento, Endereco endereco) {
        this.itensCarrinho = itens;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
        this.formaPagamento = formaPagamento;
        this.endereco = endereco;

        this.status = "Pendente";
    }

    public Pedido(int id, List<ItensCarrinho> itens, double valorTotal, String nomeCliente, String formaPagamento, Endereco endereco, String status) {
        this.id = id;
        this.itensCarrinho = itens;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
        this.formaPagamento = formaPagamento;
        this.endereco = endereco;
        this.status = status;
    }

    public void getEnderecoPedido(){
        this.endereco.getEndereco();
    }

    public Endereco getEndereco(){
        return this.endereco;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public List<ItensCarrinho> getItensCarrinho() {return itensCarrinho;}
    public double getValorTotal() {return valorTotal;}
    public String getNomeCliente() {return nomeCliente;}
    public String getFormaPagamento() {return formaPagamento;}


    public boolean Concluido() {return concluido;}
    public void MarcarComoConcluido() {this.concluido = true;}

    public boolean Entregue() {return entregue;}
    public void MarcarComoEntregue() {this.entregue = true;}

    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Verifica se o objeto é nulo ou de uma classe diferente
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        // Define que dois pedidos são iguais se tiverem o mesmo ID
        return id == pedido.id;
    }

    @Override
    public int hashCode() {
        // Gera o código hash baseado no ID
        return Objects.hash(id);
    }
}