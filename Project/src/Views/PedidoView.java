package Views;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

import Modelos.Carrinho;
import Modelos.Produto;

public class PedidoView {

    Scanner scan = new Scanner(System.in);
    ClienteView cliente;
    Carrinho carrinho = new Carrinho();

    //Cardapio
    List<Produto> cardapio = Arrays.asList(
        new Produto(1, "Pizza", 34.99),
        new Produto(2, "Acai", 12.00),
        new Produto(3, "Hamburguer", 15.50),
        new Produto(4, "Refrigerante", 7.59)
    );

    //Construtor para receber a inicialização em 'ClienteView'
    public PedidoView(ClienteView clienteV){
        this.cliente = clienteV;
    }

    public void ExibirMenu()
    {
        System.out.println("\n--- Menu ---");
        for(Produto p : cardapio)
        {
            System.out.println(p.getNome() + " : R$ " + p.getPreco());
        }
        
        Escolha();
    }

    
    public void Escolha()
    {

        System.out.println("\nOpções:");
        System.out.println("1 - Adicionar item");
        System.out.println("2 - Remover item");
        System.out.println("3 - Ver carrinho");
        System.out.println("0 - Voltar");

        System.out.print("Ação -> ");
        int option = Integer.parseInt(scan.nextLine());
        
        switch(option)
        {
            case 1: AdicionarItem(); break;
            case 2: RemoverItem(); break;
            case 3: MostrarCarrinho(); break;
            case 0: cliente.Acoes();
            default: { //TODO colocar um while englobando o conteudo da função e tirar essa recursão
                System.out.println("!! Opção inválida !!");
                Escolha();
            }
        }
    }

    public void AdicionarItem()
    {
        System.out.print("Digite o codigo do item para Adicionar (0 para encerrar) -> ");
        int codigoItem = Integer.parseInt(scan.nextLine());
        
        if(codigoItem == 0) Escolha();

        
        System.out.print("Quantidade -> ");
        int quantidade = Integer.parseInt(scan.nextLine());
        
        Produto item = Busca(codigoItem);
        if(item != null){
            carrinho.Adicionar(item, quantidade);
        }
        else{ //TODO colocar um while englobando o 'if' e tirar essa recursão
            System.out.println("! Item invalido !");
            AdicionarItem();
        }
        
    }

    public void RemoverItem()
    {
        System.out.print("Digite o codigo do item para Remover (0 para encerrar) -> ");
        int codigoItem = Integer.parseInt(scan.nextLine());
        
        if(codigoItem == 0) Escolha();

        System.out.print("Quantidade (0 para tirar do carrinho) -> ");
        int quantidade = Integer.parseInt(scan.nextLine());

        Produto item = Busca(codigoItem);
        if(item != null){
            if(quantidade <= 0){
                carrinho.Remover(item);
            }
            else if(quantidade > 0){
                carrinho.RemoverQuantidade(item, quantidade);
            }
            else{
                System.out.println("Algo deu errado!");
            }
        }
        else{ //TODO colocar um while englobando o 'if' e tirar essa recursão
            System.out.println("! Item invalido !");
            RemoverItem();
        }
    }

    public void MostrarCarrinho()
    {
        carrinho.Mostrar();
        Escolha();
    }

    
    public Produto Busca(int codigo)
    {
        for(Produto p : cardapio)
        {
            if(p.getId().equals(codigo))
            {
                return p;
            }
        }

        return null;
    }
}