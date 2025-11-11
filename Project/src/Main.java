import Views.ClienteView;
import java.util.Scanner;
//import Views.EntregadorView;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ClienteView cliente = new ClienteView();
        //EntregadorView entregador = new EntregadorView();

        //variavel de controle sobre o cadastro do usuario
        boolean cadastroAtivo = false;
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
                continue doWhileLoop;
            }

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

                    break;
                }
                case 2: {
                    System.out.println("Perfil Entregador");

                    
                    break;
                }
                case 3: {
                    System.out.println("Perfil Comerciante");
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