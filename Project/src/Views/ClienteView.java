package Views;

import java.util.Scanner;
import Modelos.Cliente;
import Modelos.Endereco;

public class ClienteView {

    Scanner scan = new Scanner(System.in);
    Cliente cliente;
    PedidoView pedido;
    Endereco endereco;

    private int choose;
    private boolean vaiVoltar = false;
    private boolean naoCadastrou = true;
    private boolean ativo;
    private String vaiAlterar;

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


        System.out.println("\n= Defina um endereço:");

        System.out.print("Rua -> ");
        String rua = scan.nextLine();

        System.out.print("Numero -> ");
        String numero = scan.nextLine();

        System.out.print("Cidade -> ");
        String cidade = scan.nextLine();

        System.out.print("Bairro -> ");
        String bairro = scan.nextLine();

        System.out.print("CEP -> ");
        String cep = scan.nextLine();

        System.out.print("Complemento -> ");
        String complemento = scan.nextLine();


        System.out.println("\nCadastro concluído!");

        cliente = new Cliente(nome, email, senha, telefone, data);
        endereco = new Endereco(rua, numero, cidade, bairro, cep, complemento);
    }

    //Mostrar e Alterar dados cliente
    public void MostrarInfo(){
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Senha: " + cliente.getSenha());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Data de Nascimento: " + cliente.getDataNasc());

    }
    public void AlterarInfo(){
        System.out.print("Novo nome -> ");
        String novoNome = scan.nextLine();
        cliente.setNome(novoNome);

        System.out.print("Novo email -> ");
        String novoEmail = scan.nextLine();
        cliente.setEmail(novoEmail);

        System.out.print("Nova senha -> ");
        String novaSenha = scan.nextLine();
        cliente.setSenha(novaSenha);

        System.out.print("Novo telefone -> ");
        String novoTelefone = scan.nextLine();
        cliente.setTelefone(novoTelefone);

        System.out.print("Nova data -> ");
        String novaData = scan.nextLine();
        cliente.setDataNasc(novaData);
    }
    
    
    public void MostrarEndereco()
    {
        System.out.println("Rua: " + endereco.getRua());
        System.out.println("N°: " + endereco.getNumero());
        System.out.println("Cidade: " + endereco.getCidade());
        System.out.println("Bairro: " + endereco.getBairro());
        System.out.println("CEP: " + endereco.getCep());
        System.out.println("Complemento: " + endereco.getComplemento());
    }
    public void AlterarEndereco()
    {
        System.out.print("Nova rua -> ");
        String novaRua = scan.nextLine();
        endereco.setRua(novaRua);

        System.out.print("Novo numero -> ");
        String novoNumero = scan.nextLine();
        endereco.setNumero(novoNumero);

        System.out.print("Nova cidade -> ");
        String novaCidade = scan.nextLine();
        endereco.setCidade(novaCidade);

        System.out.print("Novo bairro -> ");
        String novoBairro = scan.nextLine();
        endereco.setBairro(novoBairro);

        System.out.print("Novo cep -> ");
        String novoCep = scan.nextLine();
        endereco.setCep(novoCep);

        System.out.print("Novo complemento -> ");
        String novoComplemento = scan.nextLine();
        endereco.setComplemento(novoComplemento);
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
    
            try{
                System.out.print("Ação -> ");
                choose = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada invalida !!\n");
            }
                
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
            MostrarInfo();
            
            System.out.println("\nEndereço:\n");
            MostrarEndereco();

            alterLoop: while(true){
                System.out.print("\n= Deseja alterar algum dado? (s / n) -> ");
                vaiAlterar = scan.nextLine();
    
                switch(vaiAlterar){
                    case "s": AlterarDados(); break;
                    case "n": break alterLoop;
                    default: System.out.println("\n!! Opção invalida !!");
                }
            }
        }
    }

    //Altrar dados cadastrados
    public void AlterarDados()
    {
        System.out.println("= Insira os novos dados: ");
        AlterarInfo();

        alterLoop: while(true){
            System.out.print("\n= Deseja alterar o endereço? (s / n) -> ");
            vaiAlterar = scan.nextLine();

            switch(vaiAlterar){
                case "s": AlterarEndereco(); break;
                case "n": break alterLoop;
                default: System.out.println("\n!! Opção invalida !!");
            }
        }
        System.out.println("Dados atualizados com sucesso!");
    }

    //Ação de voltar
    public boolean Voltar()
    {
        return vaiVoltar;
    }
}