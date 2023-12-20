package Vista;

import Controlador.*;

import java.util.Scanner;

public class BancoVirtual {
    private ControladorBanco controlador;

    public BancoVirtual(ControladorBanco controlador) {
        this.controlador = controlador;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nMenú principal:");
                System.out.println("\n1. Lista de Clientes");
                System.out.println("2. Crear Cliente");
                System.out.println("3. Eliminar Cliente");
                System.out.println("4. Crear Cuenta de Ahorro");
                System.out.println("5. Crear Cuenta Corriente");
                System.out.println("6. Realizar Transacción");
                System.out.println("7. Salir");

                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        listaClientes();
                        break;
                    case 2:
                        crearCliente();
                        break;
                    case 3:
                        eliminarCliente();
                        break;
                    case 4:
                        crearCuentaAhorro();
                        break;
                    case 5:
                        crearCuentaCorriente();
                        break;
                    case 6:
                        realizarTransaccion();
                        break;
                    case 7:
                        System.out.println("Saliendo del programa...");
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private void listaClientes() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        if (!controlador.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Clientes disponibles: ");
            System.out.println();
            int cantClientes = controlador.getCantidadClientes();
            for (int i = 0; i < cantClientes; i++) {
                System.out.println((i + 1) + ". " + controlador.getClientes().get(i).getNombre() + " " + controlador.getClientes().get(i).getApellido());
            }
            try {
                System.out.println();
                System.out.println("Seleccione el número de un cliente para ver detalles (0 para volver al menú anterior): ");
                int seleccionCliente = scanner.nextInt();

                if (seleccionCliente >= 1 && seleccionCliente <= cantClientes) {
                    controlador.mostrarDetallesCliente(seleccionCliente);

                    // Opciones adicionales
                    System.out.println("\n1. Volver al menú anterior");
                    System.out.println("2. Volver al menú principal");
                    System.out.println("3. Ver detalles de las cuentas");
                    System.out.println();

                    System.out.print("Seleccione una opción: ");
                    int opcionCliente = scanner.nextInt();

                    switch (opcionCliente) {
                        case 1:
                            // Volver al menú anterior
                            listaClientes();
                            break;
                        case 2:
                            // Volver al menú principal
                            mostrarMenu();
                            break;
                        case 3:
                            verDetallesCuentas(seleccionCliente);
                            break;
                        default:
                            System.out.println("Opción no válida. Volviendo al menú anterior.");
                    }
                } else if (seleccionCliente == 0) {
                    // Volver al menú anterior
                    mostrarMenu();
                } else {
                    System.out.println("Número de cliente no válido. Volviendo al menú anterior.");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private void verDetallesCuentas(int seleccionCliente) {
        Scanner scanner = new Scanner(System.in);
        // Obtener la lista de cuentas del cliente desde el controlador

        if (!controlador.hayCuentas(seleccionCliente)) {
            System.out.println("El cliente no tiene cuentas registradas.");
        } else {
            try {
                int cantCuentas = controlador.getCantidadCuentas(seleccionCliente);
                if (cantCuentas == 1){
                    if(controlador.hayCuentaAhorro(seleccionCliente)){
                        controlador.mostrarDetallesCuenta(seleccionCliente, 1);
                        opcionesAdicionales(seleccionCliente);
                    } else if (controlador.hayCuentaCorriente(seleccionCliente)) {
                        controlador.mostrarDetallesCuenta(seleccionCliente, 2);
                        opcionesAdicionales(seleccionCliente);
                    }
                    else{
                        return;
                    }
                }

                else {
                    // Mostrar la lista de cuentas con números asociados
                    System.out.println();
                    System.out.print("Cuentas disponibles: ");
                    System.out.println();
                    controlador.verCuentas(seleccionCliente);
                    System.out.println();
                    System.out.println("Ingrese 1 para ver detalles de la cuenta de ahorro");
                    System.out.println("Ingrese 2 para ver detalles de la cuenta corriente");
                    System.out.println("Ingrese 0 para volver al menú anterior");
                    System.out.println();
                    int tipo = scanner.nextInt();
                    if (tipo == 0){
                        listaClientes();
                    }
                    controlador.mostrarDetallesCuenta(seleccionCliente, tipo);
                    opcionesAdicionales(seleccionCliente);
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private void opcionesAdicionales(int seleccionCliente){
        // Opciones adicionales
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n1. Volver al menú anterior");
        System.out.println("2. Volver al menú principal");
        System.out.println();

        System.out.print("Seleccione una opción: ");
        int opcionCuenta = scanner.nextInt();

        switch (opcionCuenta) {
            case 1:
                // Volver al menú anterior
                controlador.mostrarDetallesCliente(seleccionCliente);
                break;
            case 2:
                // Volver al menú principal
                mostrarMenu();
                break;
            default:
                System.out.println("Opción no válida. Volviendo al menú anterior.");
        }
    }


    private void crearCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nIngrese los datos del nuevo cliente:");
        System.out.println();

        System.out.print("Nombre: ");
        String nombre = scanner.next();

        System.out.print("Apellido: ");
        String apellido = scanner.next();

        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("RUT: ");
        String rut = scanner.next();

        System.out.print("Teléfono: ");
        int fono = scanner.nextInt();

        try {
            int cantClientes = controlador.getCantidadClientes();
            controlador.crearCliente(cantClientes + 1, nombre, apellido, email, rut, fono);

            System.out.println("\nCliente creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el cliente: " + e.getMessage());
        }
    }


    private void eliminarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        if (!controlador.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Clientes disponibles: ");
            System.out.println();
            int cantClientes = controlador.getCantidadClientes();
            for (int i = 0; i < cantClientes; i++) {
                System.out.println((i + 1) + ". " + controlador.getClientes().get(i).getNombre() + " " + controlador.getClientes().get(i).getApellido());
            }
            System.out.println();
            System.out.print("Seleccione el número de un cliente para eliminarlo del banco: ");
            try {
                int seleccionCliente = scanner.nextInt();
                System.out.println();

                if (seleccionCliente >= 1 && seleccionCliente <= cantClientes) {
                    controlador.eliminarCliente(seleccionCliente);

                    // Opciones adicionales
                    System.out.println("\n1. Volver al menú anterior");
                    System.out.println("2. Volver al menú principal");

                    System.out.println();

                    System.out.print("Seleccione una opción: ");
                    int opcionCliente = scanner.nextInt();

                    switch (opcionCliente) {
                        case 1:
                            // Volver al menú anterior
                            eliminarCliente();
                            break;
                        case 2:
                            // Volver al menú principal
                            mostrarMenu();
                            break;
                        default:
                            System.out.println("Opción no válida. Volviendo al menú anterior.");
                    }
                } else if (seleccionCliente == 0) {
                    // Volver al menú anterior
                } else {
                    System.out.println("Número de cliente no válido. Volviendo al menú anterior.");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private void crearCuentaAhorro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        // Mostrar la lista de clientes con números asociados
        if (!controlador.hayClientes()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Clientes disponibles: ");
        System.out.println();
        int cantClientes = controlador.getCantidadClientes();
        for (int i = 0; i < cantClientes; i++) {
            System.out.println((i + 1) + ". " + controlador.getClientes().get(i).getNombre() + " " + controlador.getClientes().get(i).getApellido());
        }
        System.out.println();
        System.out.println("Seleccione el número de un cliente para crear una cuenta de ahorro (0 para volver al menú anterior): ");
        try {
            int seleccionCliente = scanner.nextInt();

            if (seleccionCliente == 0) {
                // Volver al menú anterior
                mostrarMenu();
                return;
            }

            if (seleccionCliente >= 1 && seleccionCliente <= cantClientes) {

                if (controlador.hayCuentaAhorro(seleccionCliente)) {
                    System.out.println("El cliente ya tiene una cuenta de ahorro.");
                } else {
                    // Pedir datos para crear la cuenta de ahorro
                    System.out.print("Ingrese el saldo inicial: ");
                    int saldoInicial = scanner.nextInt();

                    System.out.print("Ingrese la tasa de interés: ");
                    int tasaInteres = scanner.nextInt();

                    System.out.print("Ingrese el tope mínimo de ahorro: ");
                    int topeMinimo = scanner.nextInt();

                    // Obtener el número de cuentas del cliente y sumar 1 para el nuevo ID
                    int numCuentas = controlador.getCantidadCuentas(seleccionCliente) + 1;

                    // Crear la cuenta de ahorro y asociarla al cliente
                    controlador.crearCuentaAhorro(numCuentas, controlador.getIdCliente(seleccionCliente), saldoInicial, "a", tasaInteres, topeMinimo);
                    System.out.println("\nCuenta de ahorro creada con éxito para el cliente " + controlador.nombreYApellido(seleccionCliente));
                }
            } else {
                System.out.println("Número de cliente no válido. Volviendo al menú anterior.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Ingrese un número válido.");
            scanner.nextLine();  // Limpiar el búfer de entrada
        }
    }


    private void crearCuentaCorriente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        // Mostrar la lista de clientes con números asociados
        if (!controlador.hayClientes()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Clientes disponibles: ");
        System.out.println();
        int cantClientes = controlador.getCantidadClientes();
        for (int i = 0; i < cantClientes; i++) {
            System.out.println((i + 1) + ") " + controlador.getClientes().get(i).getNombre() + " " + controlador.getClientes().get(i).getApellido());
        }
        System.out.println();
        System.out.println("Seleccione el número de un cliente para crear una cuenta corriente (0 para volver al menú anterior): ");
        try {
            int seleccionCliente = scanner.nextInt();

            if (seleccionCliente == 0) {
                // Volver al menú anterior
                mostrarMenu();
                return;
            }

            if (seleccionCliente >= 1 && seleccionCliente <= cantClientes) {

                if (controlador.hayCuentaCorriente(seleccionCliente)) {
                    System.out.println("El cliente ya tiene una cuenta corriente.");
                } else {
                    // Pedir datos para crear la cuenta corriente
                    System.out.print("Ingrese el saldo inicial: ");
                    int saldoInicial = scanner.nextInt();

                    System.out.print("Ingrese el límite de sobregiro: ");
                    int sobregiro = scanner.nextInt();

                    // Obtener el número de cuentas del cliente y sumar 1 para el nuevo ID
                    int numCuentas = controlador.getCantidadCuentas(seleccionCliente) + 1;

                    // Crear la cuenta corriente y asociarla al cliente
                    controlador.crearCuentaCorriente(numCuentas, controlador.getIdCliente(seleccionCliente), saldoInicial, "c", sobregiro);
                    System.out.println("\nCuenta corriente creada con éxito para el cliente " + controlador.nombreYApellido(seleccionCliente));
                }
            } else {
                System.out.println("Número de cliente no válido. Volviendo al menú anterior.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Ingrese un número válido.");
            scanner.nextLine();  // Limpiar el búfer de entrada
        }
    }


    private void realizarTransaccion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        // Mostrar la lista de clientes con números asociados
        if (!controlador.hayClientes()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Clientes disponibles: ");
        System.out.println();
        int cantClientes = controlador.getCantidadClientes();
        for (int i = 0; i < cantClientes; i++) {
            System.out.println((i + 1) + ". " + controlador.getClientes().get(i).getNombre() + " " + controlador.getClientes().get(i).getApellido());
        }
        System.out.println();
        System.out.println("Seleccione el número de un cliente para realizar una transacción (0 para volver al menú anterior): ");
        try {
            int seleccionCliente = scanner.nextInt();

            if (seleccionCliente == 0) {
                // Volver al menú anterior
                mostrarMenu();
                return;
            }

            if (seleccionCliente >= 1 && seleccionCliente <= cantClientes) {

                // Mostrar la lista de cuentas con números asociados
                if (!controlador.hayCuentas(seleccionCliente)) {
                    System.out.println("El cliente no tiene cuentas registradas.");
                    return;
                }

                System.out.println("Cuentas disponibles: ");
                controlador.verCuentas(seleccionCliente);

                int cantCuentas = controlador.getCantidadCuentas(seleccionCliente);

                if (cantCuentas == 0){
                    System.out.println("El cliente seleccionado no posee cuentas en el banco");
                } else if (cantCuentas == 1) {
                    if(controlador.hayCuentaAhorro(seleccionCliente)){
                        System.out.println("Ingrese 1 para hacer un depósito, y 2 para hacer un retiro sobre la cuenta de ahorro");
                        int tipoT = scanner.nextInt();
                        if(tipoT == 1){
                            System.out.println("Ingrese el monto del depósito: ");
                            int monto = scanner.nextInt();
                            controlador.procesarTransaccion(seleccionCliente, 1, tipoT, monto);
                        } else if (tipoT == 2) {
                            System.out.println("Ingrese el monto del retiro: ");
                            int monto = scanner.nextInt();
                            controlador.procesarTransaccion(seleccionCliente, 1, tipoT, monto);
                        }
                        else{
                            return;
                        }
                    } else if (controlador.hayCuentaCorriente(seleccionCliente)) {
                        System.out.println("Ingrese 1 para hacer un depósito, y 2 para hacer un retiro sobre la cuenta corriente");
                        int tipoT = scanner.nextInt();
                        if(tipoT == 1){
                            System.out.println("Ingrese el monto del depósito: ");
                            int monto = scanner.nextInt();
                            controlador.procesarTransaccion(seleccionCliente, 2, tipoT, monto);
                        } else if (tipoT == 2) {
                            System.out.println("Ingrese el monto del retiro: ");
                            int monto = scanner.nextInt();
                            controlador.procesarTransaccion(seleccionCliente, 2, tipoT, monto);
                        }
                        else{
                            return;
                        }
                    }
                } else if (cantCuentas == 2) {
                    System.out.println();
                    System.out.println("Ingrese 1 para operar con la cuenta de ahorro y 2 para operar con la cuenta corriente");
                    int tipoC = scanner.nextInt();
                    System.out.println("Ingrese 1 para hacer un depósito y 2 para hacer un retiro");
                    int tipoT = scanner.nextInt();
                    System.out.println("Ingrese el monto de la operación");
                    int monto = scanner.nextInt();

                    controlador.procesarTransaccion(seleccionCliente, tipoC, tipoT, monto);
                }
                else {
                    return;
                }
            } else {
                System.out.println("Número de cliente no válido. Volviendo al menú anterior.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Ingrese un número válido.");
            scanner.nextLine();  // Limpiar el búfer de entrada
        }
    }


    public static void main(String[] args) {
        ControladorBanco controlador = new ControladorBanco();
        BancoVirtual vista = new BancoVirtual(controlador);

        controlador.crearCliente(1, "Marcial", "Díaz", "marcial.diaz03@inacapmail.cl", "19.524.734-k", 978030199);
        controlador.crearCliente(2, "Valentina", "Díaz", "vale.diaz03@inacapmail.cl", "19.960.609-9", 978036504);

        controlador.crearCuentaAhorro(1, 1, 150000, "a", 6, 100000);
        controlador.crearCuentaCorriente(2, 1, 500000, "c", 200000);

        controlador.crearCuentaAhorro(3, 2, 300000, "a", 6, 100000);
        controlador.crearCuentaCorriente(4, 2, 1000000, "c", 400000);

        System.out.println("\n¡Hola! Bienvenid@ al Banco Virtual");
        vista.mostrarMenu();
    }
}
