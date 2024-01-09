package dao;

import java.sql.SQLException;
import java.util.List;

import Modelo.CuentaCorriente;

public interface interfazCuentaCorrienteDAO {
    CuentaCorriente obtenerCuentaCorrientePorId(int id) throws SQLException;
    List<CuentaCorriente> obtenerCuentasCorrientes() throws SQLException;
    void agregarCuentaCorriente(CuentaCorriente cuenta) throws SQLException;
    void actualizarCuentaCorriente(CuentaCorriente cuenta) throws SQLException;
    void eliminarCuentaCorriente(int id) throws SQLException;
}
