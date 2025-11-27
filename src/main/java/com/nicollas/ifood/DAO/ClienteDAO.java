package com.nicollas.ifood.DAO;

import com.nicollas.ifood.DataBase.ConnectionFactory;
import com.nicollas.ifood.Modelos.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    // CREATE ---------------------------------
    public int Create(Cliente c) {
        String sql = "INSERT INTO cliente (nome, email, senha, telefone, data_nasc) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING id";

        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getSenha());
            stmt.setString(4, c.getTelefone());
            stmt.setString(5, c.getDataNasc());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return rs.getInt("id");
            }

            return -1;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar cliente: " + e.getMessage(), e);
        }
    }

    // READ - SELECT POR EMAIL --------------------
    public Cliente SelectEmail(String email) {
        String sql = "SELECT *, id_endereco_principal FROM cliente WHERE email = ?";

        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("telefone"),
                            rs.getString("data_nasc"),
                            rs.getInt("id_endereco_principal")
                    );
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente pelo email: " + e.getMessage(), e);
        }
    }

    // READ - SELECT POR ID -----------------------
    public Cliente SelectId(int id) {
        String sql = "SELECT *, id_endereco_principal FROM cliente WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("telefone"),
                            rs.getString("data_nasc"),
                            rs.getInt("id_endereco_principal")
                    );
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por id: " + e.getMessage(), e);
        }
    }

    // READ - SELECT ALL --------------------------
    public List<Cliente> SelectAll() {
        String sql = "SELECT * FROM cliente";

        List<Cliente> lista = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        rs.getString("data_nasc"),
                        rs.getInt("id_pedido_principal")
                ));
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
        }
    }

    // UPDATE ----------------------------------------
    public boolean Update(Cliente c) {
        String sql = "UPDATE cliente SET nome = ?, email = ?, senha = ?, telefone = ?, data_nasc = ?, id_endereco_principal = ? WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getSenha());
            stmt.setString(4, c.getTelefone());
            stmt.setString(5, c.getDataNasc());
            stmt.setInt(6, c.getIdEnderecoPrincipal());
            stmt.setInt(7, c.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }

    // DELETE -----------------------------------------
    public boolean Delete(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente: " + e.getMessage(), e);
        }
    }
}