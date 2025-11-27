package com.nicollas.ifood.DAO;

import com.nicollas.ifood.DataBase.ConnectionFactory;
import com.nicollas.ifood.Modelos.ItensCarrinho;
import com.nicollas.ifood.Modelos.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItensPedidoDAO {
    // CREATE --- usado dentro de transação do PedidoDAO
    public int Create(Connection connection, int idPedido, ItensCarrinho item) throws SQLException {
        String sql = "INSERT INTO itens_pedido (id_pedido, id_produto, quantidade, preco_unitario_historico) " +
                "VALUES (?, ?, ?, ?) RETURNING id";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            stmt.setInt(2, item.getProduto().getId());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getProduto().getPreco());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }
        return -1;
    }

    // READ - busca itens pelo id do pedido
    public List<ItensCarrinho> SelectPedidoId(int idPedido) {
        String sql = "SELECT * FROM itens_pedido WHERE id_pedido = ?";
        List<ItensCarrinho> lista = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);

            try (ResultSet rs = stmt.executeQuery()) {
                ProdutoDAO produtoDAO = new ProdutoDAO();

                while (rs.next()) {
                    Produto p = produtoDAO.SelectId(rs.getInt("id_produto"));

                    ItensCarrinho item = new ItensCarrinho(
                            rs.getInt("id"),
                            p,
                            rs.getInt("quantidade")
                    );

                    lista.add(item);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens do pedido: " + e.getMessage(), e);
        }

        return lista;
    }

    // DELETE - remove todos os itens de um pedido
    public boolean DeletePedidoId(int idPedido) {
        String sql = "DELETE FROM itens_pedido WHERE id_pedido = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar itens por pedido: " + e.getMessage(), e);
        }
    }

    // DELETE - remove um item pela ID
    public boolean Delete(int id) {
        String sql = "DELETE FROM itens_pedido WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar item: " + e.getMessage(), e);
        }
    }
}