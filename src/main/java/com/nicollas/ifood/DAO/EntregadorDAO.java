package com.nicollas.ifood.DAO;

import com.nicollas.ifood.DataBase.ConnectionFactory;
import com.nicollas.ifood.Modelos.Entregador;
import com.nicollas.ifood.Modelos.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregadorDAO {

    // CREATE
    public int create(Entregador e) {
        String sql = "INSERT INTO entregador (nome, telefone, data_nasc, email, senha, id_veiculo) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getTelefone());
            stmt.setString(3, e.getDataNasc());
            stmt.setString(4, e.getEmail());
            stmt.setString(5, e.getSenha());
            stmt.setInt(6, e.getIdVeiculo());

            try (ResultSet rs = stmt.executeQuery()) { // <--- Execute a query AGORA
                if (rs.next()) return rs.getInt("id");
            }

            return -1;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao criar entregador: " + ex.getMessage(), ex);
        }
    }

    // READ por ID
    public Entregador selectId(int id) {
        String sql = "SELECT * FROM entregador WHERE id = ?";
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int idVeiculo = rs.getInt("id_veiculo");

                // Carrega Veiculo
                Veiculo veiculo = null;
                if (idVeiculo > 0) {
                    veiculo = new VeiculoDAO().SelectId(idVeiculo);
                }

                return new Entregador(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("data_nasc"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        veiculo
                );
            }

            return null;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar entregador: " + ex.getMessage(), ex);
        }
    }

    // READ por EMAIL
    public Entregador selectEmail(String email) {
        String sql = "SELECT * FROM entregador WHERE email = ?";
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Retorna um novo objeto Entregador com todos os dados do banco
                    return new Entregador(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("telefone"),
                            rs.getString("data_nasc"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getInt("id_veiculo") // idVeiculo
                    );
                }
            }

            return null;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar entregador por email: " + ex.getMessage(), ex);
        }
    }

    // READ ALL
    public List<Entregador> selectAll() {
        String sql = "SELECT * FROM entregador";
        List<Entregador> lista = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                int idVeiculo = rs.getInt("id_veiculo");
                Veiculo veiculo = null;

                if (idVeiculo > 0) {
                    veiculo = new VeiculoDAO().SelectId(idVeiculo);
                }

                Entregador ent = new Entregador(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("data_nasc"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        veiculo
                );

                lista.add(ent);
            }

            return lista;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar entregadores: " + ex.getMessage(), ex);
        }
    }

    // UPDATE
    public boolean update(Entregador e) {
        String sql = "UPDATE entregador SET nome = ?, telefone = ?, data_nasc = ?, email = ?, senha = ?, id_veiculo = ? " +
                "WHERE id = ?";
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getTelefone());
            stmt.setString(3, e.getDataNasc());
            stmt.setString(4, e.getEmail());
            stmt.setString(5, e.getSenha());
            stmt.setInt(6, e.getIdVeiculo());
            stmt.setInt(7, e.getId());

            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar entregador: " + ex.getMessage(), ex);
        }
    }

    // DELETE
    public boolean delete(int id) {
        String sql = "DELETE FROM entregador WHERE id = ?";
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar entregador: " + ex.getMessage(), ex);
        }
    }
}