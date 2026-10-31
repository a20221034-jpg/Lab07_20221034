package pe.edu.pucp.iweb.lab7.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Ejercicio 2a: DAO base */

public abstract class BaseDao {

    private static final String URL = "jdbc:mysql://localhost:3306/telecom_andina";
    private static final String USER = "root";
    private static final String PASS = "root";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de MySQL", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
