package Views;

import java.util.Scanner;
import Modelos.Comerciante;
import Modelos.Produto;

public class ComercianteView {

    Scanner scan = new Scanner(System.in);
    private Comerciante comerciante;

    public ComercianteView(Comerciante c){
        this.comerciante = c;
    }

    public void Acoes(){

        int choose;
        acoesLoop: while(true){
            System.out.println("\n= Opções disponíveis");

            System.out.println("1 - Gerenciar produtos");
            System.out.println("2 - ");
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
}