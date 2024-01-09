package Controlador;

import Modelo.*;
import dao.*;
import java.sql.SQLException;

import java.util.List;

public class ControladorClientes {
    private List<Cliente> clientes;
    private ClienteDAO clienteDAO;

    public ControladorClientes() {
        this.clienteDAO = new ClienteDAO();
        this.clientes = clienteDAO.obtenerTodosLosClientes();
    }

    public void crearCliente(String nombre, String apellido, String email, String rut, String fono) {
        try {
            Cliente cliente = new Cliente(nombre, apellido, email, rut, fono);
            clientes.add(cliente);
            clienteDAO.agregarCliente(cliente);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo agregar el cliente. Error: " + e.getMessage());
        }
    }

    public void actualizarCliente(String rut, String email, String fono) {
        try {
            Cliente cliente = encontrarClientePorRUT(rut);
            /*
            cliente.setEmail(email);
            cliente.setFono(fono);
            */
            clienteDAO.actualizarCliente(cliente, email, fono);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudieron actualizar los datos del cliente. Error: " + e.getMessage());
        }
    }

    public void eliminarCliente(String rut) {
        try {
            Cliente cliente = encontrarClientePorRUT(rut);
            clientes.remove(cliente);
            clienteDAO.eliminarCliente(cliente.getId());
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo eliminar al cliente de la base de datos. Error: " + e.getMessage());
        }
    }

    public String buscarCliente(String rut) {
        try {
            Cliente cliente = encontrarClientePorRUT(rut);
            Cliente res = clienteDAO.obtenerClientePorId(cliente.getId());
            if (res != null) {
                return res.toString();
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo encontrar al cliente en la base de datos. Error: " + e.getMessage());
            return null;
        }
    }

    public Cliente encontrarClientePorRUT(String rut) {
        for (Cliente cliente : clientes) {
            if (cliente.getRut().equals(rut)) {
                return cliente;
            }
        }
        return null;
    }

    public void mostrarDetallesCliente(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente);
        String cuentaAhorro = hayCuentaAhorro(seleccionCliente) ? "Sí" : "No";
        String cuentaCorriente = hayCuentaCorriente(seleccionCliente) ? "Sí" : "No";

        // Mostrar detalles del cliente
        System.out.print("ID: " + clienteSeleccionado.getId() + " | ");
        System.out.print("Nombre: " + clienteSeleccionado.getNombre() + " | ");
        System.out.print("Apellido: " + clienteSeleccionado.getApellido() + " | ");
        System.out.print("Rut: " + clienteSeleccionado.getRut() + " | ");
        System.out.print("Email: " + clienteSeleccionado.getEmail() + " | ");
        System.out.println("Teléfono: " + clienteSeleccionado.getFono());
        System.out.print(" | ¿Tiene cuenta de ahorro? " + cuentaAhorro + " | ");
        System.out.println("¿Tiene cuenta corriente? " + cuentaCorriente);
    }

    public void mostrarDetallesClientes(){
        if (!hayClientes()){
            System.out.println("No hay clientes registrados en este momento");
        }
        else {
            int cantClientes = this.getCantidadClientes();
            for (int i = 0; i < cantClientes; i++) {
                System.out.print("- ");
                mostrarDetallesCliente(i);
                System.out.println();
            }
        }
    }

