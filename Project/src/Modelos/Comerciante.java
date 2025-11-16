package Modelos;

import java.util.List;
import java.util.ArrayList;

public class Comerciante {

    List<Produto> cardapio = new ArrayList<>();
    List<Pedido> pedidos = new ArrayList<>();
    List<Pedido> pedidosAprovados = new ArrayList<>();

    public List<Produto> getCardapio(){
        return cardapio;
    }

    public void AdicionarPedido(Pedido pedido){
        pedidos.add(pedido);
        System.out.println("\nNovo pedido adicionado com sucesso !");
    }

    public void RemoverPedido(Pedido pedido){
        pedidos.remove(pedido);
    }

    public List<Pedido> getPedidosPendentes(){
        List<Pedido> pedidosPendentes = new ArrayList<>();
        for(Pedido p : pedidos){
            if(!p.Concluido()) pedidosPendentes.add(p);
        }

        return pedidosPendentes;
    }

    public List<Pedido> getPedidosAprovados(){
        return pedidosAprovados;
    }

    public Pedido BuscaPedido(int id){
        for(Pedido p : pedidos){
            if(p.getId() == id) return p;
        }
        return null;
    }

    public void AprovarPedido(int id){
        Pedido p = BuscaPedido(id);
        if(p != null){
            p.MarcarComoConcluido();
            pedidosAprovados.add(p);
            RemoverPedido(p);
            System.out.println("! Pedido #" + id + " marcado como concluido");
        }else{
            System.out.println("\n!! Nenhum pedido foi encontrado !!\n");
        }
    }

    public void RecusarPedido(int id){
        Pedido p = BuscaPedido(id);
        if(p != null){
            RemoverPedido(p);
            System.out.println("! Pedido #" + id + " recusado");
        }else{
            System.out.println("\n!! Nenhum pedido foi encontrado !!\n");
        }
    }

    public void AdicionarProduto(Produto produto){
        cardapio.add(produto);
        System.out.println("Item adicionado com sucesso");
        System.out.println(produto.getId() + "# | " + produto.getNome() + "\n");

        ListarCardapio();
    }

    public void RemoverProduto(int id){
        Produto ProdutoRemovido = Busca(id);
        if(ProdutoRemovido != null){
            cardapio.remove(ProdutoRemovido);
            System.out.println("Item removido com sucesso!");
            System.out.println(id + "# | " + ProdutoRemovido.getNome() + "\n");

            ListarCardapio();
        }else{
            System.out.println("Produto não encontrado");
        }
    }

    public Produto Busca(int id){
        for(Produto p : cardapio){
            if(p.getId() == id) return p;
        }
        return null;
    }

    public void ListarCardapio(){
        if(cardapio.isEmpty()){
            System.out.println("\n!! Cardápio indisponível !!");
        }else{
            System.out.println("---------- Menu -----------");
            for(Produto p : cardapio){
                System.out.print(p.getId() + "# ");
                System.out.print(p.getNome());
                int spaces = 13 - p.getNome().length();
                System.out.print(" ".repeat(spaces) + " : R$ ");
                System.out.printf("%.2f\n", p.getPreco());
            }
        }
    }
}
