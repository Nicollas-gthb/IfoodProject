package Views;

import java.util.Scanner;

import Modelos.Pagamento;

public class PagamentoView {

    Scanner scan = new Scanner(System.in); 
    PedidoView pedido;
    Pagamento pagamento = new Pagamento();

    public void Finalizar(){

        finalLoop: while(true){
            System.out.println("\n=Escolha o metodo de pagamento: ");

            System.out.println("1 - Cartão");
            System.out.println("2 - Pix");
            System.out.println("0 - Voltar");

            System.out.print("Metodo -> ");
            int metodo = Integer.parseInt(scan.nextLine());

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
        System.out.print("(1) Credito ou (2) Debito -> ");
        int tipo = Integer.parseInt(scan.nextLine());

        System.out.print("Insira numero do cartao -> ");
        String numero = scan.nextLine();

        System.out.print("Senha -> ");
        int senhaCartao = Integer.parseInt(scan.nextLine());

        System.out.println("\n Pedido realizado com sucesso !!\n");
        System.out.println("Só aguardar a entrega...");

        pagamento.DadosCartao(tipo, numero, senhaCartao);        
    }

    public void Pix(){
        System.out.print("Insira a chave/código PIX -> ");
        String chave = scan.nextLine();

        System.out.print("Senha -> ");
        int senhaPix = Integer.parseInt(scan.nextLine());

        System.out.println("\n Pedido realizado com sucesso !!\n");
        System.out.println("Só aguardar a entrega...");

        pagamento.DadosPix(chave, senhaPix);                
    }

}