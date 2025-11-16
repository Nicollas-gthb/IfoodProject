package Views;

import Modelos.*;
import java.util.Scanner;

public class EntregadorView {
    Scanner scan = new Scanner(System.in);

    Comerciante comercianteV;
    Entregador entregador;
    Veiculo veiculo;

    private int choose;
    private boolean naoCadastrou = true;
    private boolean ativo;
    private String vaiAlterar;

    public EntregadorView(Comerciante c){
        this.comercianteV = c;
    }

    public boolean PrimeiroAcesso(){
        return naoCadastrou;
    }

    public void CadastrarConta() {
        naoCadastrou = false;
        System.out.println("\n= Conta nova");

        System.out.print("Nome -> ");
        String nome = scan.nextLine();

        System.out.print("Telefone -> ");
        String telefone = scan.nextLine();

        System.out.print("Data de Nascimento -> ");
        String data = scan.nextLine();

        System.out.print("Email -> ");
        String email = scan.nextLine();

        System.out.print("Senha -> ");
        String senha = scan.nextLine();


        System.out.println("\n= Defina um veiculo:");

        System.out.print("Placa -> ");
        String placa = scan.nextLine();

        System.out.print("Ano -> ");
        String ano = scan.nextLine();

        System.out.print("Modelo -> ");
        String modelo = scan.nextLine();


        System.out.println("\nCadastro concluído!");

        entregador = new Entregador(nome, telefone, data, email, senha);
        veiculo = new Veiculo(placa, ano, modelo);
    }

    public void MostrarInfo(){
        System.out.println("Nome: " + entregador.getNome());
        System.out.println("Telefone: " + entregador.getTelefone());
        System.out.println("Data de Nascimento: " + entregador.getDataNasc());
        System.out.println("Email: " + entregador.getEmail());
        System.out.println("Senha: " + entregador.getSenha());

    }
    public void AlterarInfo(){
        System.out.print("Novo nome -> ");
        String novoNome = scan.nextLine();
        entregador.setNome(novoNome);

        System.out.print("Novo telefone -> ");
        String novoTelefone = scan.nextLine();
        entregador.setTelefone(novoTelefone);

        System.out.print("Nova data -> ");
        String novaData = scan.nextLine();
        entregador.setDataNasc(novaData);

        System.out.print("Novo email -> ");
        String novoEmail = scan.nextLine();
        entregador.setEmail(novoEmail);

        System.out.print("Nova senha -> ");
        String novaSenha = scan.nextLine();
        entregador.setSenha(novaSenha);
    }

    public void MostrarVeiculo(){
        System.out.println("Placa: " + veiculo.getPlaca());
        System.out.println("Ano: " + veiculo.getAno());
        System.out.println("Modelo: " + veiculo.getModelo());
    }
    public void AlterarVeiculo(){
        System.out.print("Nova placa -> ");
        String novaPlaca = scan.nextLine();
        veiculo.setPlaca(novaPlaca);

        System.out.print("Novo ano -> ");
        String novoAno = scan.nextLine();
        veiculo.setAno(novoAno);

        System.out.print("Novo modelo -> ");
        String novoModelo = scan.nextLine();
        veiculo.setModelo(novoModelo);
    }

    public void Acoes(){

        acoes: while(true){
            System.out.println("\n= Opções disponiveis");

            System.out.println("1 - Ver pedidos disponiveis");
            System.out.println("2 - Dados do perfil");
            System.out.println("0 - Voltar");

            try{
                System.out.println("Ação -> ");
                choose = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada inválida !!\n");
            }

            switch(choose){
                case 1: break;
                case 2: MostrarDados(); break;
                case 0: break acoes;
                default: System.out.println("\n!! Opção invalida !!\n");
            }
        }
    }

    public void MostrarDados(){
        if(!ativo){
            System.out.println("!! Dados desativados para manutenção !!");
        }else{
            System.out.println("= Dados do perfil:\n");
            MostrarInfo();

            System.out.println("\nVeiculo:\n");
            MostrarVeiculo();

            alterLoop: while(true){
                System.out.print("\n= Deseja alterar algum dado? (s / n) -> ");
                vaiAlterar = scan.nextLine();

                switch(vaiAlterar){
                    case "s": AlterarDados(); break;
                    case "n": break alterLoop;
                    default: System.out.println("\n!! Opção invalida !!\n");
                }
            }
        }
    }

    public void AlterarDados(){
        System.out.println("= Insira os novos dados: ");
        AlterarInfo();

        alterLoop: while(true){
            System.out.println("\n= Deseja alterar o veiculo? (s / n) -> ");
            vaiAlterar = scan.nextLine();

            switch(vaiAlterar){
                case "s": AlterarVeiculo(); break;
                case "n": break alterLoop;
                default: System.out.println("\n!! Opção invalida !!\n");
            }
        }
        System.out.println("Dados atualizados com sucesso!");
    }

    public void Ativar(boolean a){
        ativo = a;
    }
}
