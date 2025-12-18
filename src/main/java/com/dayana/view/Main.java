package com.dayana.view;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE COBROS CREDIYA ===");
            System.out.println("1. Modulo Empleados");
            System.out.println("2. Modulo Clientes");
            System.out.println("3. Modulo Prestamos");
            System.out.println("4. Modulo Pagos");
            System.out.println("5. Generar Reportes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");


            while (!scanner.hasNextInt()) {
                System.out.println("Error: ingrese solo numeros");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> new EmpleadoMenu().mostrarMenu();
                case 2 -> new ClienteMenu().mostrarMenu();
                case 3 -> new PrestamosMenu().prestamosMenu();
                case 4 -> new PagoMenu().mostrarMenu();
                case 5 -> new ReportesMenu().mostrarMenu();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida");
            }

        } while (opcion != 0);

        scanner.close();
    }
}




