package userinterface;

import baselayer.Cuenta;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import transferlayer.Controller;

public class Main {

    static BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
    static PrintStream out = (System.out);
    static Scanner sc = new Scanner(System.in);
    public static Controller control = new Controller();

    public static void main(String args[]) throws IOException {
        String opc = "";
        boolean noSalir = true;

        do {
            mostrarMenu();
            opc = leerOpcion();
            noSalir = ejecutarAccion(opc);
        } while (noSalir);

    }

    public static void mostrarMenu() {
        out.println("+=======================+");
        out.println("|  1. Registrar cuenta  |");
        out.println("|  2. Registrar cliente |");
        out.println("+=======================+");
        out.println();
    }

    public static String leerOpcion() throws IOException {
        String opcion = "";

        out.print("Seleccione una opción: ");
        opcion = in.readLine();
        out.println();

        return opcion;
    }

    ;

    public static boolean ejecutarAccion(String popc) throws IOException {
        boolean noSalir = true;

        switch (popc) {
            case "1":
                registrarCuenta();
                break;

            case "2":
                registrarCliente();
                break;

            case "3":
                // Función de depositar
                break;

            case "4":
                // Función de retirar
                break;

            case "5":
                // Función de listar clientes
                break;

            case "6":
                // Función de listar un cliente
                break;

            case "7":
                out.println("Hasta luego...");
                noSalir = false;
                break;

            case "8":
                break;

            default:
                out.println("Opción inválida");
                out.println();
                break;
        }

        return noSalir;
    }

    public static void registrarCuenta() throws IOException {
        String tipoUsuario = "";

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

        if (continuar.equals("n")) {
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

        if (tipo == null) {
            System.out.println("Opción invalida");
        } else {
            switch (tipo) {
                case "CC":
                    control.enviarClienteCorriente(nombre, cedula, direccion, numeroCuenta, saldoInicial);
                    out.println("\nEl cliente se registró correctamente\n");
                    break;
                case "CA":
                    control.enviarClienteAhorro(nombre, cedula, direccion, numeroCuenta, saldoInicial);
                    out.println("\nEl cliente se registró correctamente\n");
                    break;
                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }
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
                out.println("Ese cliente no está registrado  |  ¿Desea continuar con el registro? s/n");
                continuar = in.readLine();
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
                out.println("Esa cuenta ya existe  |  ¿Desea continuar con el registro? s/n");
                continuar = in.readLine();
            }
        } while (encontrado == -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        control.enviarCuentaCorriente(identificacion, numeroCuenta, saldoInicial);
        registrado = 1;

        if (registrado == 1) {
            System.out.println("Su cuenta se registró exitosamente");
        } else {
            System.out.println("No se pudo registrar la cuenta");
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
                out.println("Ese cliente no está registrado  |  ¿Desea continuar con el registro? s/n");
                continuar = in.readLine();
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
                out.println("Esa cuenta ya existe  |  ¿Desea continuar con el registro? s/n");
                continuar = in.readLine();
            }
            
        } while (encontrado != -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        control.enviarCuentaAhorro(identificacion, numeroCuenta, saldoInicial);
        registrado = 1;

        if (registrado == 1) {
            System.out.println("Su cuenta se registró exitosamente");
        } else {
            System.out.println("No se pudo registrar la cuenta");
        }
    }

    public static void registrarCuentaProgramada() throws IOException {
        String identificacion = "";
        String numero;
        String numeroCuenta;
        double saldo;
        int encontrado = 0;
        String continuar = "";
        int registrado = 0;

        do {
            
            out.print("Ingrese su identificacion: ");
            identificacion = in.readLine();

            encontrado = control.buscarCliente(identificacion);

            if (encontrado == -1) {
                out.println("Ese cliente no está registrado  |  ¿Desea continuar con el registro? s/n");
                continuar = in.readLine();
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
                out.println("Esa cuenta no existe  |  ¿Desea continuar con el registro? s/n");
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
            out.print("Ingrese el monto que quiere ahorrar mensualmente: ");
            saldo = Double.parseDouble(in.readLine());

            encontrado = control.buscarCuenta(numeroCuenta);
            
            if (encontrado != -1) {
                out.println("Esa cuenta ya existe  |  ¿Desea continuar con el registro? s/n");
                continuar = in.readLine();
            }
            
        } while (encontrado != -1 && continuar.equals("s"));

        if (continuar.equals("n")) {
            return;
        }

        control.enviarCuentaProgramada(identificacion, numero, numeroCuenta, saldo);
        registrado = 1;

        if (registrado == 1) {
            System.out.println("Su cuenta se registró exitosamente");
        } else {
            System.out.println("No se pudo registrar la cuenta");
        }
    }
    
}
