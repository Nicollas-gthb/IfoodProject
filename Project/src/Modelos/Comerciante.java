package Modelos;

import java.util.List;
import java.util.ArrayList;

public class Comerciante {

    List<Produto> cardapio = new ArrayList<>();

    public void AdicionarProduto(Produto produto){
        cardapio.add(produto);
        System.out.println("Item adicionado com sucesso");
        System.out.println(produto.getId() + " | " + produto.getNome() + "\n\n");

        ListarCardapio();
    }

    public void RemoverProduto(int id){
        Produto ProdutoRemovido = Busca(id);
        if(ProdutoRemovido != null){
            cardapio.remove(ProdutoRemovido);
            System.out.println("Item removido com sucesso!");
            System.out.println(id + " | " + ProdutoRemovido.getNome() + "\n\n");

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
            System.out.println("\n-------- Menu --------");
            for(Produto p : cardapio){
                System.out.print(p.getId() + "# ");
                System.out.print(p.getNome());
                int spaces = 13 - p.getNome().length();
                System.out.print(" ".repeat(spaces) + " : R$ ");
                System.out.printf("%.2f\n", p.getPreco());
            }
        }
    }

    public List<Produto> getCardapio(){
        return cardapio;
    }
}
