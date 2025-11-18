package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/app_delivery_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "nickplays3";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver do PostgreSQL não encontrado!");
        }
    }

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o PostgreSQL: " + e.getMessage(), e);
        }
    }

    public static void testarConexao() {
        try (Connection c = getConexao()) {
            System.out.println("Conexão com o PostgreSQL funcionando.");
        } catch (Exception e) {
            System.err.println("Falha ao testar a conexão.");
        }
    }
}
