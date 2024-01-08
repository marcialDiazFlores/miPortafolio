package dao;

import java.util.List;

import Modelo.CuentaAhorro;

public interface interfazCuentaAhorroDAO {
    CuentaAhorro obtenerCuentaAhorroPorId(int id);
    List<CuentaAhorro> obtenerCuentasDeAhorro();
    void agregarCuentaAhorro(CuentaAhorro cuenta);
    void actualizarCuentaAhorro(CuentaAhorro cuenta);
    void eliminarCuentaAhorro(int id);
}
