package Vista;

import Controlador.*;

import java.util.Scanner;

public class BancoVirtual {
    private static ControladorBanco controlador;

    private static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nMenú principal:");
                System.out.println("\n1. Gestión de clientes");
                System.out.println("2. Gestión de cuentas");
                System.out.println("3. Realizar transacciones");
                System.out.println("4. Salir");

                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        menuClientes(scanner);
                        break;
                    case 2:
                        menuCuentas(scanner);
                        break;
                    case 3:
                        //menuTransacciones();
                        break;
                    case 4:
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

    private static void menuClientes(Scanner scanner) {

        while (true) {
            try {
                System.out.println("\nGestionar clientes:");
                System.out.println("\n1. Ingresar cliente");
                System.out.println("2. Lista de clientes");
                System.out.println("3. Actualizar datos de un cliente");
                System.out.println("4. Eliminar cliente");
                System.out.println("5. Volver al manú principal");

                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        ingresarCliente(scanner);
                        break;
                    case 2:
                        listaClientes(scanner);
                        break;
                    case 3:
                        //actualizarClientes();
                        break;
                    case 4:
                        eliminarCliente();
                        break;
                    case 5:
                        mostrarMenu();
                        break;
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

    private static void ingresarCliente (Scanner scanner) {

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
        String fono = scanner.next();

        try {
            int cantClientes = getCantidadClientes();
            crearCliente(nombre, apellido, email, rut, fono);
            System.out.println("\nCliente creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el cliente: " + e.getMessage());
        }
    }

    private static void listaClientes(Scanner scanner) {
        System.out.println();

        if (!controlador.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Clientes disponibles: ");
            System.out.println();
            controlador.mostrarDetallesClientes();
            try {
                // Opciones adicionales
                System.out.println("1. Volver al menú anterior");
                System.out.println("2. Volver al menú principal");
                System.out.println();

                System.out.print("Seleccione una opción: ");
                int opcionCliente = scanner.nextInt();

                switch (opcionCliente) {
                    case 1:
                        // Volver al menú anterior
                        menuClientes(scanner);
                        break;
                    case 2:
                        // Volver al menú principal
                        mostrarMenu();
                        break;
                    default:
                        System.out.println("Opción no válida. Volviendo al menú anterior.");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private static void verDetallesCuentas(int seleccionCliente) {
        Scanner scanner = new Scanner(System.in);
        // Obtener la lista de cuentas del cliente desde el controlador

        if (!controlador.hayCuentas(seleccionCliente)) {
            System.out.println("El cliente no tiene cuentas registradas.");
        } else {
            try {
                int cantCuentas = controlador.getCantidadCuentas(seleccionCliente);
                if (cantCuentas == 1){
                    if(controlador.hayCuentaAhorro(seleccionCliente - 1)){
                        controlador.mostrarDetallesCuenta(seleccionCliente, 1);
                        opcionesAdicionales(seleccionCliente);
                    } else if (controlador.hayCuentaCorriente(seleccionCliente - 1)) {
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
                        listaClientes(scanner);
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

    private static void opcionesAdicionales(int seleccionCliente){
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
                controlador.mostrarDetallesCliente(seleccionCliente - 1);
                break;
            case 2:
                // Volver al menú principal
                mostrarMenu();
                break;
            default:
                System.out.println("Opción no válida. Volviendo al menú anterior.");
        }
    }

    private static void eliminarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        if (!controlador.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Clientes del banco: ");
            System.out.println();
            int cantClientes = getCantidadClientes();
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

    private static void menuCuentas(Scanner scanner) {

        while (true) {
            try {
                System.out.println("\nMenú de gestión de cuentas:");

                if (!controlador.hayClientes()) {
                    System.out.println("No hay clientes registrados para poder gestionar cuentas.");
                } else {
                    System.out.println("\nClientes disponibles: ");
                    System.out.println();
                    int cantClientes = getCantidadClientes();
                    for (int i = 0; i < cantClientes; i++) {
                        System.out.println((i + 1) + ". " + controlador.getClientes().get(i).getNombre() + " " + controlador.getClientes().get(i).getApellido());
                    }
                    System.out.println();
                    System.out.println("Seleccione el número de un cliente para la gestión de cuentas (0 para volver al menú anterior): ");
                    try {
                        int seleccionCliente = scanner.nextInt();

                        if (seleccionCliente == 0) {
                            // Volver al menú anterior
                            mostrarMenu();
                            return;
                        }

                        if (seleccionCliente >= 1 && seleccionCliente <= cantClientes) {

                            if (controlador.hayCuentaAhorro(seleccionCliente - 1) && controlador.hayCuentaCorriente(seleccionCliente - 1)) {
                                System.out.println("\n1. Eliminar cuenta de ahorro");
                                System.out.println("2. Eliminar cuenta corriente");
                                System.out.println("3. Volver al menú anterior");
                                System.out.println("4. Volver al menú principal");
                                System.out.println();

                                int seleccion = scanner.nextInt();

                                if (seleccion == 1) {
                                    //eliminarCuentaAhorro(scanner, seleccionCliente);
                                }

                                else if (seleccion == 2) {
                                    //eliminarCuentaCorriente(scanner, seleccionCliente);
                                }

                                else {
                                    System.out.println("La opcion ingresada no es válida");
                                    menuCuentas(scanner);
                                }
                            }
                            else if (controlador.hayCuentaAhorro(seleccionCliente - 1) && !controlador.hayCuentaCorriente(seleccionCliente - 1)) {
                                System.out.println("\n1. Eliminar cuenta de ahorro");
                                System.out.println("2. Crear cuenta corriente");
                                System.out.println("3. Volver al menú anterior");
                                System.out.println("4. Volver al menú principal");
                                System.out.println();

                                int seleccion = scanner.nextInt();

                                if (seleccion == 1) {
                                    //eliminarCuentaAhorro(scanner, seleccionCliente);
                                }

                                else if (seleccion == 2) {
                                    crearCuentaCorriente(scanner, seleccionCliente);
                                }

                                else {
                                    System.out.println("La opcion ingresada no es válida");
                                    menuCuentas(scanner);
                                }
                            }
                            else if (!controlador.hayCuentaAhorro(seleccionCliente - 1) && controlador.hayCuentaCorriente(seleccionCliente - 1)) {
                                System.out.println("\n1. Crear cuenta de ahorro");
                                System.out.println("2. Eliminar cuenta corriente");
                                System.out.println("3. Volver al menú anterior");
                                System.out.println("4. Volver al menú principal");
                                System.out.println();

                                int seleccion = scanner.nextInt();

                                if (seleccion == 1) {
                                    crearCuentaAhorro(scanner, seleccionCliente);
                                }

                                else if (seleccion == 2) {
                                    //eliminarCuentaCorriente(scanner, seleccionCliente);
                                }

                                else {
                                    System.out.println("La opcion ingresada no es válida");
                                    menuCuentas(scanner);
                                }
                            }
                            else {
                                System.out.println("\n1. Crear cuenta de ahorro");
                                System.out.println("2. Crear cuenta corriente");
                                System.out.println("3. Volver al menú anterior");
                                System.out.println("4. Volver al menú principal");
                                System.out.println();

                                int seleccion = scanner.nextInt();

                                if (seleccion == 1) {
                                    crearCuentaAhorro(scanner, seleccionCliente);
                                }

                                else if (seleccion == 2) {
                                    crearCuentaCorriente(scanner, seleccionCliente);
                                }

                                else {
                                    System.out.println("La opcion ingresada no es válida");
                                    menuCuentas(scanner);
                                }
                            }
                        } else {
                            System.out.println("Número de cliente no válido. Volviendo al menú anterior.");
                        }
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Error: Ingrese un número válido.");
                        scanner.nextLine();  // Limpiar el búfer de entrada
                    }
                }

                System.out.println("\nSeleccione un cliente para gestionar sus cuentas (0 para volver al menú principal)");

                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 0:
                        mostrarMenu();
                    case 1:
                        ingresarCliente(scanner);
                        break;
                    case 2:
                        listaClientes(scanner);
                        break;
                    case 3:
                        //actualizarClientes();
                        break;
                    case 4:
                        eliminarCliente();
                        break;
                    case 5:
                        mostrarMenu();
                        break;
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

    private static void crearCuentaAhorro(Scanner scanner, int seleccionCliente) {

        try {

            if (seleccionCliente >= 1 && seleccionCliente <= controlador.getCantidadClientes()) {
                if (controlador.hayCuentaAhorro(seleccionCliente - 1)) {
                    System.out.println("\nEl cliente ya tiene una cuenta de ahorro.");
                } else {
                    // Pedir datos para crear la cuenta de ahorro
                    System.out.print("\nIngrese el saldo inicial: ");
                    int saldoInicial = scanner.nextInt();

                    System.out.print("Ingrese la tasa de interés: ");
                    int tasaInteres = scanner.nextInt();

                    System.out.print("Ingrese el tope mínimo de ahorro: ");
                    int topeMinimo = scanner.nextInt();

                    // Obtener el número de cuentas del cliente y sumar 1 para el nuevo ID
                    int numCuentas = controlador.getCantidadCuentas(seleccionCliente) + 1;

                    // Crear la cuenta de ahorro y asociarla al cliente
                    controlador.crearCuentaAhorro(controlador.getIdCliente(seleccionCliente), saldoInicial, "a", tasaInteres, topeMinimo);
                    System.out.println("\nCuenta de ahorro creada con éxito para el cliente " + controlador.nombreYApellido(seleccionCliente));

                    System.out.println("\n1. Volver a gestión de cuentas");
                    System.out.println("2. Volver al menú principal");
                    System.out.println();

                    int opcion = scanner.nextInt();

                    switch(opcion) {
                        case 1:
                            menuCuentas(scanner);
                            break;
                        case 2:
                            mostrarMenu();
                            break;
                        default:
                            System.out.println("Opción no válida, intente nuevamente");
                    }
                }
            } else {
                System.out.println("Número de cliente no válido. Volviendo al menú anterior.");
            }
        }
        catch (java.util.InputMismatchException e) {
            System.out.println("Error: Ingrese un número válido.");
            scanner.nextLine();  // Limpiar el búfer de entrada
        }
    }


    private static void crearCuentaCorriente(Scanner scanner, int seleccionCliente) {
        try {
            if (seleccionCliente >= 1 && seleccionCliente <= controlador.getCantidadClientes()) {

                if (controlador.hayCuentaCorriente(seleccionCliente - 1)) {
                    System.out.println("\nEl cliente ya tiene una cuenta corriente.");
                } else {
                    // Pedir datos para crear la cuenta corriente
                    System.out.print("\nIngrese el saldo inicial: ");
                    int saldo = scanner.nextInt();

                    System.out.print("Ingrese el cupo máximo de sobregiro: ");
                    int sobregiro = scanner.nextInt();

                    // Obtener el número de cuentas del cliente y sumar 1 para el nuevo ID
                    int numCuentas = controlador.getCantidadCuentas(seleccionCliente) + 1;

                    // Crear la cuenta de ahorro y asociarla al cliente
                    controlador.crearCuentaCorriente(controlador.getIdCliente(seleccionCliente), saldo, "c", sobregiro);
                    System.out.println("\nCuenta de ahorro creada con éxito para el cliente " + controlador.nombreYApellido(seleccionCliente));

                    System.out.println("\n1. Volver a gestión de cuentas");
                    System.out.println("2. Volver al menú principal");
                    System.out.println();

                    int opcion = scanner.nextInt();

                    switch(opcion) {
                        case 1:
                            menuCuentas(scanner);
                            break;
                        case 2:
                            mostrarMenu();
                            break;
                        default:
                            System.out.println("Opción no válida, intente nuevamente");
                    }
                }
            } else {
                System.out.println("Número de cliente no válido. Volviendo al menú anterior.");
            }
        }
        catch (java.util.InputMismatchException e) {
            System.out.println("Error: Ingrese un número válido.");
            scanner.nextLine();  // Limpiar el búfer de entrada
        }
    }


    private static void realizarTransaccion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        // Mostrar la lista de clientes con números asociados
        if (!controlador.hayClientes()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Clientes disponibles: ");
        System.out.println();
        controlador.mostrarDetallesClientes();
        System.out.println();
        System.out.println("Seleccione el número de un cliente para realizar una transacción (0 para volver al menú anterior): ");
        try {
            int cantClientes = getCantidadClientes();
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
                    if(controlador.hayCuentaAhorro(seleccionCliente - 1)){
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
                    } else if (controlador.hayCuentaCorriente(seleccionCliente - 1)) {
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

    private static void inicializarControlador() {
        controlador = new ControladorBanco();
    }

    private static void reiniciarBDD() {
       // controlador.reiniciarBDD();
    }

    private static void crearCliente(String nombre, String apellido, String email, String rut, String fono) {
        controlador.crearCliente(nombre, apellido, email, rut, fono);
    }

    private static int getCantidadClientes() {
        return controlador.getCantidadClientes();
    }


    public static void main(String[] args) {
        inicializarControlador();
        reiniciarBDD();

        crearCliente("Marcial", "Díaz", "marcial.diaz03@inacapmail.cl", "19.524.734-k", "+56978030199");
        crearCliente("Valentina", "Díaz", "vale.diaz03@inacapmail.cl", "19.960.609-9", "+56978036504");

        controlador.crearCuentaAhorro(1, 150000, "a", 6, 100000);
        controlador.crearCuentaCorriente(1,465630, "c", 55000);

        controlador.crearCuentaAhorro(2, 666000, "a", 6, 800000);
        controlador.crearCuentaCorriente(2,1000000, "c", 400000);

        System.out.println("\n¡Hola! Bienvenid@ al Banco Virtual");
        mostrarMenu();
    }
}
