package Modelos;

import java.util.Map;
import java.util.HashMap;

public class Carrinho {
    
    private Map<Produto, Integer> carrinho = new HashMap<>();
    
    public void Adicionar(Produto pdt, int qtd)
    {
        if(carrinho.containsKey(pdt))
        {
            int quantidadeAtual = carrinho.get(pdt);
            quantidadeAtual += qtd;
            carrinho.put(pdt, quantidadeAtual);
            System.out.println("Adicionado!");
        }
        else
        {
            carrinho.put(pdt, qtd);
        }
    }

    public void Remover(Produto pdt)
    {
        if(carrinho.containsKey(pdt)){
            carrinho.remove(pdt);
            System.out.println("Removido!");
        }
        else{
            System.out.println("Item não está no carrinho!");
        }
    }

    public void RemoverQuantidade(Produto pdt, int qtd)
    {
        if(carrinho.containsKey(pdt)){
            int qtdAtual = carrinho.get(pdt);
            qtdAtual -= qtd;

            if(qtdAtual <= 0){
                carrinho.remove(pdt);
                System.out.println("Removida todas as quantidades!");
            }
            else{
                carrinho.put(pdt, qtdAtual);
                System.out.println("Quantidade Removida!");
            }
        }
        else{
            System.out.println("Item não está no carrinho!");
        }
        
    }

    public void Mostrar()
    {
        if(carrinho.isEmpty()){
            System.out.println("\n! Carrinho está Vazio !\n");
            return;
        }

        double precoTotal = 0;

        System.out.println("Itens no carrinho:\n");
        System.out.println("-- #Id --|- Produto -|-- Qtd --|-- Valor --");
        System.out.println("-".repeat(40)); 

        for (Map.Entry<Produto, Integer> item : carrinho.entrySet()) {

            Produto produto = item.getKey();
            int quantidade = item.getValue();
            
            double precoCalculado = produto.getPreco() * quantidade;
            System.out.println("#" + produto.getId() + " | " + produto.getNome() + " | " + quantidade + " | " + precoCalculado);

            precoTotal += precoCalculado;
        }

        System.out.println("-".repeat(40));    
        System.out.println("Total do carrinho: " + precoTotal);
        System.out.println("-".repeat(40));        
    }
}
