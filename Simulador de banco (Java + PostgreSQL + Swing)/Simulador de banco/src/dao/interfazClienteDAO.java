package dao;

import java.util.List;

import Modelo.Cliente;

public interface interfazClienteDAO {
    /*
    Cliente obtenerClientePorId(int id);
    List<Cliente> obtenerTodosLosClientes();
    */
    void agregarCliente(Cliente cliente);
    void actualizarCliente(Cliente cliente);
    void eliminarCliente(int id);
}