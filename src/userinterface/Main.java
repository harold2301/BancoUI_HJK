package userinterface;

import java.io.*;
import transferlayer.Controller;

public class Main { 

    static BufferedReader in = new BufferedReader(
            new InputStreamReader(System.in));
    static PrintStream out = (System.out);

    public static Controller control = new Controller();
    
    public static void main(String args[]) throws IOException {
        String opc = "";
        boolean noSalir = true;

        do {
            mostrarMenu();
            opc = leerOpcion();
            noSalir = ejecutarAccion(opc);
        } while (noSalir);

    };

    public static void mostrarMenu() {
        out.println("+=======================+");
        out.println("|  1. Registrar cuenta  |");
        out.println("+=======================+");
        out.println();
    };

    public static String leerOpcion() throws IOException {
        String opcion = "";

        out.print("Seleccione una opción: ");
        opcion = in.readLine();
        out.println();

        return opcion;
    };

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
    };
    
    public static void registrarCuenta()throws IOException {
        String tipoUsuario = "";
        
        out.println("Cuenta corriente  |  Cuenta de ahorro  |  Cuenta de ahorro programada");
        out.println("       CC         |         CA         |               CP");
        out.print("\nSeleccione un tipo de cuenta: ");
        tipoUsuario = in.readLine();
        out.println();
        
        switch (tipoUsuario) {
            case "CC":
                break;
                
            case "CA":
                break;
                
            case "CP":
                break;
            
            default:
                out.println("opción inválida");
                out.println();
                break;
        }
    };
    
   
    
    public static void listarMateriales() {
//        String[] lista = control.listaMateriales();
//        
//        for (String info : lista) {
//            out.println(info);
//        }
    };
}
