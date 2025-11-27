package com.nicollas.ifood.DAO;

import com.nicollas.ifood.DataBase.ConnectionFactory;
import com.nicollas.ifood.Modelos.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    // CREATE
    public int Create(Endereco e) {
        String sql = "INSERT INTO endereco (rua, numero, cidade, bairro, cep, complemento) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, e.getRua());
            stmt.setString(2, e.getNumero());
            stmt.setString(3, e.getCidade());
            stmt.setString(4, e.getBairro());
            stmt.setString(5, e.getCep());
            stmt.setString(6, e.getComplemento());

            try (ResultSet rs = stmt.executeQuery()) { // <--- Execute a query AGORA
                if (rs.next()) return rs.getInt("id");
            }
            return -1;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao criar endereco: " + ex.getMessage(), ex);
        }
    }

    // READ
    public Endereco SelectId(int id) {
        String sql = "SELECT * FROM endereco WHERE id = ?";
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    return new Endereco(
                            rs.getInt("id"),
                            rs.getString("rua"),
                            rs.getString("numero"),
                            rs.getString("cidade"),
                            rs.getString("bairro"),
                            rs.getString("cep"),
                            rs.getString("complemento")
                    );
                }
            }
                return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar endereco: " + e.getMessage(), e);
        }
    }

    // READ
    public List<Endereco> SelectAll() {
        String sql = "SELECT * FROM endereco";
        List<Endereco> lista = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                lista.add(new Endereco(
                        rs.getInt("id"),
                        rs.getString("rua"),
                        rs.getString("numero"),
                        rs.getString("cidade"),
                        rs.getString("bairro"),
                        rs.getString("cep"),
                        rs.getString("complemento")
                ));
            }
            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar enderecos: " + e.getMessage(), e);
        }
    }

    // UPDATE
    public boolean Update(Endereco e) {
        String sql = "UPDATE endereco SET rua = ?, numero = ?, cidade = ?, bairro = ?, cep = ?, complemento = ? WHERE id = ?";
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, e.getRua());
            stmt.setString(2, e.getNumero());
            stmt.setString(3, e.getCidade());
            stmt.setString(4, e.getBairro());
            stmt.setString(5, e.getCep());
            stmt.setString(6, e.getComplemento());
            stmt.setInt(7, e.getId());

            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar endereco: " + ex.getMessage(), ex);
        }
    }

    // DELETE
    public boolean Delete(int id) {
        String sql = "DELETE FROM endereco WHERE id = ?";
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar endereco: " + ex.getMessage(), ex);
        }
    }
}
