package userinterface;

import java.io.*;
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

    ;

    public static void mostrarMenu() {
        out.println("+=======================+");
        out.println("|  1. Registrar cuenta  |");
        out.println("+=======================+");
        out.println();
    }

    ;

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
                break;

            case "3":
                break;

            case "4":
                break;

            case "5":
                out.println("Hasta luego...");
                noSalir = false;
                break;

            case "6":
                break;

            default:
                out.println("Opción inválida");
                out.println();
                break;
        }

        return noSalir;
    }

    ;
    
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
                break;

            default:
                out.println("opción inválida");
                out.println();
                break;
        }
    }
    
   public static void registrarCuentaCorriente() {

        String numeroCuenta;
        double saldoInicial;

        System.out.println("Ingrese el número de su cuenta corriente: ");
        numeroCuenta = sc.next();
        System.out.println("Ingrese el saldo inicial de la cuenta corriente: ");
        saldoInicial = sc.nextDouble();
        int valida = control.enviarCuentaCorriente(numeroCuenta, saldoInicial);
        if (valida == -1) {
            System.out.println("Su cuenta se registró exitosamente");
        } else {
            System.out.println("Ya existe una cuenta con ese número de cuenta"
                    + " , favor ingrese otro número de cuenta");
        }
    }

    public static void registrarCuentaAhorro() {

        String numeroCuenta;
        double saldoInicial;

        System.out.println("Ingrese el número de su cuenta ahorro: ");
        numeroCuenta = sc.next();
        System.out.println("Ingrese el saldo inicial de la cuenta ahorro: ");
        saldoInicial = sc.nextDouble();
        int valida = control.enviarCuentaAhorro(numeroCuenta, saldoInicial);
        if (valida == -1) {
            System.out.println("Su cuenta se registró exitosamente");
        } else {
            System.out.println("Ya existe una cuenta con ese número de cuenta"
                    + " , favor ingrese otro número de cuenta");
        }
    }
    
    public static void registrarCuentaProgramada() throws IOException {
       String numero = "";
       double saldo = 0.0;
       
       System.out.print("Ingrese el número de su cuenta corriente: ");
       numero = in.readLine();
       System.out.print("Ingrese el monto que quiere ahorrar mensualmente: ");
       saldo = Double.parseDouble(in.readLine());
       
       int registrado = control.enviarCuenta(numero, saldo);
       
       if (registrado != -1) {
           out.println("La cuenta de ahorro se registró correctamente");
       } else {
           out.println("No se pudo hace la cuenta de ahorro porque la cuenta ingresada no existe");
       }
   }

    public static void listarMateriales() {
//        String[] lista = control.listaMateriales();
//        
//        for (String info : lista) {
//            out.println(info);
//        }
    }
;
}
