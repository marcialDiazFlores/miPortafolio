package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ConexionBDD
{
    public ConexionBDD () {

    }

    public void reiniciarBDD () {
        Connection connection = null;

        try
        {
            // Se crea una conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:Banco.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // Se ajusta un timeout de 30 segundos

            statement.executeUpdate("drop table if exists Clientes");
            statement.executeUpdate("drop table if exists Cuentas_Ahorro");
            statement.executeUpdate("drop table if exists Cuentas_Corrientes");

            statement.executeUpdate("create table if not exists Clientes (id integer, Nombre text, Apellido text, Email text, RUT text, Fono integer)");
            statement.executeUpdate("create table if not exists Cuentas_Ahorro (id integer, idCliente integer, saldoInicial integer, tipo text, tasaInteres integer, topeMinimo integer)");
            statement.executeUpdate("create table if not exists Cuentas_Corrientes (id integer, idCliente integer, saldo integer, tipo text, sobregiro integer)");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
    public void agregarCliente(int id, String nombre, String apellido, String email, String rut, int fono) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Se crea una conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:Banco.db");

            // Se utiliza PreparedStatement para evitar problemas de inyección SQL
            String query = "INSERT INTO Clientes VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            // Se establecen los parámetros de la consulta
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, rut);
            preparedStatement.setInt(6, fono);

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

    public void agregarCuentaAhorro(int id, int idCliente, int saldoInicial, String tipo, int tasaInteres, int topeMinimo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Se crea una conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:Banco.db");

            // Se utiliza PreparedStatement para evitar problemas de inyección SQL
            String query = "INSERT INTO Cuentas_Ahorro VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            tipo = (tipo.equals("a")) ? "Ahorro" : "Corriente";

            // Se establecen los parámetros de la consulta
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idCliente);
            preparedStatement.setInt(3, saldoInicial);
            preparedStatement.setString(4, tipo);
            preparedStatement.setInt(5, tasaInteres);
            preparedStatement.setInt(6, topeMinimo);

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

    public void agregarCuentaCorriente(int id, int idCliente, int saldo, String tipo, int sobregiro) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Se crea una conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:Banco.db");

            // Se utiliza PreparedStatement para evitar problemas de inyección SQL
            String query = "INSERT INTO Cuentas_Corrientes VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            tipo = (tipo.equals("a")) ? "Ahorro" : "Corriente";

            // Se establecen los parámetros de la consulta
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idCliente);
            preparedStatement.setInt(3, saldo);
            preparedStatement.setString(4, tipo);
            preparedStatement.setInt(5, sobregiro);

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
    public static void main(String[] args)
    {
        Connection connection = null;
        try
        {
            // Se crea una conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:Banco.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // Se ajusta un timeout de 30 segundos

            statement.executeUpdate("drop table if exists Cliente");
            statement.executeUpdate("create table Cliente (ID integer, Nombre text, Apellido text)");
            statement.executeUpdate("insert into Cliente values(1, 'Marcial', 'Díaz')");
            statement.executeUpdate("insert into Cliente values(2, 'Valentina', 'Díaz')");
            ResultSet rs = statement.executeQuery("select * from Cliente");
            while(rs.next())
            {
                // Mostrar el set de resultados
                System.out.println("ID = " + rs.getInt("ID"));
                System.out.println("Nombre = " + rs.getString("Nombre"));
                System.out.println("Apellido = " + rs.getString("Apellido"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}