package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cliente;
import Modelo.CuentaAhorro;
import db.ConexionBDD;

public class CuentaAhorroDAO implements interfazCuentaAhorroDAO {
    ConexionBDD conn = new ConexionBDD();

    @Override
    public void agregarCuentaDeAhorro(CuentaAhorro cuenta) throws SQLException {
        try (Connection connection = conn.conectar()) {

            String query = "INSERT INTO cuentas_ahorro (id, cliente_id, saldo, tasa_interes, tope_minimo) VALUES (DEFAULT, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                // Se obtienen los datos del objeto CuentaAhorro
                int cliente_id = cuenta.getIdCliente();
                int saldo = cuenta.getSaldo();
                float tasa_interes = cuenta.getTasaInteres();
                int tope_minimo = cuenta.getTopeMinimo();

                // Se establecen los parámetros de la consulta
                preparedStatement.setInt(1, cliente_id);
                preparedStatement.setInt(2, saldo);
                preparedStatement.setFloat(3, tasa_interes);
                preparedStatement.setInt(4, tope_minimo);

                // Se ejecuta la consulta preparada
                preparedStatement.executeUpdate();

                // Obtener las claves generadas (en este caso, el ID generado)
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);

                        // Setea el ID en el objeto Cliente
                        cuenta.setId(idGenerado);

                        System.out.println("Inserción exitosa");

                        System.out.println("ID: " + cuenta.getId());
                    } else {
                        throw new SQLException("Error al obtener el ID de la cuenta de ahorro");
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al agregar cuenta de ahorro", e);
        }
    }
}