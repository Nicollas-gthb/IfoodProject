package com.nicollas.ifood.Views;

import com.nicollas.ifood.Modelos.Cliente;
import com.nicollas.ifood.Modelos.Comerciante;
import com.nicollas.ifood.Modelos.Endereco;
import com.nicollas.ifood.DAO.ClienteDAO;
import com.nicollas.ifood.DAO.EnderecoDAO;

import java.util.Scanner;

public class ClienteView {

    Scanner scan = new Scanner(System.in);
    Cliente cliente;
    PedidoView pedido;
    Endereco endereco;

    private int choose;
    private boolean naoCadastrou = true;
    private boolean ativo;
    private String vaiAlterar;

    private final ClienteDAO clienteD = new ClienteDAO();
    private final EnderecoDAO enderecoD = new EnderecoDAO();

    //Construtor para inicializar 'PedidoView pedido'
    public ClienteView(Comerciante c) {
        this.pedido = new PedidoView(c, this);
    }

    private void LoadEnderecoPrincipal() {
        if (this.cliente == null) return; // Segurança

        int idEndereco = this.cliente.getIdEnderecoPrincipal();

        if (idEndereco > 0) {
            // Busca o endereço correto usando a FK salva no cliente
            this.endereco = enderecoD.SelectId(idEndereco);
        } else {
            // Se a FK for 0/NULL (cliente novo), o endereço em memória fica nulo
            this.endereco = null;
        }
    }

    //Conferir primeiro acesso
    public boolean PrimeiroAcesso() {
        return naoCadastrou;
    }

    public boolean FazerLogin() {
        System.out.println("\n= Login Cliente");
        System.out.print("Email -> ");
        String email = scan.nextLine();
        System.out.print("Senha -> ");
        String senha = scan.nextLine();

        // 1. Busca o cliente no DB pelo email
        Cliente clienteDoDB = clienteD.SelectEmail(email);

        // 2. Verifica se o cliente existe e se a senha está correta
        if (clienteDoDB != null && clienteDoDB.getSenha().equals(senha)) {
            this.cliente = clienteDoDB; // Seta o objeto Cliente logado

            LoadEnderecoPrincipal();

            if (this.endereco == null) {
                System.out.println("AVISO: Endereço principal (ID 1) não encontrado. Cadastre um endereço!");
            }

            System.out.println("\nLogin do Cliente " + cliente.getNome() + " realizado com sucesso!");
            naoCadastrou = false; // Indica que o cliente está logado
            return true;
        } else {
            System.out.println("\n!! Email ou senha inválidos !!\n");
            return false;
        }
    }

    //Inserir dados de cadastro
    public void CadastrarConta() {
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


        cliente = new Cliente(nome, email, senha, telefone, data);
        int idClienteGerado = clienteD.Create(cliente);
        if (idClienteGerado != -1) {
            cliente.setId(idClienteGerado); // Salva o ID no objeto Cliente da View
            System.out.println("Cliente cadastrado com sucesso! ID: " + idClienteGerado);

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


            endereco = new Endereco(rua, numero, cidade, bairro, cep, complemento);
            int idEndereco = enderecoD.Create(endereco);

            if (idEndereco != -1) {
                endereco.setId(idEndereco);
                cliente.setIdEnderecoPrincipal(idEndereco);

                if (clienteD.Update(cliente)) {
                    System.out.println("Endereço cadastrado e vinculado ao perfil com sucesso! ID: " + idEndereco);
                } else {
                    System.out.println("AVISO: Endereço cadastrado (ID: " + idEndereco + "), mas falha ao vincular ao perfil no DB.");
                }
            } else {
                System.out.println("Aviso: Endereço não foi salvo corretamente.");
            }
        } else {
            System.out.println("Falha ao cadastrar cliente.");
            // Lógica de tratamento de erro...
        }

    }

    public int getIdCliente() {
        // Retorna o ID do objeto cliente, que foi preenchido após o CREATE no DB
        return cliente != null ? cliente.getId() : -1;
    }

    public String getNomeCliente() {
        if (cliente != null) return cliente.getNome();
        return "Cliente desconhecido";
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

        if (clienteD.Update(cliente)) {
            System.out.println("\n[SISTEMA] Dados do perfil atualizados com sucesso no DB.");
        } else {
            System.out.println("\n[SISTEMA] Falha ao atualizar dados do perfil no DB.");
        }
    }

    public void MostrarEndereco() {
        System.out.println("Rua: " + endereco.getRua());
        System.out.println("N°: " + endereco.getNumero());
        System.out.println("Cidade: " + endereco.getCidade());
        System.out.println("Bairro: " + endereco.getBairro());
        System.out.println("CEP: " + endereco.getCep());
        System.out.println("Complemento: " + endereco.getComplemento());
    }
    public void AlterarEndereco() {

        boolean isNewAddress = (cliente.getIdEnderecoPrincipal() == 0);

        if (isNewAddress) {
            System.out.println("\n= Cadastro de Endereço Principal: ");
            // Inicializa o objeto para coletar os dados
            this.endereco = new Endereco("", "", "", "", "", "");
        } else {
            System.out.println("\n= Novo Endereço: ");
            // Garante que o endereço atual está em 'this.endereco' para exibir os dados antigos
            if(this.endereco == null) LoadEnderecoPrincipal();
        }

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

        if (isNewAddress) {
            // A. CRIA E SALVA O NOVO ENDEREÇO NO DB
            int idEndereco = enderecoD.Create(endereco);

            if (idEndereco != -1) {
                endereco.setId(idEndereco);

                // B. VINCULA A FK NO CLIENTE E SALVA O CLIENTE NO DB
                cliente.setIdEnderecoPrincipal(idEndereco);

                if (clienteD.Update(cliente)) {
                    System.out.println("\n[SISTEMA] Endereço Principal cadastrado e vinculado ao perfil com sucesso!");
                } else {
                    System.out.println("\n[SISTEMA] Endereço cadastrado, mas falha ao vincular ao perfil. ");
                }
            } else {
                System.out.println("\n[SISTEMA] Falha ao cadastrar endereço no DB.");
            }

        } else {
            // C. ATUALIZA O ENDEREÇO EXISTENTE
            if (enderecoD.Update(endereco)) {
                System.out.println("\n[SISTEMA] Endereço atualizado com sucesso no DB.");
            } else {
                System.out.println("\n[SISTEMA] Falha ao atualizar endereço no DB.");
            }
        }
    }

    //Escolhas no perfil de cliente
    public void Acoes() {
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
                continue;
            }

            switch(choose)
            {
                case 1: pedido.ExibirMenu(); break;
                case 2: MostrarDados(); break;
                case 0: break acoesLoop;
                default: System.out.println("\n!! Opa.. ação inválida !!");
            }
        }
    }

    public void MostrarDados() {
        if(!ativo){
            System.out.println("!! Dados desativados para manutenção !!");
        }else{
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

    //Alterar dados cadastrados
    public void AlterarDados() {
        System.out.println("= Insira os novos dados: ");
        AlterarInfo();

        alterLoop: while(true){
            System.out.print("\n= Deseja alterar o endereço? (s / n) -> ");
            vaiAlterar = scan.nextLine();

            switch(vaiAlterar){
                case "s": AlterarEndereco(); break alterLoop;
                case "n": break alterLoop;
                default: System.out.println("\n!! Opção invalida !!");
            }
        }
        System.out.println("Dados atualizados com sucesso!");
    }

    public void Ativar(boolean a){
        ativo = a;
    }
}