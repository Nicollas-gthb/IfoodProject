package com.nicollas.ifood.DAO;

import com.nicollas.ifood.DataBase.ConnectionFactory;
import com.nicollas.ifood.Modelos.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // CREATE
    public int Create(Produto p) {
        String sql = "INSERT INTO produto (nome, preco) VALUES (?, ?) RETURNING id";

        Connection conn = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
            return -1;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar produto: " + e.getMessage(), e);
        }
    }

    // READ ID
    public Produto SelectId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";

        Connection conn = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Produto(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDouble("preco")
                    );
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto: " + e.getMessage(), e);
        }
    }

    // READ ALL
    public List<Produto> SelectAll() {
        String sql = "SELECT * FROM produto";
        List<Produto> lista = new ArrayList<>();

        Connection conn = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco")
                ));
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage(), e);
        }
    }

    // UPDATE
    public boolean Update(Produto p) {
        String sql = "UPDATE produto SET nome = ?, preco = ? WHERE id = ?";

        Connection conn = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getId());

            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        }
    }

    // DELETE
    public boolean Delete(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";

        Connection conn = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto: " + e.getMessage(), e);
        }
    }
}