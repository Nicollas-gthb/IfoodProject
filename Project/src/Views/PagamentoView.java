package Views;

import java.util.Scanner;

import Modelos.Pagamento;

public class PagamentoView {

    Scanner scan = new Scanner(System.in); 
    PedidoView pedido;
    Pagamento pagamento = new Pagamento();

    public void Finalizar(){
        int metodo;

        finalLoop: while(true){
            System.out.println("\n=Escolha o metodo de pagamento: ");

            System.out.println("1 - Cartão");
            System.out.println("2 - Pix");
            System.out.println("0 - Voltar");

            try{
                System.out.print("Metodo -> ");
                metodo = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e){
                System.out.println("\n!!Entrada inválida!!\n");
                continue finalLoop;
            }

            switch(metodo){
                case 1: Cartao(); break finalLoop;
                case 2: Pix(); break finalLoop;
                case 0: break finalLoop;
                default: {
                    System.out.println("\n ! Metodo inválido !\n");
                }
            }
        }
    }

    public void Cartao(){
        int tipo;
        String numero;
        int senhaCartao;

        while(true){
            try{
                System.out.print("(1) Credito ou (2) Debito -> ");
                tipo = Integer.parseInt(scan.nextLine());

                System.out.print("Insira numero do cartao -> ");
                numero = scan.nextLine();

                System.out.print("Senha -> ");
                senhaCartao = Integer.parseInt(scan.nextLine());

                System.out.println("\n Pedido realizado com sucesso !!\n");
                System.out.println("Só aguardar a entrega...");
                break;
            }catch(NumberFormatException e){
                System.out.println("\n!!Entrada inválida!!\n");
                continue;
            }
        }
        
        pagamento.DadosCartao(tipo, numero, senhaCartao);        
    }

    public void Pix(){
        String chave;
        int senhaPix;

        while(true){
            try{
                System.out.print("Insira a chave/código PIX -> ");
                chave = scan.nextLine();

                System.out.print("Senha -> ");
                senhaPix = Integer.parseInt(scan.nextLine());

                System.out.println("\n Pedido realizado com sucesso !!\n");
                System.out.println("Só aguardar a entrega...");
                break;
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada inválida !!\n");
                continue;
            }
        }
        
        pagamento.DadosPix(chave, senhaPix);                
    }

}