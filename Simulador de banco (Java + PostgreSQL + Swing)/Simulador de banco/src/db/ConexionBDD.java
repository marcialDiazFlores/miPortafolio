package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDD implements interfazConexionBDD {
    private Connection connection;

    @Override
    public Connection conectar() {
        try {
            // Cargar el controlador JDBC de PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Establecer la conexión con la base de datos PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Banco", "postgres", "AdminBanco");
            System.out.println("Conectado a la base de datos");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
        }
    }

}