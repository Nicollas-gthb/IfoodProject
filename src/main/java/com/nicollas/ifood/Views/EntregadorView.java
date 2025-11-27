package com.nicollas.ifood.Views;

import com.nicollas.ifood.Modelos.*;
import com.nicollas.ifood.DAO.EntregadorDAO;
import com.nicollas.ifood.DAO.VeiculoDAO;
import com.nicollas.ifood.DAO.PedidoDAO;

import java.util.List;
import java.util.Scanner;

public class EntregadorView {
    Scanner scan = new Scanner(System.in);

    Comerciante comerciante;
    Entregador entregador;
    Veiculo veiculo;

    private int choose;
    private boolean naoCadastrou = true;
    private boolean ativo;
    private String vaiAlterar;
    private final EntregadorDAO entregadorD = new EntregadorDAO();
    private final VeiculoDAO veiculoD = new VeiculoDAO();
    private final PedidoDAO pedidoD = new PedidoDAO();

    public EntregadorView(Comerciante c){
        this.comerciante = c;
    }

    public boolean PrimeiroAcesso(){
        return naoCadastrou;
    }

    public boolean FazerLogin() {
        System.out.println("\n= Login Entregador");
        System.out.print("Email -> ");
        String email = scan.nextLine();
        System.out.print("Senha -> ");
        String senha = scan.nextLine();

        // 1. Busca o entregador no DB pelo email
        Entregador entregadorDoDB = entregadorD.selectEmail(email);

        // 2. Verifica se o entregador existe e se a senha está correta
        if (entregadorDoDB != null && entregadorDoDB.getSenha().equals(senha)) {
            this.entregador = entregadorDoDB; // Seta o objeto Entregador logado

            // 3. Busca o Veículo associado (USANDO ID VEÍCULO DO ENTREGADOR)
            if (entregador.getIdVeiculo() != 0) {
                this.veiculo = veiculoD.SelectId(entregador.getIdVeiculo());
                if (this.veiculo == null) {
                    System.out.println("AVISO: Veículo associado (ID: " + entregador.getIdVeiculo() + ") não encontrado no DB.");
                }
            }

            System.out.println("\nLogin do Entregador " + entregador.getNome() + " realizado com sucesso!");
            naoCadastrou = false;
            return true;
        } else {
            System.out.println("\n!! Email ou senha inválidos !!\n");
            return false;
        }
    }

    public void CadastrarConta() {
        System.out.println("\n= Conta nova - Entregador");

        // 1. DADOS PESSOAIS
        System.out.print("Nome -> ");
        String nome = scan.nextLine();

        System.out.print("Telefone -> ");
        String telefone = scan.nextLine();

        System.out.print("Data de Nascimento -> ");
        String data = scan.nextLine(); // Manter como String, conforme o modelo

        System.out.print("Email -> ");
        String email = scan.nextLine();

        System.out.print("Senha -> ");
        String senha = scan.nextLine();


        // 2. DADOS DO VEÍCULO
        System.out.println("\n= Defina um veiculo:");

        System.out.print("Placa -> ");
        String placa = scan.nextLine();

        System.out.print("Ano -> ");
        String ano = scan.nextLine();

        System.out.print("Modelo -> ");
        String modelo = scan.nextLine();

        // FLUXO DE PERSISTÊNCIA:

        // A. Cria e salva o Veículo no DB (VeiculoDAO)
        this.veiculo = new Veiculo(placa, ano, modelo);
        int idVeiculo = veiculoD.Create(this.veiculo); // Usa o VeiculoDAO

        if (idVeiculo != -1) {
            this.veiculo.setId(idVeiculo);

            // B. Cria e salva o Entregador, usando o ID do Veículo (EntregadorDAO)
            this.entregador = new Entregador(nome, telefone, data, email, senha, this.veiculo);
            int idEntregador = entregadorD.create(this.entregador); // Usa o EntregadorDAO

            if (idEntregador != -1) {
                this.entregador.setId(idEntregador);
                System.out.println("\nEntregador e Veículo cadastrados com sucesso! [ID #"+ idEntregador +"]");
                naoCadastrou = false; // Indica sucesso
            } else {
                // Caso falhe o Entregador, idealmente o Veículo deveria ser deletado (Rollback)
                // Para simplificar, exibiremos a falha.
                System.out.println("\n!! Falha ao cadastrar entregador. Email pode já existir ou erro no DB. !!");
                // Tenta remover o veículo "órfão"
                if (veiculoD.Delete(idVeiculo)) {
                    System.out.println("Veículo criado (ID #"+ idVeiculo +") foi removido.");
                }
                naoCadastrou = true;
            }
        } else {
            System.out.println("\n!! Falha ao cadastrar veículo. Placa pode já existir ou erro no DB. !!");
            naoCadastrou = true;
        }
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

        if(entregadorD.update(entregador)){
            System.out.println("\n[SISTEMA] Dados do perfil atualizados com sucesso no DB.");
        }else{
            System.out.println("\n[SISTEMA] Falha ao atualizar os Dados do perfil no DB.");
        }
    }

