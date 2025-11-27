package com.nicollas.ifood.Views;

import java.util.Scanner;
import com.nicollas.ifood.Modelos.Pagamento;

public class PagamentoView {

    Scanner scan = new Scanner(System.in);
    private Pagamento pagamento;
    private boolean confirmado = false;

    public PagamentoView() {
        this.pagamento = new Pagamento();
    }

    public boolean Finalizar(){
        int metodo;

        while(true){
            System.out.println("\n=Escolha o metodo de pagamento: ");

            System.out.println("1 - Cartão");
            System.out.println("2 - Pix");
            System.out.println("0 - Voltar");

            try{
                System.out.print("Metodo -> ");
                metodo = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e){
                System.out.println("\n!!Entrada inválida!!\n");
                continue;
            }

            switch(metodo){
                case 1:{
                    if(Cartao()){
                        return true;
                    }else{
                        continue;
                    }
                }
                case 2: {
                    if(Pix()){
                        return true;
                    }else{
                        continue;
                    }
                }
                case 0: return false;
                default: {
                    System.out.println("\n ! Metodo inválido !\n");
                }
            }
        }
    }

    public boolean Cartao(){
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

                confirmado = true;
                System.out.println("\nPedido realizado com sucesso !!\n");
                System.out.println("Só aguardar a entrega...");
                pagamento.DadosCartao(tipo, numero, senhaCartao);
                pagamento.setForma("Cartão");
                return confirmado;
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada Inválida !!\n");
            }
        }
    }

    public boolean Pix(){
        String chave;
        int senhaPix;

        while(true){
            try{
                System.out.print("Insira a chave/código PIX -> ");
                chave = scan.nextLine();

                System.out.print("Senha -> ");
                senhaPix = Integer.parseInt(scan.nextLine());

                confirmado = true;
                System.out.println("\nPedido realizado com sucesso !!\n");
                System.out.println("Só aguardar a entrega...");
                pagamento.DadosPix(chave, senhaPix);
                pagamento.setForma("Pix");
                return confirmado;
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada Inválida !!\n");
            }
        }
    }

    public Pagamento getPagamento() {
        return pagamento;
    }
}