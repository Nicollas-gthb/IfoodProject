package com.nicollas.ifood.DAO;

import com.nicollas.ifood.DataBase.ConnectionFactory;
import com.nicollas.ifood.Modelos.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // CREATE
    public int create(Pedido p, int idCliente, int idEndereco) {

        String sql = "INSERT INTO pedido (id_cliente, id_endereco, status, valor_total, forma_pagamento) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING id";

        Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            int idPedido;

            // INSERIR PEDIDO
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, idCliente);
                stmt.setInt(2, idEndereco);
                stmt.setString(3, p.getStatus() != null ? p.getStatus() : "Pendente");
                stmt.setDouble(4, p.getValorTotal());
                stmt.setString(5, p.getFormaPagamento());

                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        conn.rollback();
                        throw new RuntimeException("Falha ao inserir pedido.");
                    }
                    idPedido = rs.getInt("id");
                }
            }

            // INSERIR ITENS
            ItensPedidoDAO itensDAO = new ItensPedidoDAO();

            for (ItensCarrinho item : p.getItensCarrinho()) {
                itensDAO.Create(conn, idPedido, item);
            }

            conn.commit();
            return idPedido;

        } catch (SQLException ex) {

            try { if (conn != null) conn.rollback(); } catch (Exception ignore) {}

            throw new RuntimeException("Erro ao criar pedido: " + ex.getMessage(), ex);

        } finally {

            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException ignore) {}
        }
    }

    // SELECT BY ID
    public Pedido selectById(int id) {

        String sql = "SELECT p.id, p.status, p.valor_total, p.forma_pagamento, p.id_endereco, " +
                "c.nome AS cliente_nome " +
                "FROM pedido p " +
                "JOIN cliente c ON c.id = p.id_cliente " +
                "WHERE p.id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (!rs.next()) return null;

                // Endereco
                Endereco endereco = new EnderecoDAO().SelectId(rs.getInt("id_endereco"));

                // Itens
                ItensPedidoDAO itensDAO = new ItensPedidoDAO();
                List<ItensCarrinho> itens = itensDAO.SelectPedidoId(id);

                // Monta objeto
                Pedido p = new Pedido(
                        rs.getInt("id"),
                        itens,
                        rs.getDouble("valor_total"),
                        rs.getString("cliente_nome"),
                        rs.getString("forma_pagamento"),
                        endereco,
                        rs.getString("status")
                );

                return p;
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar pedido: " + ex.getMessage(), ex);
        }
    }

    // SELECT ALL BY STATUS
    public List<Pedido> selectStatus(String status) {

        // A query agora filtra os resultados pela coluna 'status'
        String sql = "SELECT p.id, p.status, p.valor_total, p.forma_pagamento, p.id_endereco, " +
                "c.nome AS cliente_nome " +
                "FROM pedido p " +
                "JOIN cliente c ON c.id = p.id_cliente " +
                "WHERE p.status = ? " +
                "ORDER BY p.id DESC";

        List<Pedido> lista = new ArrayList<>();

        // Usando try-with-resources para garantir o fechamento de Connection e PreparedStatement
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define o parâmetro do status na consulta
            stmt.setString(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    // 1. Busca Endereco
                    Endereco endereco = new EnderecoDAO().SelectId(rs.getInt("id_endereco"));

                    // 2. Busca Itens do Pedido
                    ItensPedidoDAO itensDAO = new ItensPedidoDAO();
                    List<ItensCarrinho> itens = itensDAO.SelectPedidoId(rs.getInt("id"));

                    // 3. Monta o objeto Pedido
                    Pedido p = new Pedido(
                            rs.getInt("id"),
                            itens,
                            rs.getDouble("valor_total"),
                            rs.getString("cliente_nome"),
                            rs.getString("forma_pagamento"),
                            endereco,
                            rs.getString("status")
                    );

                    lista.add(p);
                }
            }
            return lista;

        } catch (SQLException ex) {
            // Em caso de erro, lança a exceção com uma mensagem clara
            throw new RuntimeException("Erro ao listar pedidos com status '" + status + "': " + ex.getMessage(), ex);
        }
    }

    // SELECT ALL
    public List<Pedido> selectAll() {

        String sql = "SELECT p.id, p.status, p.valor_total, p.forma_pagamento, p.id_endereco, " +
                "c.nome AS cliente_nome " +
                "FROM pedido p " +
                "JOIN cliente c ON c.id = p.id_cliente " +
                "ORDER BY p.id DESC";

        List<Pedido> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Endereco endereco = new EnderecoDAO().SelectId(rs.getInt("id_endereco"));

                ItensPedidoDAO itensDAO = new ItensPedidoDAO();
                List<ItensCarrinho> itens = itensDAO.SelectPedidoId(rs.getInt("id"));

                Pedido p = new Pedido(
                        rs.getInt("id"),
                        itens,
                        rs.getDouble("valor_total"),
                        rs.getString("cliente_nome"),
                        rs.getString("forma_pagamento"),
                        endereco,
                        rs.getString("status")
                );

                lista.add(p);
            }

            return lista;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar pedidos: " + ex.getMessage(), ex);
        }
    }

    // UPDATE (COM TRANSAÇÃO)
    public boolean update(Pedido p, int idCliente, int idEndereco) {

        String sqlUpdate = "UPDATE pedido SET id_cliente = ?, id_endereco = ?, status = ?, valor_total = ?, forma_pagamento = ? " +
                "WHERE id = ?";

        Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            // Atualiza o pedido
            try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {

                stmt.setInt(1, idCliente);
                stmt.setInt(2, idEndereco);
                stmt.setString(3, p.getStatus());
                stmt.setDouble(4, p.getValorTotal());
                stmt.setString(5, p.getFormaPagamento());
                stmt.setInt(6, p.getId());

                stmt.executeUpdate();
            }

            // Remove itens antigos
            ItensPedidoDAO itensDAO = new ItensPedidoDAO();
            itensDAO.DeletePedidoId(p.getId());

            // Reinsere itens
            for (ItensCarrinho item : p.getItensCarrinho()) {
                itensDAO.Create(conn, p.getId(), item);
            }

            conn.commit();
            return true;

        } catch (SQLException ex) {

            try { if (conn != null) conn.rollback(); } catch (Exception ignore) {}

            throw new RuntimeException("Erro ao atualizar pedido: " + ex.getMessage(), ex);

        } finally {

            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException ignore) {}
        }
    }

    // DELETE (COM TRANSAÇÃO)
    public boolean delete(int id) {

        String sqlDelete = "DELETE FROM pedido WHERE id = ?";

        Connection conn = null;

        try {

            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            // Deleta itens
            new ItensPedidoDAO().DeletePedidoId(id);

            // Deleta pedido
            try (PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {
                stmt.setInt(1, id);
                int affected = stmt.executeUpdate();
                conn.commit();
                return affected > 0;
            }

        } catch (SQLException ex) {

            try { if (conn != null) conn.rollback(); } catch (Exception ignore) {}

            throw new RuntimeException("Erro ao deletar pedido: " + ex.getMessage(), ex);

        } finally {

            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException ignore) {}
        }
    }

    // UPDATE APENAS STATUS
    public boolean updateStatus(int id, String novoStatus) {

        String sql = "UPDATE pedido SET status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar status: " + ex.getMessage(), ex);
        }
    }
}