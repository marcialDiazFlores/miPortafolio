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
    private String fono;
    private CuentaDeAhorro cuentaDeAhorro;
    private CuentaCorriente cuentaCorriente;

    // Constructor

    public Cliente(String nombre, String apellido, String email, String rut, String fono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.rut = rut;
        this.fono = fono;
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

    public String getFono() {
        return fono;
    }

    public CuentaDeAhorro getCuentaDeAhorro() { return cuentaDeAhorro; }
    public CuentaCorriente getCuentaCorriente() { return cuentaCorriente; }

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

    public void setFono(String fono) {
        this.fono = fono;
    }

    public void setCuentaDeAhorro(CuentaDeAhorro cuentaDeAhorro) {
        this.cuentaDeAhorro = cuentaDeAhorro;
    }
    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public String toString() {
        String cuentaDeAhorro = (this.cuentaDeAhorro != null) ? "Sí" : "No";
        String cuentaCorriente = (this.cuentaCorriente != null) ? "Sí" : "No";

        return "ID: " + this.getId() + " | " +
                "Nombre: " + this.getNombre() + " | " +
                "Apellido: " + this.getApellido() + " | " +
                "Rut: " + this.getRut() + " | " +
                "Email: " + this.getEmail() + " | " +
                "Teléfono: " + this.getFono() + " | " +
                "¿Tiene cuenta de ahorro? " + cuentaDeAhorro + " | " +
                "¿Tiene cuenta corriente? " + cuentaCorriente;
    }
}
