package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDD implements interfazConexionBDD {
    private Connection connection;

    @Override
    public Connection conectar() throws SQLException {
        try {
            // Cargar el controlador JDBC de PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Establecer la conexión con la base de datos PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Banco", "postgres", "AdminBanco");
            // System.out.println("Conectado a la base de datos");
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el controlador JDBC", e);
        }
    }/*

    public void crearTablaClientes () {
        try (Connection connection = conn.conectar()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Clientes (" +
                    "ID SERIAL PRIMARY KEY," +
                    "Nombre VARCHAR(255) NOT NULL," +
                    "Apellido VARCHAR(255) NOT NULL," +
                    "Email VARCHAR(255) NOT NULL," +
                    "RUT VARCHAR(20) NOT NULL," +
                    "Fono VARCHAR(20) NOT NULL)";

            PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery);
            createTableStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("No se pudo crear la tabla Cliente");
            e.printStackTrace();
        }
    }
    */

}