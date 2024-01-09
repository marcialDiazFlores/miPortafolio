package dao;

import java.sql.SQLException;
import java.util.List;

import Modelo.CuentaAhorro;

public interface interfazCuentaAhorroDAO {
    //CuentaAhorro obtenerCuentaDeAhorroPorId(int id) throws SQLException;
    //List<CuentaAhorro> obtenerCuentasDeAhorro() throws SQLException;
    void agregarCuentaDeAhorro(CuentaAhorro cuenta) throws SQLException;
    //void eliminarCuentaDeAhorro(int id) throws SQLException;
}
