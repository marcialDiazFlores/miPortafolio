package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

import Modelo.Cliente;
import db.ConexionBDD;

public class ClienteDAO implements interfazClienteDAO 
{
    ConexionBDD conn = new ConexionBDD();
    Connection connection;

    /*
    public Cliente obtenerClientePorId(int id) {

    }

    public List<Cliente> obtenerTodosLosClientes() {

    }

    */

    public void agregarCliente(Cliente cliente) {
        PreparedStatement preparedStatement = null;

        try {
            // Se crea una conexión con la base de datos
            connection = conn.conectar();

            // Se utiliza PreparedStatement para evitar problemas de inyección SQL
            String query = "INSERT INTO Clientes VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            // Se obtienen los datos del objeto Cliente

            int id = cliente.getId();
            String nombre = cliente.getNombre();
            String apellido = cliente.getApellido();
            String email = cliente.getEmail();
            String rut = cliente.getRut();
            String fono = cliente.getFono();

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
                conn.desconectar();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void actualizarCliente(Cliente cliente) {

    }

    public void eliminarCliente(int id) {

    }
    
}