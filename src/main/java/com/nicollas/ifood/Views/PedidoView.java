package com.nicollas.ifood.Views;

import com.nicollas.ifood.Modelos.*;
import com.nicollas.ifood.DAO.EnderecoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class PedidoView {

    Scanner scan = new Scanner(System.in);
    PagamentoView pagamento = new PagamentoView();
    Carrinho carrinho = new Carrinho();
    Pedido pedido;
    Cliente cliente;
    Pagamento formaPagamento;

    private final EnderecoDAO enderecoD = new EnderecoDAO();
    private final Comerciante comerciante;
    private final ClienteView clienteView;
    private boolean pagou;

    ComercianteView cmv = new ComercianteView(this);

    public PedidoView(Comerciante c, ClienteView cv) {
        this.comerciante = c;
        this.clienteView = cv;
    }

    public void ExibirMenu() {
        comerciante.ListarCardapio();
        Escolha();
    }

    public void Escolha() {
        int option;
        escolhaLoop: while(true){
            System.out.println("\n= Opções:");
            System.out.println("1 - Adicionar item");
            System.out.println("2 - Remover item");
            System.out.println("3 - Ver carrinho");
            System.out.println("4 - Confirmar pedido");
            System.out.println("0 - Voltar");
    
            try{
                System.out.print("= Ação -> ");
                option = Integer.parseInt(scan.nextLine());
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada invalida !!\n");
                continue;
            }
            
            switch(option)
            {
                case 1: AdicionarItem(); break;
                case 2: RemoverItem(); break;
                case 3: MostrarCarrinho(); break;
                case 4: {
                    pagou = pagamento.Finalizar();
                    this.formaPagamento = pagamento.getPagamento();
                    ConfirmarPedido();
                    break;
                }
                case 0: break escolhaLoop;
                default: System.out.println("!! Opção inválida !!");
            }
        }
    }

    public void AdicionarItem() {
        int codigoItem;
        int quantidade;
        while(true){
            try{
                System.out.print("= Digite o codigo do item para Adicionar (0 para encerrar) -> ");
                codigoItem = Integer.parseInt(scan.nextLine());

                if(codigoItem == 0) return;

                System.out.print("Quantidade -> ");
                quantidade = Integer.parseInt(scan.nextLine());

                Produto item = comerciante.Busca(codigoItem);
                if(item != null){
                    carrinho.Adicionar(item, quantidade);
                }else{
                    System.out.println("! Item invalido !");
                }
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada inválida, digite novamente.. !!\n");
            }
        }
    }

    public void RemoverItem() {
        while(true){
            try{
                System.out.print("Digite o codigo do item para Remover (0 para encerrar) -> ");
                int codigoItem = Integer.parseInt(scan.nextLine());
                
                if(codigoItem == 0) return;
        
                System.out.print("Quantidade (0 para tirar do carrinho) -> ");
                int quantidade = Integer.parseInt(scan.nextLine());
        
                Produto item = comerciante.Busca(codigoItem);
                if(item != null){
                    if(quantidade <= 0){
                        carrinho.Remover(item);
                    }else{
                        carrinho.RemoverQuantidade(item, quantidade);
                    }
                }else{
                    System.out.println("! Item invalido !");
                }
            }catch(NumberFormatException e){
                System.out.println("\n!! Entrada inválida, digite novamente.. !!\n");
            }
        }
    }

    public void MostrarCarrinho()
    {
        carrinho.Mostrar();
    }

    public void ConfirmarPedido(){
        if(!pagou){
            System.out.println("\n ! Pagamento negado, pedido não gerado !");
            return;
        }

        Map<Produto, Integer> mapa = carrinho.getCarrinho();
        List<ItensCarrinho> itensLista = new ArrayList<>();
        for (Entry<Produto,Integer> e : mapa.entrySet()) {
            itensLista.add(new ItensCarrinho(e.getKey(), e.getValue()));
        }

        Endereco enderecoCliente = clienteView.endereco;
        Endereco enderecoPedido = new Endereco(
                enderecoCliente.getRua(),
                enderecoCliente.getNumero(),
                enderecoCliente.getCidade(),
                enderecoCliente.getBairro(),
                enderecoCliente.getCep(),
                enderecoCliente.getComplemento()
        );

        int idEndereco = enderecoD.Create(enderecoPedido);
        if (idEndereco == -1) {
            System.out.println("Falha ao salvar endereço do pedido. Cancelando.");
            return;
        }
        enderecoPedido.setId(idEndereco); // Define o ID gerado no objeto Endereco do Pedido

        // 4. Obtém o ID do Cliente logado
        int idCliente = clienteView.getIdCliente();
        if (idCliente == -1) {
            System.out.println("Erro: Cliente não está logado/cadastrado. Cancelando pedido.");
            return;
        }

        pedido = new Pedido(
            itensLista,
            carrinho.Total(),
            clienteView.getNomeCliente(),
            formaPagamento.getForma(),
            enderecoPedido
        );

        comerciante.AdicionarPedido(pedido, idCliente, idEndereco);
        System.out.println("Pedido enviado ao comerciante!\n");
        carrinho.Limpar();
    }
}