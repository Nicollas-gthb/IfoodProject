package Views;

import Modelos.Comerciante;
import Modelos.Carrinho;
import Modelos.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class PedidoView {

    Scanner scan = new Scanner(System.in);
    PagamentoView pagamento = new PagamentoView();
    Carrinho carrinho = new Carrinho();
    private Comerciante comerciante;
    private List<Produto> cardapio;

    public PedidoView(Comerciante c){
        this.comerciante = c;
        this.cardapio = new ArrayList<>(comerciante.getCardapio());
    }

    ComercianteView cv = new ComercianteView(this);

    public void ExibirMenu()
    {
        comerciante.ListarCardapio();
        Escolha();
    }

    public void Escolha()
    {
        int option;
        escolhaLoop: while(true){
            System.out.println("\n= Opções:");
            System.out.println("1 - Adicionar item");
            System.out.println("2 - Remover item");
            System.out.println("3 - Ver carrinho");
            System.out.println("4 - Confirmar pedido");
            System.out.println("0 - Voltar");
    
            try{
                System.out.print("= Ação -> ");
                option = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada invalida !!\n");
                continue;
            }
            
            switch(option)
            {
                case 1: AdicionarItem(); break;
                case 2: RemoverItem(); break;
                case 3: MostrarCarrinho(); break;
                case 4: {
                    /*if(pagamento.Finalizar()){
                        GerarDemanda();
                    }*/
                    System.out.println("Manutenção");
                }
                break;
                case 0: {
                    break escolhaLoop;
                }
                default: {
                    System.out.println("!! Opção inválida !!");
                }
            }
        }
    }

    public void AdicionarItem()
    {
        int codigoItem;
        int quantidade;
        while(true){
            try{
                System.out.print("= Digite o codigo do item para Adicionar (0 para encerrar) -> ");
                codigoItem = Integer.parseInt(scan.nextLine());

                if(codigoItem == 0) return;

                System.out.print("Quantidade -> ");
                quantidade = Integer.parseInt(scan.nextLine());

                Produto item = comerciante.Busca(codigoItem);
                if(item != null){
                    carrinho.Adicionar(item, quantidade);
                }
                else{
                    System.out.println("! Item invalido !");
                }
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada inválida, digite novamente.. !!\n");
            }
        }
    }

    public void RemoverItem()
    {
        while(true){

            try{
                System.out.print("Digite o codigo do item para Remover (0 para encerrar) -> ");
                int codigoItem = Integer.parseInt(scan.nextLine());
                
                if(codigoItem == 0) return;
        
                System.out.print("Quantidade (0 para tirar do carrinho) -> ");
                int quantidade = Integer.parseInt(scan.nextLine());
        
                Produto item = comerciante.Busca(codigoItem);
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
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada inválida, digite novamente.. !!\n");
            }
        }
    }

    public void MostrarCarrinho()
    {
        carrinho.Mostrar();
    }

    /*public Map<Produto, Integer> GerarDemanda()
    {
        return carrinho.getCarrinho();
    }*/
}