package Views;

import java.util.Scanner;
import Modelos.Cliente;

public class ClienteView {

    Scanner scan = new Scanner(System.in);
    Cliente cliente;
    PedidoView pedido;

    private int choose;
    private boolean vaiVoltar = false;
    private boolean naoCadastrou = true;
    private boolean ativo;

    //Construtor para inicializar 'PedidoView pedido'
    public ClienteView(){
        this.pedido = new PedidoView(this);
    }

    //setter para o vaiVoltar
    public void setVaiVoltar(boolean valor){
        vaiVoltar = valor;
    }

    //Conferir primeiro acesso
    public boolean PrimeiroAcesso()
    {
        return naoCadastrou;
    }

    //Inserir dados de cadastro
    public void CadastrarConta()
    {
        naoCadastrou = false;
        System.out.println("\n= Conta nova");

        System.out.print("Nome -> ");
        String nome = scan.nextLine();

        System.out.print("Email -> ");
        String email = scan.nextLine();

        System.out.print("Senha -> ");
        String senha = scan.nextLine();

        System.out.print("Telefone -> ");
        String telefone = scan.nextLine();

        System.out.print("Data de Nascimento -> ");
        String data = scan.nextLine();

        System.out.println("\nCadastro concluído!");

        cliente = new Cliente(nome, email, senha, telefone, data);
    }

    //Mostrar e Alterar dados cliente
    public void MostrarNome()
    {
        System.out.println("Nome: " + cliente.getNome());
    }
    public void AlterarNome()
    {
        System.out.print("Novo nome -> ");
        String novoNome = scan.nextLine();
        cliente.setNome(novoNome);
    }

    public void MostrarEmail()
    {
        System.out.println("Email: " + cliente.getEmail());
    }
    public void AlterarEmail()
    {
        System.out.print("Novo email -> ");
        String novoEmail = scan.nextLine();
        cliente.setEmail(novoEmail);
    }

    public void MostrarSenha()
    {
        System.out.println("Senha: " + cliente.getSenha());
    }
    public void AlterarSenha()
    {
        System.out.print("Nova senha -> ");
        String novaSenha = scan.nextLine();
        cliente.setSenha(novaSenha);
    }

    public void MostrarTelefone()
    {
        System.out.println("Telefone: " + cliente.getTelefone());
    }
    public void AlterarTelefone()
    {
        System.out.print("Novo telefone-> ");
        String novoTelefone = scan.nextLine();
        cliente.setTelefone(novoTelefone);
    }

    public void MostrarData()
    {
        System.out.println("Data de Nascimento: " + cliente.getDataNasc());
    }
    public void AlterarData()
    {
        System.out.print("Nova data-> ");
        String novaData = scan.nextLine();
        cliente.setDataNasc(novaData);
    }

    //Escolhas no perfil de cliente
    public void Acoes()
    {
        vaiVoltar = false;
        acoesLoop: while(true){

            System.out.println("\n= Opções disponíveis");
    
            System.out.println("1 - Escolher pedido");
            System.out.println("2 - Dados do Perfil");
            System.out.println("0 - Voltar");
    
            System.out.print("Ação -> ");
            choose = Integer.parseInt(scan.nextLine());
    
            switch(choose)
            {
                case 1: {
                    pedido.ExibirMenu(); 
                    if(vaiVoltar) break acoesLoop;
                    break;
                }
                case 2: MostrarDados(); break;
                case 0: {
                    vaiVoltar = true; 
                    break acoesLoop;
                }
                default:{
                    System.out.println("\n!! Opa.. ação inválida !!");
                }
            }
        }
    }

    //Exibir dados cadastrados
    public void Ativo(boolean condition)
    {
        ativo = condition;
    }

    public void MostrarDados()
    {
        if(!ativo)
        {
            System.out.println("!! Dados desativados para manutenção !!");
            
            return;
        }
        else
        {
            System.out.println("= Dados do perfil:\n");
            MostrarNome();
            MostrarEmail();
            MostrarSenha();
            MostrarTelefone();
            MostrarData();

            System.out.print("\n= Deseja alterar algum dado? (s / n) -> ");
            String vaiAlterar = scan.nextLine();

            switch(vaiAlterar){
                case "s" -> AlterarDados();
                case "n" -> {}
                default -> System.out.println("\n!! Opção invalida !!");
            }
        }
    }

    //Altrar dados cadastrados
    public void AlterarDados()
    {
        System.out.println("= Insira os novos dados: ");
        AlterarNome();
        AlterarEmail();
        AlterarSenha();
        AlterarTelefone();
        AlterarData();
        System.out.println("Dados atualizados com sucesso!");

    }

    //Ação de voltar
    public boolean Voltar()
    {
        return vaiVoltar;
    }
}
