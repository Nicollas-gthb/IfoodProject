package com.nicollas.ifood;

import com.nicollas.ifood.Views.ClienteView;
import com.nicollas.ifood.Views.ComercianteView;
import com.nicollas.ifood.Views.EntregadorView;
import com.nicollas.ifood.Modelos.Comerciante;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Comerciante comerciante = new Comerciante();
        ClienteView clienteV = new ClienteView(comerciante);
        ComercianteView comercianteV = new ComercianteView(comerciante);
        EntregadorView entregadorV = new EntregadorView(comerciante);

        //variavel de controle sobre o cadastro do usuario
        boolean cadastroAtivo = true;
        int option;

        doWhileLoop: while(true){

            System.out.println("\n= Escolha qual perfil deseja seguir");
            System.out.println("1 - Cliente");
            System.out.println("2 - Entregador");
            System.out.println("3 - Comerciante");
            System.out.println("0 - Sair");

            try{
                System.out.print("Opção -> ");
                option = Integer.parseInt(scan.nextLine());  
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada invalida !!\n");
                continue;
            }

            switch (option) {
                case 1: {
                    System.out.println("Perfil Cliente");
                    clienteV.Ativar(cadastroAtivo);

                    boolean logado = false;

                    if(clienteV.PrimeiroAcesso()) {
                        // Se naoCadastrou == true, faz o cadastro (e fica logado)
                        clienteV.CadastrarConta();
                        logado = true;
                    } else {
                        // Se naoCadastrou == false, tenta fazer o login
                        logado = clienteV.FazerLogin();
                    }

                    // Só prossegue se o login/cadastro for bem-sucedido
                    if (logado) {
                        clienteV.Acoes();
                    }
                    break;
                }
                case 2: {
                    System.out.println("Perfil Entregador");
                    entregadorV.Ativar(cadastroAtivo);

                    boolean logado = false;

                    if(entregadorV.PrimeiroAcesso()) {
                        // Se naoCadastrou == true, faz o cadastro (e fica logado)
                        entregadorV.CadastrarConta();
                        logado = true;
                    } else {
                        // Se naoCadastrou == false, tenta fazer o login
                        logado = entregadorV.FazerLogin();
                    }

                    // Só prossegue se o login/cadastro for bem-sucedido
                    if (logado) {
                        entregadorV.Acoes();
                    }
                    break;
                }
                case 3: {
                    System.out.println("Perfil Comerciante");
                    comercianteV.Acoes();
                    break;
                }
                case 0: {
                    System.out.println("Saindo..");
                    break doWhileLoop;
                }
                default: {
                    System.out.println("Opa... opção inválida!\n");
                }
            }
        }

        scan.close();
        System.out.println("Fim do teste!");
    }
}