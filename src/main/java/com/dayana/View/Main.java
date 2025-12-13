package com.dayana.view;

import java.util.Scanner;

import com.dayana.service.ClienteService;
import com.dayana.service.EmpleadoService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 🔹 CREAR LOS SERVICES
        ClienteService clienteService = new ClienteService();
        EmpleadoService empleadoService = new EmpleadoService();

        EmpleadoMenu empleadoMenu = new EmpleadoMenu(empleadoService);
        ClienteMenu clienteMenu = new ClienteMenu(clienteService);
        PrestamoMenu prestamoMenu = new PrestamoMenu(clienteService, empleadoService);


        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE COBROS CREDIYA ===");
            System.out.println("1. Módulo Empleados");
            System.out.println("2. Módulo Clientes");
            System.out.println("3. Módulo Préstamos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error: ingrese solo números");
                scanner.next();
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> empleadoMenu.mostrarMenu();
                case 2 -> clienteMenu.mostrarMenu();
                case 3 -> prestamoMenu.mostrarMenu();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }
}
