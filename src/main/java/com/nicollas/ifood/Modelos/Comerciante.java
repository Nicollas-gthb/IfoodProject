package com.nicollas.ifood.Modelos;

import java.util.List;
import com.nicollas.ifood.DAO.ProdutoDAO;
import com.nicollas.ifood.DAO.PedidoDAO;

public class Comerciante {

    private final ProdutoDAO produtoD = new ProdutoDAO();
    private final PedidoDAO pedidoD = new PedidoDAO();

    public Comerciante() {}

    public List<Produto> getCardapio(){
        return produtoD.SelectAll();
    }

    public List<Pedido> getPedido(){
        return pedidoD.selectStatus("Aprovado");
    }

    public List<Pedido> getPedidosPendentes(){
        // Usa o PedidoDAO para buscar pedidos com o status "Pendente" (ou como está na sua lógica DAO)
        return pedidoD.selectStatus("Pendente");
    }

    public void AdicionarPedido(Pedido pedido, int idCliente, int idEndereco){

        int newId = pedidoD.create(pedido, idCliente, idEndereco);

        if (newId != -1) {
            pedido.setId(newId); // <-- O ID GERADO PELO BANCO É SETADO NO OBJETO
            pedido.setStatus("Pendente"); // Garante o status no objeto
            System.out.println("\nNovo pedido adicionado com sucesso ao DB! (ID #" + newId + ")");
        } else {
            System.err.println("\nFalha ao adicionar pedido ao DB.");
        }
    }

    public void RemoverPedido(Pedido pedido){
        pedidoD.delete(pedido.getId());
    }

    public boolean FinalizarPedido(int idPedido, String novoStatus) {
        // O PedidoDAO cuidará de atualizar o status no banco
        boolean sucesso = pedidoD.updateStatus(idPedido, novoStatus);

        if(sucesso){
            System.out.println("Status do pedido #" + idPedido + " alterado para: " + novoStatus);
        } else {
            System.err.println("Falha ao atualizar status do pedido no DB.");
        }
        return sucesso;
    }

    public Pedido BuscaPedido(int id){
        return pedidoD.selectById(id);
    }

    public void AprovarPedido(int id){
        Pedido p = BuscaPedido(id);
        if(p != null){
            // Atualiza o status no BANCO
            boolean sucesso = pedidoD.updateStatus(id, "Aprovado");
            if (sucesso) {
                p.setStatus("Aprovado"); // Atualiza o objeto na memória (se necessário)
                System.out.println("Pedido #" + id + " aprovado!");
            } else {
                System.out.println("Falha ao aprovar pedido no DB.");
            }
        }else{
            System.out.println("\n!! Nenhum pedido foi encontrado !!\n");
        }
    }

    public void RecusarPedido(int id){
        Pedido p = BuscaPedido(id);
        if(p != null){
            // Atualiza o status no BANCO
            boolean sucesso = pedidoD.updateStatus(id, "Recusado");
            if (sucesso) {
                p.setStatus("Recusado"); // Atualiza o objeto na memória (se necessário)
                System.out.println("Pedido #" + id + " recusado!");
                RemoverPedido(p);
            } else {
                System.out.println("Falha ao recusar pedido no DB.");
            }
        }else{
            System.out.println("\n!! Nenhum pedido foi encontrado !!\n");
        }
    }

    public void AdicionarProduto(Produto produto){
        int newId = produtoD.Create(produto);

        if(newId != -1){
            produto.setId(newId);
            System.out.println("Item adicionado com sucesso ao DB [ID: " + newId + "]");
        }else{
            System.out.println("Falha ao adicionar produto ao DB.");
        }
    }

    public void RemoverProduto(int id){
        boolean sucesso = produtoD.Delete(id);

        if(sucesso){
            System.out.println("Item (ID #" + id + ") removido com sucesso do DB!");
        }else{
            System.out.println("Produto não encontrado ou falha ao remover no DB.");
        }
    }

    public Produto Busca(int id){
        return produtoD.SelectId(id);
    }

    public void ListarCardapio(){
        List<Produto> cardapioDB = getCardapio();

        if(cardapioDB.isEmpty()){
            System.out.println("\n!! Cardapio indisponivel !!");
        }else{
            System.out.println("---------- Menu -----------");
            for(Produto p : cardapioDB){
                // O p.getId() agora é o ID gerado pelo banco.
                System.out.printf("%d# | %s | R$ %.2f\n", p.getId(), p.getNome(), p.getPreco());
            }
            System.out.println("---------------------------");
        }
    }
}
