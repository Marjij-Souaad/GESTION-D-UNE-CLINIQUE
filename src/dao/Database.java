package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3308/gestion_clinique?serverTimezone=UTC";
    private static final String USER = "root";
private static final String PASSWORD = "root";
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver"); // IMPORTANT

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion réussie à MySQL !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL introuvable !");
        }
        return connection;
    }
}