    public boolean hayCuentaAhorro(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente);
        if (clienteSeleccionado.getCuentaAhorro() != null){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hayCuentaAhorro(String rut){
        Cliente clienteSeleccionado = encontrarClientePorRUT(rut);
        if (clienteSeleccionado.getCuentaAhorro() != null){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hayCuentaCorriente(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente);
        if (clienteSeleccionado.getCuentaCorriente() != null){
            return true;
        }
        else {
            return false;
        }
    }
    public int getCantidadClientes() {
        if (clientes != null){
            return clientes.size();
        }
        else {
            return 0;
        }
    }

    public boolean hayClientes() {
        return !clientes.isEmpty();
    }

    /*

    public void crearCuentaAhorro(int idCliente, int saldoInicial, String tipo, int tasaInteres, int topeMinimo) {
        Cliente cliente = encontrarClientePorId(idCliente);
        if (cliente != null) {
            CuentaAhorro cuenta = new CuentaAhorro(idCliente, saldoInicial, tipo, tasaInteres, topeMinimo);
            cliente.agregarCuenta(cuenta);
            //conn.agregarCuentaAhorro(cuenta.getId(), idCliente, saldoInicial, tasaInteres, topeMinimo);
        } else {
            System.out.println("Error: Cliente no encontrado al crear cuenta de ahorro.");
        }
    }

    public int getIdCliente(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        return clienteSeleccionado.getId();
    }

    public void crearCuentaCorriente(int idCliente, int saldo, String tipo, int sobregiro) {
        Cliente cliente = encontrarClientePorId(idCliente);
        if (cliente != null) {
            CuentaCorriente cuenta = new CuentaCorriente(idCliente, saldo, tipo, sobregiro);
            cliente.agregarCuenta(cuenta);
            //conn.agregarCuentaCorriente(cuenta.getId(), idCliente, saldo, sobregiro);
        } else {
            System.out.println("Error: Cliente no encontrado al crear cuenta corriente.");
        }
    }

    */

    /*

    public void eliminarCliente(int idCliente) {
        Cliente cliente = encontrarClientePorId(idCliente);
        if (cliente != null) {
            clientes.remove(cliente);
            //conn.eliminarCliente(idCliente);
            System.out.println("Cliente eliminado con éxito.");
        } else {
            System.out.println("Error: Cliente no encontrado.");
        }
    }

    public void eliminarCuenta(CuentaBancaria cuenta) {
        for (Cliente cliente : clientes) {
            if (cliente.getCuentas().contains(cuenta)) {
                cliente.eliminarCuenta(cuenta);
                System.out.println("Cuenta eliminada con éxito.");
                return;
            }
        }
        System.out.println("Error: Cuenta no encontrada.");
    }

    public Cliente encontrarClientePorId(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    public List<CuentaBancaria> getCuentasCliente(Cliente cliente){
        if(cliente.getCuentas() != null){
            return cliente.getCuentas();
        }
        else {
            System.out.println("El cliente no tiene cuentas asociadas para mostrar");
            return null;
        }
    }


    public boolean hayCuentas(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        return !clienteSeleccionado.getCuentas().isEmpty();
    }

    public int getCantidadCuentas(int seleccionCliente) {
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        List<CuentaBancaria> cuentas = clienteSeleccionado.getCuentas();
        if (cuentas != null){
            return cuentas.size();
        }
        else {
            return 0;
        }
    }

    public String nombreYApellido(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);

        String nombreYApellido = clienteSeleccionado.getNombre() + " " + clienteSeleccionado.getApellido();
        return nombreYApellido;
    }

    /*

    public void mostrarDetallesCuenta(int seleccionCliente, int tipo){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        CuentaAhorro cuentaAhorro = clienteSeleccionado.getCuentaAhorro();
        CuentaCorriente cuentaCorriente = clienteSeleccionado.getCuentaCorriente();

        if (tipo == 1){
            mostrarDetallesCuentaAhorro(cuentaAhorro);
        } else if (tipo == 2) {
            mostrarDetallesCuentaCorriente(cuentaCorriente);
        }
        else{
            System.out.println("Opción inválida");
            return;
        }
    }

    public void mostrarDetallesCuentaAhorro(CuentaAhorro cuenta){

        // Mostrar detalles de la cuenta
        System.out.println("\nDetalles de la Cuenta de Ahorro:");
        System.out.println();
        System.out.println("Saldo: $" + cuenta.getSaldo());
        System.out.println("Tasa de interés: " + cuenta.getTasaInteres() + "%");
        System.out.println("Ahorro mínimo: $" + cuenta.getTopeMinimo());
    }

    public void mostrarDetallesCuentaCorriente(CuentaCorriente cuenta){

        // Mostrar detalles de la cuenta
        System.out.println("\nDetalles de la Cuenta Corriente:");
        System.out.println("Saldo: $" + cuenta.getSaldo());
        System.out.println("Sobregiro: $" + cuenta.getSobregiro());
    }

    public void verCuentas(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        List<CuentaBancaria> cuentas = clienteSeleccionado.getCuentas();
        System.out.println();
        for (int i = 0; i < cuentas.size(); i++) {
            String tipo;
            if(cuentas.get(i).getTipo().equals("a")){
                tipo = "Cuenta de Ahorro";
                System.out.println(String.valueOf(i + 1) + ". " + tipo);
                System.out.println();
            } else if (cuentas.get(i).getTipo().equals("c")) {
                tipo = "Cuenta Corriente";
                System.out.println(String.valueOf(i + 1) + ". " + tipo);
                System.out.println();
            }
        }
    }

    public void procesarTransaccion(int seleccionCliente, int tipoC, int tipoT, int monto){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        int idCliente = clienteSeleccionado.getId();
        if(tipoC == 1){
            CuentaAhorro cuenta = clienteSeleccionado.getCuentaAhorro();
            if(tipoT == 1){
                cuenta.depositar(monto);
                System.out.println("El deposito se realizó con éxito");
            } else if (tipoT == 2) {
                cuenta.retirar(monto);
            }
        } else if (tipoC == 2) {
            CuentaCorriente cuenta = clienteSeleccionado.getCuentaCorriente();
            if(tipoT == 1){
                cuenta.depositar(monto);
                System.out.println("El deposito se realizó con éxito");
            } else if (tipoT == 2) {
                cuenta.retirar(monto);
            }
        }
    }

    */
}
