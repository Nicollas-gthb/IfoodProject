package Views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Modelos.Carrinho;
import Modelos.Comerciante;
import Modelos.Pedido;
import Modelos.Produto;

public class ComercianteView {

    Scanner scan = new Scanner(System.in);
    private Comerciante comerciante;
    private PedidoView pedido;
    private Map<Produto, Integer> carrinho;

    public ComercianteView(Comerciante c){
        this.comerciante = c;
    }

    public ComercianteView(PedidoView pv){
        this.pedido = pv;
    }

    public void Acoes(){

        int choose;
        acoesLoop: while(true){
            System.out.println("\n= Opções disponíveis");

            System.out.println("1 - Gerenciar produtos");
            System.out.println("2 - Gerenciar pedidos");
            System.out.println("0 - Voltar");

            try{
                System.out.print("Ação -> ");
                choose = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e){
                System.out.println("\n !! Entrada inválida !!");
                continue;
            }

            switch(choose){
                case 1:{
                    GerenciarProdutos();
                    break;
                }
                case 2:{
                    GerenciarPedidos();
                    break;
                }
                case 0:{
                    break acoesLoop;
                }
                default:{
                    System.out.println("!! Opção inválida !!");
                }
            }
        }
    }

    public void GerenciarProdutos(){
        int escolhaP;
        gpLoop: while(true){
            System.out.println("Opções:");

            System.out.println("1 - Adicionar produto");
            System.out.println("2 - Remover produto");
            System.out.println("0 - Voltar");

            try{
                System.out.print("Escolha - > ");
                escolhaP = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada inválida !!");
                continue;
            }

            switch(escolhaP){
                case 1:{
                    AdicionarProduto();
                    break;
                }
                case 2:{
                    RemoverProduto();
                    break;
                }
                case 0:{
                    break gpLoop;
                }
                default:{
                    System.out.println("\n!! Opção invalida !!");
                }
            }

        }
    }

    public void AdicionarProduto(){
        System.out.print("Digite o id -> ");
        int id = Integer.parseInt(scan.nextLine());

        System.out.print("Digite o nome -> ");
        String nome = scan.nextLine();
        
        System.out.print("Digite o preço -> ");
        double preco = Double.parseDouble(scan.nextLine());

        Produto p = new Produto(id, nome, preco);
        comerciante.AdicionarProduto(p);
    }

    public void RemoverProduto(){
        System.out.print("Digite o id -> ");
        int id = Integer.parseInt(scan.nextLine());

        comerciante.RemoverProduto(id);
    }

    public void GerenciarPedidos(){
        int id;
        String aprovacao;

        List<Pedido> pendentes = comerciante.getPedidosPendentes();

        if(pendentes.isEmpty()){
            System.out.println("Nenhum pedido encontrado!");
            return;
        }

        for(Pedido p : pendentes){
            System.out.println("=======================");
            System.out.println("Pedido #" + p.getId());
            System.out.println("Cliente: " + p.getNomeCliente());
            System.out.println("Total R$: " + p.getValorTotal());
            System.out.println("Pagamento: " + p.getFormaPagamento());
        }

        try{
            System.out.print("\nDigite o id do pedido para aprovar/recusar (0 para sair) -> ");
            id = Integer.parseInt(scan.nextLine());

            if(id == 0) return;

            System.out.print("Aprovar(a) ou recusar(r)? -> ");
            aprovacao = scan.nextLine();

        }catch(NumberFormatException e){
            System.out.println("\n!! Entrada inválida !!\n");
            return;
        }

        if(aprovacao.equals("a")) comerciante.AprovarPedido(id);
        if(aprovacao.equals("r")) comerciante.RecusarPedido(id);
    }
}