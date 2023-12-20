// Cliente.java
package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String rut;
    private int fono;
    private List<CuentaBancaria> cuentas;

    // Constructor

    public Cliente(int id, String nombre, String apellido, String email, String rut, int fono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.rut = rut;
        this.fono = fono;
        this.cuentas = new ArrayList<>();
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getRut() {
        return rut;
    }

    public int getFono() {
        return fono;
    }

    public List<CuentaBancaria> getCuentas() { return cuentas; }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setFono(Integer fono) {
        this.fono = fono;
    }

    public void setCuentas(List<CuentaBancaria> cuentas) { this.cuentas = cuentas; }

    // Operaciones sobre las cuentas

    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    public void eliminarCuenta(CuentaBancaria cuenta) {
        cuentas.remove(cuenta);
    }

    public CuentaAhorro getCuentaAhorro() {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta instanceof CuentaAhorro) {
                return (CuentaAhorro) cuenta;
            }
        }
        return null;  // No se encontró una cuenta de ahorro
    }

    public CuentaCorriente getCuentaCorriente() {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta instanceof CuentaCorriente) {
                return (CuentaCorriente) cuenta;
            }
        }
        return null;  // No se encontró una cuenta corriente
    }
}
