// CuentaCorriente.java
package Modelo;

// Hereda de la clase CuentaBancaria

public class CuentaCorriente extends CuentaBancaria {
    private int sobregiro;

    // Constructor

    public CuentaCorriente(int idCliente, int saldo, int sobregiro) {
        super(idCliente, saldo);
        this.sobregiro = sobregiro;
    }

    // Getters

    public int getSobregiro() {
        return sobregiro;
    }

    // Setters

    public void setSobregiro(int sobregiro) {
        this.sobregiro = sobregiro;
    }

    // Operaciones bancarias

    /*

    // Polimorfismo (sobrecarga del método)
    public void retirar(int monto) {
        if (monto > 0) {
            int saldoActual = getSaldo();

            boolean sobreg;

            if (getSobregiro() >= 0){
                sobreg = true;
            }
            else{
                sobreg = false;
            }

            // Verificar si hay suficiente saldo y sobregiro para el retiro
            if (monto <= saldoActual) {
                // Aplicar retiro
                setSaldo(saldoActual - monto);
                ConexionBDD conn = ConexionBDD.getConn();
                System.out.println();
                System.out.println("Retiro exitoso.");
                System.out.println("Saldo restante: " + getSaldo());
            }
            else if (monto <= saldoActual + getSobregiro()){
                System.out.println("Retiro exitoso. Se utilizó el sobregiro disponible de $" + sobregiro + ".");
                setSobregiro(sobregiro + (saldoActual - monto));
                setSaldo(saldoActual - monto);
                System.out.println();
                System.out.println("Cupo restante: $" + sobregiro);
                System.out.println("Saldo de la cuenta: $" + getSaldo());
            }
            else if(sobreg && monto <= getSobregiro()) {
                System.out.println("Retiro exitoso. Se utilizó el sobregiro disponible de $" + sobregiro + ".");
                setSobregiro(sobregiro - monto);
                setSaldo(saldoActual - monto);
                System.out.println();
                System.out.println("Cupo restante: $" + sobregiro);
                System.out.println("Saldo de la cuenta: $" + getSaldo());
            }
            else {
                System.out.println("Error: Fondos insuficientes y excede el límite de sobregiro.");
            }
        } else {
            System.out.println("Error: El monto de retiro debe ser mayor que cero.");
        }
    } */
}
