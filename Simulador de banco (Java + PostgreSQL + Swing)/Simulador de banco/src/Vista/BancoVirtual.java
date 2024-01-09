package Vista;

import Controlador.*;

import java.util.Scanner;

public class BancoVirtual {
    private static ControladorClientes controladorClientes;
    private static ControladorCuentasDeAhorro controladorCuentasDeAhorro;

    private static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nMenú principal:");
                System.out.println("\n1. Gestión de clientes");
                System.out.println("2. Gestión de cuentas de ahorro");
                System.out.println("3. Gestión de cuentas corrientes");
                System.out.println("4. Salir");

                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        menuClientes(scanner);
                        break;
                    case 2:
                        menuCuentasDeAhorro(scanner);
                        break;
                    case 3:
                        //menuCuentasCorrientes(scanner);
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
                System.out.println("5. Buscar cliente");
                System.out.println("6. Volver al menú principal");

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
                        actualizarCliente();
                        break;
                    case 4:
                        eliminarCliente();
                        break;
                    case 5:
                        buscarCliente();
                        break;
                    case 6:
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
            int cantClientes = controladorClientes.getCantidadClientes();
            controladorClientes.crearCliente(nombre, apellido, email, rut, fono);
            System.out.println("\nCliente creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el cliente: " + e.getMessage());
        }
    }

    private static void listaClientes(Scanner scanner) {
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Clientes disponibles: ");
            System.out.println();
            controladorClientes.mostrarDetallesClientes();
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

    private static void actualizarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Buscar al cliente por su RUT
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();

            if (controladorClientes.encontrarClientePorRUT(rut) != null) {
                String nombreCliente = controladorClientes.encontrarClientePorRUT(rut).getNombre();

                System.out.println("Cliente encontrado");
                System.out.println("Nombre: " + nombreCliente + ", ID: " + controladorClientes.encontrarClientePorRUT(rut).getId());

                System.out.println("\nIngrese los nuevos datos del cliente:");
                System.out.println();

                System.out.print("Email: ");
                String email = scanner.next();

                System.out.print("Teléfono: ");
                String fono = scanner.next();

                controladorClientes.actualizarCliente(rut, email, fono);

                // Opciones adicionales
                System.out.println("\n1. Volver a gestión de clientes");
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

            else {
                System.out.println("Cliente no encontrado, intente nuevamente");
                actualizarCliente();
            }
        }
    }

    private static void eliminarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();

            if (controladorClientes.encontrarClientePorRUT(rut) != null) {
                String nombreCliente = controladorClientes.encontrarClientePorRUT(rut).getNombre();

                System.out.println("Cliente encontrado");
                System.out.println("Nombre: " + nombreCliente + ", ID: " + controladorClientes.encontrarClientePorRUT(rut).getId());

                System.out.println("\nIngrese 1 para borrar al cliente de la base de datos");
                System.out.println("\nPresione cualquier otra tecla para volver al menú de gestión de clientes");

                int seleccion = scanner.nextInt();

                if (seleccion == 1) {
                    controladorClientes.eliminarCliente(rut);
                    menuClientes(scanner);
                }

                else {
                    menuClientes(scanner);
                }
            }

            else {
                System.out.println("Cliente no encontrado, intente nuevamente");
                eliminarCliente();
            }
        }
    }

    private static void buscarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();
            String res = controladorClientes.buscarCliente(rut);
            if (res != null){
                System.out.println("Cliente encontrado en la base de datos");
                System.out.println();
                System.out.println(res);
            }
            else {
                System.out.println("Cliente no encontrado en la base de datos");
            }
        }
    }

    private static void menuCuentasDeAhorro(Scanner scanner) {

        while (true) {
            try {
                System.out.println("\nGestionar cuentas de ahorro:");
                System.out.println("\n1. Crear cuenta de ahorro");
                System.out.println("2. Lista de cuentas de ahorro");
                System.out.println("3. Eliminar cuenta de ahorro");
                System.out.println("4. Buscar cuenta de ahorro");
                System.out.println("5. Volver al menú principal");

                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        crearCuentaDeAhorro(scanner);
                        break;
                    case 2:
                        //listaCuentasDeAhorro(scanner);
                        break;
                    case 3:
                        //eliminarCuentaDeAhorro();
                        break;
                    case 4:
                        //buscarCuentaDeAhorro();
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

    private static void crearCuentaDeAhorro (Scanner scanner) {

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Buscar al cliente por su RUT
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();

            if (controladorClientes.encontrarClientePorRUT(rut) != null) {
                String nombreCliente = controladorClientes.encontrarClientePorRUT(rut).getNombre();

                System.out.println("Cliente encontrado");
                System.out.println("Nombre: " + nombreCliente + ", ID: " + controladorClientes.encontrarClientePorRUT(rut).getId());

                if (controladorClientes.hayCuentaAhorro(rut)){
                    System.out.println("El cliente ya tiene una cuenta de ahorro.");
                    menuCuentasDeAhorro(scanner);
                }

                else {

                    int idCliente = controladorClientes.encontrarClientePorRUT(rut).getId();

                    System.out.println("\nIngrese los datos de la cuenta de ahorro");
                    System.out.println();

                    System.out.print("Saldo inicial: ");
                    int saldo = scanner.nextInt();

                    System.out.print("Tasa de interés: ");
                    float tasa = scanner.nextFloat();

                    System.out.print("Saldo mínimo para hacer retiros: ");
                    int tope = scanner.nextInt();

                    /*

                    try {
                        controladorCuentasDeAhorro.crearCuentaDeAhorro(idCliente, saldo, tasa, tope);
                        System.out.println("\nCuenta de ahorro creada con éxito.");
                    } catch (Exception e) {
                        System.out.println("Error al crear la cuenta de ahorro: " + e.getMessage());
                    }

                    */
                }
            }

            else {
                System.out.println("Cliente no encontrado, intente nuevamente");
                actualizarCliente();
            }
        }
    }

    public static void main(String[] args) {
        controladorClientes = new ControladorClientes();
        controladorCuentasDeAhorro = new ControladorCuentasDeAhorro();

        controladorClientes.crearCliente("Marcial", "Díaz", "marcial.diaz03@inacapmail.cl", "19.524.734-k", "+56978030199");
        controladorClientes.crearCliente("Vale", "Díaz", "vale.diaz03@inacapmail.cl", "19.960.609-9", "+56978036504");

        controladorCuentasDeAhorro.crearCuentaDeAhorro(1, 150000, 6, 100000);
        controladorCuentasDeAhorro.crearCuentaDeAhorro(2, 666000, 10, 800000);

        /*

        controlador.crearCuentaCorriente(465630, 55000);
        controlador.crearCuentaCorriente(1000000, 400000);
        */

        System.out.println("\n¡Hola! Bienvenid@ al módulo de administración del Banco Virtual");
        mostrarMenu();
    }
}
