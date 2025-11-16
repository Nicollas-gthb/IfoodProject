package Modelos;

public class ItensCarrinho {
    private Produto produto;
    private int quantidade;

    public ItensCarrinho(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }
    public int getQuantidade() {
        return quantidade;
    }
}
