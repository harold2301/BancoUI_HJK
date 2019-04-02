package userinterface;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import transferlayer.Controller;

public class Main {

    static BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
    static PrintStream out = (System.out);
    static Scanner sc = new Scanner(System.in);

    public static Controller control = new Controller();

    public static void main(String args[]) throws IOException {
        
        String [] cuentasProgra;
        cuentasProgra = obtenerCuentasProgramadas();
        retiroAutomatico(cuentasProgra);
        
        String opc = "";
        boolean noSalir = true;

        do {
            mostrarMenu();
            opc = leerOpcion();
            noSalir = ejecutarAccion(opc);
        } while (noSalir);

    }

    public static void mostrarMenu() {
        out.println("+========================+");
        out.println("|  1. Registrar cliente  |");
        out.println("|  2. Registrar cuenta   |");
        out.println("|  3. Depositar          |");
        out.println("|  4. Retirar            |");
        out.println("|  5. Listar clientes    |");
        out.println("|  6. Listar un cliente  |");
        out.println("|  7. Salir              |");
        out.println("+========================+");
        out.println();
    }

    public static String leerOpcion() throws IOException {
        String opcion = "";

        out.print("Seleccione una opción: ");
        opcion = in.readLine();
        out.println();

        return opcion;
    }

    public static boolean ejecutarAccion(String popc) throws IOException {
        boolean noSalir = true;

        switch (popc) {
            case "1":
                // Función que registra a un nuevo cliente
                registrarCliente();
                break;

            case "2":
                // Función que registra una nueva cuenta a un cliente ya registrado
                registrarCuenta();
                break;

            case "3":
                //Función para depositar
                depositarCuenta();
                break;
            case "4":
                // Función de retirar
                retirarCuenta();
                break;
            case "5":
                // Función que lista a todos los clientes
                listarClientes();
                break;
            case "6":
                // Función que lista un cliente
                listarClienteEsp();
                break;
            case "7":
                out.println("Hasta luego...");
                noSalir = false;
                break;
            default:
                out.println("Opción inválida");
                out.println();
                break;
        }

        return noSalir;
    }

    public static void registrarCuenta() throws IOException {
        String tipoUsuario;

        out.println("Cuenta corriente  |  Cuenta de ahorro  |  Cuenta de ahorro programada");
        out.println("       CC         |         CA         |               CP");
        out.print("\nSeleccione un tipo de cuenta: ");
        tipoUsuario = in.readLine();
        out.println();

        switch (tipoUsuario) {
            case "CC":
                registrarCuentaCorriente();
                break;

            case "CA":
                registrarCuentaAhorro();
                break;

            case "CP":
                registrarCuentaProgramada();
                break;
            default:
                out.println("opción inválida");
                out.println();
                break;
        }
    }

