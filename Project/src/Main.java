import java.util.Scanner;

import Views.ClienteView;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ClienteView cliente = new ClienteView();

        boolean cadastroAtivo = false;

        doWhileLoop: do{

            System.out.println("\nEscolha qual perfil deseja seguir");
            System.out.println("1 - Cliente");
            System.out.println("2 - Entregador");
            System.out.println("3 - Comerciante");
            System.out.println("0 - Sair");

            System.out.print("Opção -> ");
            int option = scan.nextInt();

            switch (option) {
                case 1: {
                    System.out.println("Perfil Cliente");

                    cliente.Ativo(cadastroAtivo);
                    
                    if(cadastroAtivo)
                    {
                        if(cliente.PrimeiroAcesso()) cliente.CadastrarConta();
                    }

                    cliente.Acoes();
                    if(cliente.Voltar()) continue doWhileLoop;

                    break doWhileLoop;
                }
                case 2: {
                    System.out.println("Perfil Entregador");
                    break doWhileLoop;
                }
                case 3: {
                    System.out.println("Perfil Comerciante");
                    break doWhileLoop;
                }
                case 0: {
                    System.out.println("Saindo..");
                    break doWhileLoop;
                }
                default: {
                    System.out.println("Opa... opção inválida!\n");
                }
            }
        }while(true);

        scan.close();
        System.out.println("Fim do teste!");
    }
}