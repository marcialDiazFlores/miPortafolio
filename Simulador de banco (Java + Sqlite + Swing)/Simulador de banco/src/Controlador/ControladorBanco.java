package Controlador;

import Modelo.*;

import java.util.ArrayList;
import java.util.List;

public class ControladorBanco {
    private List<Cliente> clientes;

    public ControladorBanco() {
        this.clientes = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void crearCliente(int id, String nombre, String apellido, String email, String rut, int fono) {
        Cliente cliente = new Cliente(id, nombre, apellido, email, rut, fono);
        clientes.add(cliente);
    }

    public void crearCuentaAhorro(int id, int idCliente, int saldoInicial, String tipo, int tasaInteres, int topeMinimo) {
        Cliente cliente = encontrarClientePorId(idCliente);
        if (cliente != null) {
            CuentaAhorro cuenta = new CuentaAhorro(id, idCliente, saldoInicial, tipo, tasaInteres, topeMinimo);
            cliente.agregarCuenta(cuenta);
        } else {
            System.out.println("Error: Cliente no encontrado al crear cuenta de ahorro.");
        }
    }

    public int getIdCliente(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        return clienteSeleccionado.getId();
    }

    public void crearCuentaCorriente(int id, int idCliente, int saldo, String tipo, int sobregiro) {
        Cliente cliente = encontrarClientePorId(idCliente);
        if (cliente != null) {
            CuentaCorriente cuenta = new CuentaCorriente(id, idCliente, saldo, tipo, sobregiro);
            cliente.agregarCuenta(cuenta);
        } else {
            System.out.println("Error: Cliente no encontrado al crear cuenta corriente.");
        }
    }

    public void eliminarCliente(int idCliente) {
        Cliente cliente = encontrarClientePorId(idCliente);
        if (cliente != null) {
            clientes.remove(cliente);
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

    public boolean hayClientes() {
        return !clientes.isEmpty();
    }


    public boolean hayCuentas(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        return !clienteSeleccionado.getCuentas().isEmpty();
    }

    public int getCantidadClientes() {
        if (clientes != null){
            return clientes.size();
        }
        else {
            return 0;
        }
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

    public boolean hayCuentaAhorro(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        if (clienteSeleccionado.getCuentaAhorro() != null){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hayCuentaCorriente(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        if (clienteSeleccionado.getCuentaCorriente() != null){
            return true;
        }
        else {
            return false;
        }
    }

    public void mostrarDetallesCliente(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);

        // Mostrar detalles del cliente
        System.out.println("\nDetalles del Cliente:");
        System.out.println();
        System.out.println("Nombre: " + clienteSeleccionado.getNombre());
        System.out.println("Apellido: " + clienteSeleccionado.getApellido());
        System.out.println("Rut: " + clienteSeleccionado.getRut());
        System.out.println("Email: " + clienteSeleccionado.getEmail());
        System.out.println("Teléfono: " + clienteSeleccionado.getFono());
    }

    public String nombreYApellido(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);

        String nombreYApellido = clienteSeleccionado.getNombre() + " " + clienteSeleccionado.getApellido();
        return nombreYApellido;
    }

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
            } else if (cuentas.get(i).getTipo().equals("c")) {
                tipo = "Cuenta Corriente";
                System.out.println(String.valueOf(i + 1) + ". " + tipo);
            }
        }
    }

    public void procesarTransaccion(int seleccionCliente, int tipoC, int tipoT, int monto){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
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
}
