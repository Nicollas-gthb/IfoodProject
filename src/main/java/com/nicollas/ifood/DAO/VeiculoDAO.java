package com.nicollas.ifood.DAO;

import com.nicollas.ifood.DataBase.ConnectionFactory;
import com.nicollas.ifood.Modelos.Veiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    // CREATE ------------------------------------------------------
    public int Create(Veiculo v) {
        String sql = "INSERT INTO veiculo (placa, ano, modelo) VALUES (?, ?, ?) RETURNING id";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getAno());
            stmt.setString(3, v.getModelo());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return rs.getInt("id");
            }

            return -1;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar veiculo: " + e.getMessage(), e);
        }
    }


    // READ - SELECT ID ------------------------------------------------------
    public Veiculo SelectId(int id) {
        String sql = "SELECT * FROM veiculo WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Veiculo(
                            rs.getInt("id"),
                            rs.getString("placa"),
                            rs.getString("ano"),
                            rs.getString("modelo")
                    );
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veiculo: " + e.getMessage(), e);
        }
    }


    // READ - SELECT ALL ------------------------------------------------------
    public List<Veiculo> SelectAll() {
        String sql = "SELECT * FROM veiculo";
        List<Veiculo> lista = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Veiculo(
                        rs.getInt("id"),
                        rs.getString("placa"),
                        rs.getString("ano"),
                        rs.getString("modelo")
                ));
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veiculos: " + e.getMessage(), e);
        }
    }


    // UPDATE --------------------------------------------------------------
    public boolean Update(Veiculo v) {
        String sql = "UPDATE veiculo SET placa = ?, ano = ?, modelo = ? WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getAno());
            stmt.setString(3, v.getModelo());
            stmt.setInt(4, v.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veiculo: " + e.getMessage(), e);
        }
    }

    // DELETE --------------------------------------------------------------
    public boolean Delete(int id) {
        String sql = "DELETE FROM veiculo WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar veiculo: " + e.getMessage(), e);
        }
    }
}