    public void MostrarVeiculo(){
        System.out.println("Placa: " + veiculo.getPlaca());
        System.out.println("Ano: " + veiculo.getAno());
        System.out.println("Modelo: " + veiculo.getModelo());
    }
    public void AlterarVeiculo(){
        if (veiculo == null || veiculo.getId() == 0) {
            System.out.println("!! Não é possível alterar: Veículo não carregado ou sem ID. !!");
            return;
        }

        System.out.print("Nova placa -> ");
        String novaPlaca = scan.nextLine();
        veiculo.setPlaca(novaPlaca);

        System.out.print("Novo ano -> ");
        String novoAno = scan.nextLine();
        veiculo.setAno(novoAno);

        System.out.print("Novo modelo -> ");
        String novoModelo = scan.nextLine();
        veiculo.setModelo(novoModelo);

        if(veiculoD.Update(veiculo)){
            System.out.println("\n[SISTEMA] Veiculo atualizado com sucesso no DB.");
        }else{
            System.out.println("\n[SISTEMA] Falha ao atualizar o Veiculo no DB.");
        }
    }

    public void Acoes(){

        acoes: while(true){
            System.out.println("\n= Opções disponiveis");

            System.out.println("1 - Ver pedidos disponiveis");
            System.out.println("2 - Dados do perfil");
            System.out.println("0 - Voltar");

            try{
                System.out.print("Ação -> ");
                choose = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada inválida !!\n");
            }

            switch(choose){
                case 1: GerenciarPedidos(); break;
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

    public void GerenciarPedidos() {
        // Busca a lista atualizada de pedidos aprovados no DB (via Comerciante)
        List<Pedido> aprovados = comerciante.getPedido();

        if (aprovados.isEmpty()) {
            System.out.println("\n!! Nenhum pedido aprovado encontrado !!\n");
            return;
        }

        System.out.println("= Pedidos disponíveis para entrega:");
        for (Pedido p : aprovados) {
            System.out.println("================================");
            System.out.println("Pedido # " + p.getId()); // ID do DB
            System.out.println("Cliente: " + p.getNomeCliente());
            System.out.println("Destino: " + p.getEndereco().getEnderecoCurto());
            // Você pode adicionar mais detalhes do pedido se quiser
        }
        System.out.println("================================");

        // --- RESTO DO MÉT.ODO: Solicitação da Ação ---

        int id;
        String aceitar;

        try {
            System.out.print("\nDigite o ID do pedido para aceitar/recusar (0 para voltar) -> ");
            id = Integer.parseInt(scan.nextLine());

            if (id == 0) return; // Retorna se for 0

            System.out.print("Aceitar(a) ou recusar(r)? -> ");
            aceitar = scan.nextLine();

        } catch (NumberFormatException e) {
            System.out.println("\n!! Entrada invalida !!\n");
            return;
        }

        if (aceitar.equals("a")) AceitarPedido(id);
        if (aceitar.equals("r")) RecusarPedido(id);
    }

    public void AceitarPedido(int id) {
        String foiEntregue;
        Pedido p = BuscaPedido(id);

        if (p != null) {
            // 1. Mudar status para 'Em Entrega' no DB
            if (pedidoD.updateStatus(id, "Em Entrega")) {
                System.out.println("Pedido # " + id + " aceito! Status atualizado para 'Em Entrega'.");
                System.out.println("Dirija-se para o endereço de entrega!");

                System.out.print("\nPedido foi entregue? (s / n) -> ");
                foiEntregue = scan.nextLine();

                if (foiEntregue.equals("s")) {
                    // 2. Mudar status para 'Entregue' no DB
                    if (pedidoD.updateStatus(id, "Entregue")) {
                        // Não precisamos remover da lista do Comerciante,
                        // pois a próxima busca por 'Aprovados' não o trará.
                        System.out.println("Pedido entregue com sucesso! Status atualizado para 'Entregue'.");
                    } else {
                        System.out.println("!! Falha ao marcar pedido como Entregue no DB !!");
                    }
                }
            } else {
                System.out.println("!! Falha ao aceitar pedido no DB. Tente novamente. !!");
            }
        } else {
            System.out.println("\n!! Pedido não encontrado !!\n");
        }
    }

    public void RecusarPedido(int id) {
        Pedido p = BuscaPedido(id);

        if (p != null) {
            // Atualiza status para 'Recusado pelo Entregador' no DB
            if (pedidoD.updateStatus(id, "Recusado pelo Entregador")) {
                // Não é necessário remover da lista em memória (comerciante.getPedidosAprovados().remove(p);)
                // O DB já garante que ele não será buscado novamente se o Comerciante.getPedidosAprovados()
                // buscar apenas status 'Aprovado'.
                System.out.println("Pedido #" + id + " recusado com sucesso! Status atualizado no DB.");
            } else {
                System.out.println("!! Falha ao recusar pedido no DB. Tente novamente. !!");
            }
        } else {
            System.out.println("\n!! Pedido não encontrado !!\n");
        }
    }

    public Pedido BuscaPedido(int id){
        return pedidoD.selectById(id);
    }
}
