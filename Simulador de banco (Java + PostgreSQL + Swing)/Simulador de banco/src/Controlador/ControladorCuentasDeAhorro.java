package Controlador;

import Modelo.*;
import dao.*;
import java.sql.SQLException;

import java.util.List;

public class ControladorCuentasDeAhorro {
    private List<CuentaAhorro> cuentasDeAhorro;
    private CuentaAhorroDAO cuentaDeAhorroDAO;

    public ControladorCuentasDeAhorro() {
        this.cuentaDeAhorroDAO = new CuentaAhorroDAO();
        //this.cuentasDeAhorro = cuentaDeAhorroDAO.obtenerTodosLosClientes();
    }

    public void crearCuentaDeAhorro(int idCliente, int saldo, float tasaInteres, int topeMinimo) {
        try {
            CuentaAhorro cuenta = new CuentaAhorro(idCliente, saldo, tasaInteres, topeMinimo);
            //cuentasDeAhorro.add(cuenta);
            cuentaDeAhorroDAO.agregarCuentaDeAhorro(cuenta);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo agregar el cliente. Error: " + e.getMessage());
        }
    }
}