package Vista;

import Controlador.*;

        import java.util.Scanner;

public class BancoVirtual {
    private static ControladorClientes controladorClientes;
    private static ControladorCuentasDeAhorro controladorCuentasDeAhorro;
    private static ControladorCuentasCorrientes controladorCuentasCorrientes;

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
                        menuCuentasCorrientes(scanner);
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
                menuClientes(scanner);
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
                menuClientes(scanner);
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
            controladorClientes.buscarCliente(rut);
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
                        listaCuentasDeAhorro(scanner);
                        break;
                    case 3:
                        eliminarCuentaDeAhorro(scanner);
                        break;
                    case 4:
                        buscarCuentaDeAhorro(scanner);
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

                    try {
                        controladorCuentasDeAhorro.crearCuentaDeAhorro(idCliente, saldo, tasa, tope);
                        System.out.println("\nCuenta de ahorro creada con éxito.");
                    } catch (Exception e) {
                        System.out.println("Error al crear la cuenta de ahorro: " + e.getMessage());
                    }
                }
            }

            else {
                System.out.println("Cliente no encontrado, intente nuevamente");
                menuCuentasDeAhorro(scanner);
            }
        }
    }

    private static void listaCuentasDeAhorro(Scanner scanner) {
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay cuentas de ahorro en la base de datos.");
        } else {
            // Mostrar la lista de cuentas de ahorro
            System.out.println("Cuentas de ahorro en el banco: ");
            System.out.println();
            controladorCuentasDeAhorro.mostrarDetallesCuentasDeAhorro();
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
                        menuCuentasDeAhorro(scanner);
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

    private static void eliminarCuentaDeAhorro(Scanner scanner) {
        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados en el banco.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente para eliminar su cuenta de ahorro: ");
            System.out.println();
            String rut = scanner.next();

            if (controladorClientes.encontrarClientePorRUT(rut) != null) {
                String nombreCliente = controladorClientes.encontrarClientePorRUT(rut).getNombre();

                System.out.println("Cliente encontrado");
                System.out.println("Nombre: " + nombreCliente + ", ID: " + controladorClientes.encontrarClientePorRUT(rut).getId());

                if (controladorClientes.hayCuentaAhorro(rut)) {
                    System.out.println();
                    System.out.println("Datos de la cuenta:");
                    System.out.println("ID: " + controladorClientes.encontrarClientePorRUT(rut).getCuentaDeAhorro().getId() + ", Saldo: " + controladorClientes.encontrarClientePorRUT(rut).getCuentaDeAhorro().getSaldo());

                    System.out.println("\nIngrese 1 para borrar la cuenta de ahorro del cliente");
                    System.out.println("\nPresione cualquier otra tecla para volver al menú de gestión de clientes");

                    int seleccion = scanner.nextInt();

                    if (seleccion == 1) {
                        controladorCuentasDeAhorro.eliminarCuentaDeAhorro(rut);
                        menuCuentasDeAhorro(scanner);
                    }

                    else {
                        menuCuentasDeAhorro(scanner);
                    }
                }
                else {
                    System.out.println("El cliente seleccionado no posee una cuenta de ahorro");
                    menuCuentasDeAhorro(scanner);
                }
            }

            else {
                System.out.println("Cliente no encontrado");
                menuCuentasDeAhorro(scanner);
            }
        }
    }

    private static void buscarCuentaDeAhorro(Scanner scanner) {
        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados en el banco.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();
            controladorCuentasDeAhorro.buscarCuentaDeAhorro(rut);
            menuCuentasDeAhorro(scanner);
        }
    }

    private static void menuCuentasCorrientes(Scanner scanner){
        while (true) {
            try {
                System.out.println("\nGestionar cuentas corrientes:");
                System.out.println("\n1. Crear cuenta corriente");
                System.out.println("2. Lista de cuentas corrientes");
                System.out.println("3. Eliminar cuenta corriente");
                System.out.println("4. Buscar cuenta corriente");
                System.out.println("5. Volver al menú principal");

                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        crearCuentaCorriente(scanner);
                        break;
                    case 2:
                        listaCuentasCorrientes(scanner);
                        break;
                    case 3:
                        eliminarCuentaCorriente(scanner);
                        break;
                    case 4:
                        buscarCuentaCorriente(scanner);
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

    private static void crearCuentaCorriente (Scanner scanner) {

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

                if (controladorClientes.hayCuentaCorriente(rut)){
                    System.out.println("El cliente ya tiene una cuenta corriente.");
                    menuCuentasCorrientes(scanner);
                }

                else {

                    int idCliente = controladorClientes.encontrarClientePorRUT(rut).getId();

                    System.out.println("\nIngrese los datos de la cuenta corriente");
                    System.out.println();

                    System.out.print("Saldo inicial: ");
                    int saldo = scanner.nextInt();

                    System.out.print("Cupo de sobregiro: ");
                    int sobregiro = scanner.nextInt();

                    try {
                        controladorCuentasCorrientes.crearCuentaCorriente(idCliente, saldo, sobregiro);
                        System.out.println("\nCuenta corriente creada con éxito.");
                    } catch (Exception e) {
                        System.out.println("Error al crear la cuenta corriente: " + e.getMessage());
                    }
                }
            }

            else {
                System.out.println("Cliente no encontrado, intente nuevamente");
                menuCuentasCorrientes(scanner);
            }
        }
    }

    private static void listaCuentasCorrientes(Scanner scanner) {
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay cuentas corrientes en la base de datos.");
        } else {
            // Mostrar la lista de cuentas corrientes
            System.out.println("Cuentas corrientes en el banco: ");
            System.out.println();
            controladorCuentasCorrientes.mostrarDetallesCuentasCorrientes();
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
                        menuCuentasCorrientes(scanner);
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

    private static void eliminarCuentaCorriente(Scanner scanner) {
        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados en el banco.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente para eliminar su cuenta corriente: ");
            System.out.println();
            String rut = scanner.next();

            if (controladorClientes.encontrarClientePorRUT(rut) != null) {
                String nombreCliente = controladorClientes.encontrarClientePorRUT(rut).getNombre();

                System.out.println("Cliente encontrado");
                System.out.println("Nombre: " + nombreCliente + ", ID: " + controladorClientes.encontrarClientePorRUT(rut).getId());

                if (controladorClientes.hayCuentaCorriente(rut)) {
                    System.out.println();
                    System.out.println("Datos de la cuenta:");
                    System.out.println("ID: " + controladorClientes.encontrarClientePorRUT(rut).getCuentaCorriente().getId() + ", Saldo: " + controladorClientes.encontrarClientePorRUT(rut).getCuentaCorriente().getSaldo());

                    System.out.println("\nIngrese 1 para borrar la cuenta de ahorro del cliente");
                    System.out.println("\nPresione cualquier otra tecla para volver al menú de gestión de clientes");

                    int seleccion = scanner.nextInt();

                    if (seleccion == 1) {
                        controladorCuentasCorrientes.eliminarCuentaCorriente(rut);
                        menuCuentasCorrientes(scanner);
                    }

                    else {
                        menuCuentasCorrientes(scanner);
                    }
                }
                else {
                    System.out.println("El cliente seleccionado no posee una cuenta corriente");
                    menuCuentasCorrientes(scanner);
                }
            }

            else {
                System.out.println("Cliente no encontrado");
                menuCuentasCorrientes(scanner);
            }
        }
    }

    private static void buscarCuentaCorriente(Scanner scanner) {
        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados en el banco.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();
            controladorCuentasCorrientes.buscarCuentaCorriente(rut);
            menuCuentasCorrientes(scanner);
        }
    }

    public static void main(String[] args) {
        controladorClientes = new ControladorClientes();
        controladorCuentasDeAhorro = new ControladorCuentasDeAhorro();
        controladorCuentasCorrientes = new ControladorCuentasCorrientes();

        if (controladorCuentasDeAhorro.hayCuentasDeAhorro()){
            controladorClientes.agregarCuentasDeAhorro(controladorCuentasDeAhorro.getCuentasDeAhorro());
        }

        if (controladorCuentasCorrientes.hayCuentasCorrientes()){
            controladorClientes.agregarCuentasCorrientes(controladorCuentasCorrientes.getCuentasCorrientes());
        }

        System.out.println("\n¡Hola! Bienvenid@ al módulo de administración del Banco Virtual");
        mostrarMenu();
    }
}
