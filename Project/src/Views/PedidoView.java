package Views;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

import Modelos.Carrinho;
import Modelos.Produto;

public class PedidoView {

    Scanner scan = new Scanner(System.in);
    PagamentoView pagamento = new PagamentoView();
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
        System.out.println("\n-------- Menu --------");
        for(Produto p : cardapio)
        {
            System.out.print(p.getNome());
            int spaces = 13 - p.getNome().length();
            System.out.print(" ".repeat(spaces) + " : R$ ");
            System.out.printf("%.2f\n", p.getPreco());
        }
        Escolha();
    }

    public void Escolha()
    {
        while(true){
            System.out.println("\n= Opções:");
            System.out.println("1 - Adicionar item");
            System.out.println("2 - Remover item");
            System.out.println("3 - Ver carrinho");
            System.out.println("4 - Confirmar pedido");
            System.out.println("0 - Voltar");
    
            System.out.print("= Ação -> ");
            int option = Integer.parseInt(scan.nextLine());
            
            switch(option)
            {
                case 1: AdicionarItem(); break;
                case 2: RemoverItem(); break;
                case 3: MostrarCarrinho(); break;
                case 4: pagamento.Finalizar(); break;
                case 0: {
                    cliente.setVaiVoltar(false);
                    return;
                }
                default: {
                    System.out.println("!! Opção inválida !!");
                }
            }
        }
    }

    public void AdicionarItem()
    {
        while(true){
            System.out.print("= Digite o codigo do item para Adicionar (0 para encerrar) -> ");
            int codigoItem = Integer.parseInt(scan.nextLine());
            
            if(codigoItem == 0) return;
    
            System.out.print("Quantidade -> ");
            int quantidade = Integer.parseInt(scan.nextLine());
            
            Produto item = Busca(codigoItem);
            if(item != null){
                carrinho.Adicionar(item, quantidade);
            }
            else{
                System.out.println("! Item invalido !");
            }
        }
    }

    public void RemoverItem()
    {
        while(true){
            System.out.print("Digite o codigo do item para Remover (0 para encerrar) -> ");
            int codigoItem = Integer.parseInt(scan.nextLine());
            
            if(codigoItem == 0) return;
    
            System.out.print("Quantidade (0 para tirar do carrinho) -> ");
            int quantidade = Integer.parseInt(scan.nextLine());
    
            Produto item = Busca(codigoItem);
            if(item != null){
                if(quantidade <= 0){
                    carrinho.Remover(item);
                }
                else{
                    carrinho.RemoverQuantidade(item, quantidade);
                }
            }
            else{
                System.out.println("! Item invalido !");
            }
        }
    }

    public void MostrarCarrinho()
    {
        carrinho.Mostrar();
    }

    
    public Produto Busca(int codigo)
    {
        for(Produto p : cardapio){
            if(p.getId().equals(codigo)) return p;
        }

        return null;
    }
}