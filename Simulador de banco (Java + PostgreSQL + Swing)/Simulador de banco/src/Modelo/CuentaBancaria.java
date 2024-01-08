// CuentaBancaria.java
package Modelo;

public class CuentaBancaria {
    private int id;
    private int idCliente;
    private int saldo;
    private String tipo;

    // Constructor, getters y setters

    // Constructor

    public CuentaBancaria(int id, int idCliente, int saldo, String tipo) {
        this.id = id;
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.tipo = tipo;
    }

    // Getters

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getTipo() {
        return tipo;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Operaciones bancarias

    public void depositar(int monto) {
        this.setSaldo(this.getSaldo() + monto);
    }

    public void retirar (int monto){
        this.setSaldo(this.getSaldo() - monto);
    }
}
