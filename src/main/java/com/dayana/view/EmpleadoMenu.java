package com.dayana.view;

import java.util.Scanner;

import com.dayana.service.EmpleadoService;

public class EmpleadoMenu {

    private final Scanner scanner = new Scanner(System.in);
    EmpleadoService servicio = new EmpleadoService();

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n--- MENU CLIENTES ---");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Listar empleado");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error: ingrese solo numeros");
                scanner.next();
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> servicio.registrarEmpleado(scanner);
                case 2 -> servicio.listaEmpleado();
            }

        } while (opcion != 0);
    }
}

