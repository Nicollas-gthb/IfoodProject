package Views;

import java.util.Scanner;

public class ComercianteView {

    private int choose;
    private int escolhaP;

    Scanner scan = new Scanner(System.in);

    public void Acoes(){

        acoesLoop: while(true){
            System.out.println("\n= Opções disponíveis");

            System.out.println("1 - Gerenciar produtos");
            System.out.println("2 - ");
            System.out.println("0 - Voltar");

            try{
                System.out.println("Ação -> ");
                choose = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e){
                System.out.println("\n !! Entrada inválida !!");
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
        gpLoop: while(true){
            System.out.println("Opções:");

            System.out.println("1 - Adicionar produto");
            System.out.println("2 - Remover produto");
            System.out.println("0 - Voltar");

            try{
                System.out.println("Escolha - >");
                escolhaP = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada inválida !!");
            }

            switch(escolhaP){
                case 1:{
                    AdicionarProduto();
                    break;
                }
                case 2:{
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
        System.out.println("Digite o id : ");
        int id = Integer.parseInt(scan.nextLine());

        System.out.println("Digite o nome : ");
        String nome = scan.nextLine();
        
        System.out.println("Digite o preço : ");
        double preco = Double.parseDouble(scan.nextLine());
    }
}
