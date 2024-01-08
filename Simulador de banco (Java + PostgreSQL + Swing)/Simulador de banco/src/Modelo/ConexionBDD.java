/*

package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ConexionBDD
{
    private static ConexionBDD conn = null;
    private Connection connection;

    private ConexionBDD() {

    }

    public static ConexionBDD getConn() {
        if (conn == null) {
            conn = new ConexionBDD();
        }
        return conn;
    }

  
    public void agregarCliente(int id, String nombre, String apellido, String email, String rut, String fono) {
        PreparedStatement preparedStatement = null;

        try {
            // Se crea una conexión con la base de datos
            conectar();

            // Se utiliza PreparedStatement para evitar problemas de inyección SQL
            String query = "INSERT INTO Clientes VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            // Se establecen los parámetros de la consulta
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, rut);
            preparedStatement.setString(6, fono);

            // Se ejecuta la consulta preparada
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void eliminarCliente(int idCliente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Se crea una conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:Banco.db");

            // Se utiliza PreparedStatement para evitar problemas de inyección SQL
            String query = "DELETE FROM Clientes WHERE id == ?";
            preparedStatement = connection.prepareStatement(query);

            // Se establecen los parámetros de la consulta
            preparedStatement.setInt(1, idCliente);

            // Se ejecuta la consulta preparada
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void agregarCuentaAhorro(int id, int idCliente, int saldo, int tasaInteres, int topeMinimo) {
        PreparedStatement preparedStatement = null;

        try {
            // Se crea una conexión con la base de datos
            conectar();

            // Se utiliza PreparedStatement para evitar problemas de inyección SQL
            String query = "INSERT INTO Cuentas_Ahorro VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            // Se establecen los parámetros de la consulta
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idCliente);
            preparedStatement.setInt(3, saldo);
            preparedStatement.setInt(4, tasaInteres);
            preparedStatement.setInt(5, topeMinimo);

            // Se ejecuta la consulta preparada
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void agregarCuentaCorriente(int id, int idCliente, int saldo, int sobregiro) {
        PreparedStatement preparedStatement = null;

        try {
            // Se crea una conexión con la base de datos
            conectar();

            // Se utiliza PreparedStatement para evitar problemas de inyección SQL
            String query = "INSERT INTO Cuentas_Corrientes VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            // Se establecen los parámetros de la consulta
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idCliente);
            preparedStatement.setInt(3, saldo);
            preparedStatement.setInt(4, sobregiro);

            // Se ejecuta la consulta preparada
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void pruebaBDD () {
        Connection connection = null;

        try {
            // Cargar el controlador JDBC de PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Establecer la conexión con la base de datos PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Demencia2099");

            // Verificar si la conexión fue exitosa
            if (connection != null) {
                System.out.println("Conexión exitosa a PostgreSQL");

                Statement statement = connection.createStatement();
                statement.execute("CREATE TABLE IF NOT EXISTS Ejemplo (id SERIAL PRIMARY KEY, nombre VARCHAR(50), edad INT)");
                statement.execute("INSERT INTO Ejemplo (nombre, edad) VALUES ('Juan', 25)");
                statement.execute("INSERT INTO Ejemplo (nombre, edad) VALUES ('Ana', 30)");

                System.out.println("Tabla 'Ejemplo' creada y registros agregados.");
            } else {
                System.out.println("Error al conectar a PostgreSQL");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }



    public static void main(String[] args) {

    }
}

*/