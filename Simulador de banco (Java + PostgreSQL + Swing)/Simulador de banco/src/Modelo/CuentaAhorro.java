package Modelo;

// Hereda de la clase CuentaBancaria
public class CuentaAhorro extends CuentaBancaria {
    private float tasaInteres;
    private int topeMinimo;

    public CuentaAhorro(int idCliente, int saldo, float tasaInteres, int topeMinimo) {
        super(idCliente, saldo);
        this.tasaInteres = tasaInteres;
        this.topeMinimo = topeMinimo;
    }

    public float getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(int tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public int getTopeMinimo() {
        return topeMinimo;
    }

    public void setTopeMinimo(int topeMinimo) {
        this.topeMinimo = topeMinimo;
    }

    /*

    // Polimorfismo (sobrecarga del método)
    public void retirar(int monto) {
        if (monto > 0) {
            int saldoActual = this.getSaldo();

            // Aplicar tasa de interés a la cuenta de ahorro
            int intereses = saldoActual * tasaInteres/100;
            this.setSaldo(saldoActual + intereses);
            saldoActual = this.getSaldo();

            // Verificar si hay suficiente saldo para el retiro
            if (monto <= saldoActual && saldoActual >= topeMinimo) {
                // Aplicar retiro
                this.setSaldo(saldoActual - monto);
                System.out.println();
                System.out.println("Retiro exitoso. Se aplicó una tasa de interés de " + (tasaInteres) + "%.");
                System.out.println("Saldo de la cuenta: $" + getSaldo());
            } else if (monto <= saldoActual) {
                System.out.println("Error: La cuenta no ha alcanzado el tope mínimo de ahorro.");
            } else {
                System.out.println("Error: Fondos insuficientes para hacer el retiro.");
            }
        } else {
            System.out.println("Error: El monto de retiro debe ser mayor que cero.");
        }
    }

    */
}
