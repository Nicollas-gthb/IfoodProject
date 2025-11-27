package com.nicollas.ifood.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/app_delivery_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "nickplays3";

    private static Connection connection;

    public static Connection getConnection(){
        try{
            if(connection == null || connection.isClosed()){
                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                System.out.println("Conectado ao PostgreSQL com sucesso!");
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Erro ao conectar com o banco de dados!" + e.getMessage());
        }

        return connection;
    }
}