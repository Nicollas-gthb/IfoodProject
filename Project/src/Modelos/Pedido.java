package Modelos;

import java.util.List;

public class Pedido {

    private int id;
    private List<Carrinho> itensCarrinho;
    private double valorTotal;
    private String status = "Pendente";
    private String nomeCliente;
    private String formaPagamento;

    public Pedido(int id, List<Carrinho> itens,  double valorTotal, String nomeCliente, String formaPagamento) {
        this.id = id;
        this.itensCarrinho = itens;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
        this.formaPagamento = formaPagamento;
    }

    public int getId() {return id;}
    public List<Carrinho> getItensCarrinho() {return itensCarrinho;}
    public double getValorTotal() {return valorTotal;}
    public String getNomeCliente() {return nomeCliente;}
    public String getFormaPagamento() {return formaPagamento;}
    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}
}
