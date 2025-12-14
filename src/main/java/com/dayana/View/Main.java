package com.dayana.view;

import com.dayana.service.EmpleadoService;
import com.dayana.service.ClienteService;
import com.dayana.service.PrestamoService;
import com.dayana.service.PagoService;

import com.dayana.view.EmpleadoMenu;
import com.dayana.view.ClienteMenu;
import com.dayana.view.PrestamoMenu;
import com.dayana.view.PagoMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EmpleadoService empleadoService = new EmpleadoService();
        ClienteService clienteService = new ClienteService();
        PrestamoService prestamoService = new PrestamoService(clienteService, empleadoService);
        PagoService pagoService = new PagoService(prestamoService);

        EmpleadoMenu empleadoMenu = new EmpleadoMenu(empleadoService);
        ClienteMenu clienteMenu = new ClienteMenu(clienteService);
        PrestamoMenu prestamoMenu = new PrestamoMenu(clienteService, empleadoService, prestamoService);
        PagoMenu pagoMenu = new PagoMenu(prestamoService, pagoService);

        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE COBROS CREDIYA ===");
            System.out.println("1. Modulo Empleados");
            System.out.println("2. Modulo Clientes");
            System.out.println("3. Modulo Prestamos");
            System.out.println("4. Modulo Pagos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error: ingrese solo numeros");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> empleadoMenu.mostrarMenu();
                case 2 -> clienteMenu.mostrarMenu();
                case 3 -> prestamoMenu.mostrarMenu();
                case 4 -> pagoMenu.mostrarMenu();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
