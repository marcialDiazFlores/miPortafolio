package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cliente;
import db.ConexionBDD;

public class ClienteDAO implements interfazClienteDAO 
{
    ConexionBDD conn = new ConexionBDD();
    Connection connection;

    public Cliente obtenerClientePorId(int id) throws SQLException {
        Cliente cliente = null;

        try (Connection connection = conn.conectar()) {
            String query = "SELECT * FROM clientes WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Se establecen los parámetros de la consulta
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Se obtienen los datos del resultado y se crea un objeto Cliente
                        String nombre = resultSet.getString("nombre");
                        String apellido = resultSet.getString("apellido");
                        String email = resultSet.getString("email");
                        String rut = resultSet.getString("rut");
                        String fono = resultSet.getString("fono");

                        cliente = new Cliente(nombre, apellido, email, rut, fono);
                        cliente.setId(id);

                        return cliente;

                    } else {
                        System.out.println("Error: No se encontró el cliente con ID " + id);
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener cliente por ID", e);
        }
    }

    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = conn.conectar()) {
            String query = "SELECT * FROM clientes";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    String email = resultSet.getString("email");
                    String rut = resultSet.getString("rut");
                    String fono = resultSet.getString("fono");

                    // Crea un objeto Cliente con los datos obtenidos de la base de datos
                    Cliente cliente = new Cliente(nombre, apellido, email, rut, fono);
                    cliente.setId(id);

                    // Agrega el cliente a la lista
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Verifica si la lista está vacía y muestra un mensaje si es así
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes en la base de datos.");
        }

        return clientes;
    }

    public void agregarCliente(Cliente cliente) throws SQLException {
        try (Connection connection = conn.conectar()) {

            String query = "INSERT INTO clientes (id, nombre, apellido, email, rut, fono) VALUES (DEFAULT, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                // Se obtienen los datos del objeto Cliente
                String nombre = cliente.getNombre();
                String apellido = cliente.getApellido();
                String email = cliente.getEmail();
                String rut = cliente.getRut();
                String fono = cliente.getFono();

                // Se establecen los parámetros de la consulta
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, apellido);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, rut);
                preparedStatement.setString(5, fono);

                /*

                System.out.println("Cliente antes de la inserción: " + cliente);
                System.out.println("Query ejecutada: " + preparedStatement.toString());

                System.out.println("Consulta SQL: " + query);

                */

                // Se ejecuta la consulta preparada
                preparedStatement.executeUpdate();

                // Obtener las claves generadas (en este caso, el ID generado)
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);

                        // Setea el ID en el objeto Cliente
                        cliente.setId(idGenerado);

                        // System.out.println("Nombre: " + cliente.getNombre() + ", ID: " + cliente.getId());
                    } else {
                        throw new SQLException("Error al obtener el ID del cliente");
                    }
                }

                // System.out.println("Inserción exitosa");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al agregar cliente", e);
        }
    }


    public void actualizarCliente(Cliente cliente, String nuevoEmail, String nuevoFono) throws SQLException {
        try (Connection connection = conn.conectar()) {
            String query = "UPDATE clientes SET email = ?, fono = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Se establecen los parámetros de la consulta
                preparedStatement.setString(1, nuevoEmail);
                preparedStatement.setString(2, nuevoFono);
                preparedStatement.setInt(3, cliente.getId());

                // Se ejecuta la consulta preparada
                int filasActualizadas = preparedStatement.executeUpdate();

                if (filasActualizadas > 0) {
                    // La actualización fue exitosa
                    cliente.setEmail(nuevoEmail);
                    cliente.setFono(nuevoFono);
                    System.out.println();
                    System.out.println("Cliente actualizado exitosamente.");
                } else {
                    System.out.println("Error: No se encontró el cliente con ID " + cliente.getId());
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar cliente", e);
        }
    }


    public void eliminarCliente(int id) throws SQLException {
        try (Connection connection = conn.conectar()) {
            String query = "DELETE FROM clientes WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Se establecen los parámetros de la consulta
                preparedStatement.setInt(1, id);

                // Se ejecuta la consulta preparada
                int filasActualizadas = preparedStatement.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Cliente eliminado exitosamente de la base de datos.");
                } else {
                    System.out.println("Error: No se encontró el cliente con ID " + id);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar al cliente de la base de datos", e);
        }
    }

    /*
    public ClienteDAO () {
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