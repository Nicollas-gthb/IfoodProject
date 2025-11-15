package Modelos;

import java.util.List;

public class Pedido {

    private int id;
    private List<ItensCarrinho> itensCarrinho;
    private double valorTotal;
    private String nomeCliente;
    private String formaPagamento;
    private boolean concluido = false;

    public Pedido(int id, List<ItensCarrinho> itens,  double valorTotal, String nomeCliente, String formaPagamento) {
        this.id = id;
        this.itensCarrinho = itens;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
        this.formaPagamento = formaPagamento;
    }

    public int getId() {return id;}
    public List<ItensCarrinho> getItensCarrinho() {return itensCarrinho;}
    public double getValorTotal() {return valorTotal;}
    public String getNomeCliente() {return nomeCliente;}
    public String getFormaPagamento() {return formaPagamento;}

    public boolean Concluido() {return concluido;}

    public void MarcarComoConcluido() {this.concluido = true;}
}