    public static void registrarCliente() throws IOException {
        String nombre;
        String cedula;
        String direccion;
        String numeroCuenta;
        double saldoInicial;
        String tipo;
        int encontrado = 0;
        String continuar = "";

        do {

            System.out.print("Ingrese el nombre del cliente: ");
            nombre = sc.next();

            System.out.print("Ingrese la cédula del cliente: ");
            cedula = sc.next();

            System.out.print("Ingrese la direccion del cliente: ");
            direccion = sc.next();

            encontrado = control.buscarCliente(cedula);

            if (encontrado != -1) {
                out.print("\nEse cliente ya está registrado  |  ¿Desea continuar con el registro? s/n: ");
                continuar = in.readLine();
                out.println();
            }

        } while (encontrado != -1 && continuar.equals("s"));

        if (continuar.equals("n")) {  // Si la respuesta de continuar es no la función termina con la palabra return
            return;
        }

        encontrado = 0;
        continuar = "";

        do {

            System.out.println("\n      Registro de la cuenta del cliente     ");
            System.out.println("|  CC. Cuenta Corriente  |  CA. Cuenta de ahorro  |");
            System.out.print("\nSeleccione una opción: ");
            tipo = sc.next();
            System.out.println();

            System.out.print("Ingrese el número de cuenta: ");
            numeroCuenta = sc.next();
            System.out.print("Ingrese el saldo inicial de la cuenta: ");
            saldoInicial = sc.nextDouble();

            encontrado = control.buscarCuenta(numeroCuenta);

            if (encontrado != -1) {
                out.print("\nEsa cuenta ya está registrada  |  ¿Desea continuar con el registro? s/n: ");
                continuar = in.readLine();
                out.println();
            }

        } while (encontrado != -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        control.enviarCliente(nombre, cedula, direccion, numeroCuenta, saldoInicial, tipo);

        out.println("\nEl cliente se registró correctamente\n");
    }

    public static void registrarCuentaCorriente() throws IOException {

        String identificacion;
        String numeroCuenta;
        double saldoInicial;
        int encontrado = 0;
        String continuar = "";
        int registrado = 0;

        do {
            System.out.print("Ingrese su identificación: ");
            identificacion = sc.next();

            encontrado = control.buscarCliente(identificacion);

            if (encontrado == -1) {
                out.print("\nEse cliente no está registrado  |  ¿Desea continuar con el registro? s/n: ");
                continuar = in.readLine();
                out.println();
            }
        } while (encontrado == -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        encontrado = 0;
        continuar = "";

        do {
            System.out.print("Ingrese el número de la nueva cuenta: ");
            numeroCuenta = sc.next();
            System.out.print("Ingrese el saldo inicial de la nueva cuenta: ");
            saldoInicial = sc.nextDouble();

            encontrado = control.buscarCuenta(numeroCuenta);

            if (encontrado != -1) {
                out.print("\nEsa cuenta ya existe  |  ¿Desea continuar con el registro? s/n: ");
                continuar = in.readLine();
                out.println();
            }
        } while (encontrado != -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        control.enviarCuenta(identificacion, numeroCuenta, saldoInicial, "CC");
        registrado = 1;

        if (registrado == 1) {
            System.out.println("\nSu cuenta se registró exitosamente\n");
        } else {
            System.out.println("\nNo se pudo registrar la cuenta\n");
        }
    }

    public static void registrarCuentaAhorro() throws IOException {
        String identificacion;
        String numeroCuenta;
        double saldoInicial;
        int encontrado = 0;
        String continuar = "";
        int registrado = 0;

        do {
            System.out.print("Ingrese su identificación: ");
            identificacion = sc.next();

            encontrado = control.buscarCliente(identificacion);

            if (encontrado == -1) {
                out.print("\nEse cliente no está registrado  |  ¿Desea continuar con el registro? s/n: ");
                continuar = in.readLine();
                out.println();
            }
        } while (encontrado == -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        encontrado = 0;
        continuar = "";

        do {

            System.out.print("Ingrese el número de la nueva cuenta de ahorro: ");
            numeroCuenta = sc.next();
            System.out.print("Ingrese el saldo inicial de la nueva cuenta de ahorra: ");
            saldoInicial = sc.nextDouble();

            encontrado = control.buscarCuenta(numeroCuenta);

            if (encontrado != -1) {
                out.print("\nEsa cuenta ya existe  |  ¿Desea continuar con el registro? s/n: ");
                continuar = in.readLine();
            }

        } while (encontrado != -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        control.enviarCuenta(identificacion, numeroCuenta, saldoInicial, "CA");
        registrado = 1;

        if (registrado == 1) {
            System.out.println("\nSu cuenta se registró exitosamente\n");
        } else {
            System.out.println("\nNo se pudo registrar la cuenta\n");
        }
    }

    public static void registrarCuentaProgramada() throws IOException {
        String identificacion = "";
        String numero;
        String numeroCuenta;
        LocalDate fechaCreacion = LocalDate.now();
        int encontrado = 0;
        String continuar = "";
//        System.out.println(fechaCreacion);

        do {

            out.print("Ingrese su identificacion: ");
            identificacion = in.readLine();

            encontrado = control.buscarCliente(identificacion);

            if (encontrado == -1) {
                out.println("\nEse cliente no está registrado  |  ¿Desea continuar con el registro? s/n: ");
                continuar = in.readLine();
                out.println();
            }

        } while (encontrado == -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        encontrado = 0;
        continuar = "";

        do {
            out.print("Ingrese el número de su cuenta corriente: ");
            numero = in.readLine();

            encontrado = control.buscarCuenta(numero);

            if (encontrado == -1) {
                out.print("\nEsa cuenta no existe  |  ¿Desea continuar con el registro? s/n: ");
                continuar = in.readLine();
            }

        } while (encontrado == -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        encontrado = 0;
        continuar = "";

        do {

            out.print("Ingrese el número de la cuenta de ahorro programada: ");
            numeroCuenta = in.readLine();

            encontrado = control.buscarCuenta(numeroCuenta);

            if (encontrado != -1) {
                out.print("\nEsa cuenta ya existe  |  ¿Desea continuar con el registro? s/n: ");
                continuar = in.readLine();
                out.println();
            }

        } while (encontrado != -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        control.enviarCuentaProgramada(numero, numeroCuenta);

        System.out.println("\nSu cuenta se registró exitosamente\n");
    }

    public static void listarClientes() {
        String[] lista = control.listaClientes();

        for (String info : lista) {
            out.println(info);
            out.println();
        }
    }

    public static void listarClienteEsp() throws IOException {

        String identificacion = "";
        String continuar = "";
        int encontrado;

        do {
            System.out.print("Ingrese la identificación del cliente: ");
            identificacion = in.readLine();

            encontrado = control.buscarCliente(identificacion);

            if (encontrado == -1) {
                out.print("\nEse cliente no está registrado  |  ¿Desea buscar otro cliente? s/n: ");
                continuar = in.readLine();
                out.println();
            }
        } while (encontrado == -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        String cliente = "\n" + control.mostrarCliente(identificacion) + "\n"; // Contiene la información del cliente
        out.println(cliente);
    }

    public static String[] obtenerCuentasProgramadas(){
       String [] cuentas = null; 
        return cuentas;
    }
    public static void retiroAutomatico(String[] cuentas) {

    }

    public static void depositarCuenta() throws IOException {
        double monto;

        String numero;

            out.print("Digite el número de cuenta a la cual desea depositar: ");
            numero = in.readLine();

            int encontrado = control.buscarCuenta(numero);

            if (encontrado == -1) {
                out.println("\nEsa cuenta no existe ");
            }else if(encontrado != -1){  
            out.print("Digite cuanto desea depositar: ");
             monto = Double.parseDouble(in.readLine());
            control.enviarDeposito(numero, monto);
            out.print("Deposito efectuado");
     }
            
    }

    public static void retirarCuenta() throws IOException {
        double monto;

        String numero;
    
            out.print("Digite el número de cuenta a la cual desea retirar: ");
            numero = in.readLine();

            int encontrado = control.buscarCuenta(numero);

            if (encontrado == -1) {
                out.print("\nEsa cuenta no existe ");
            }else {  
            out.print("Digite cuanto desea retirar: ");
             monto = Double.parseDouble(in.readLine());
           control.enviarRetiro(numero,monto);
            
            out.print("Retiro efectuado");
            }
    }
}

