package com.dayana.view;

import com.dayana.service.ClienteService;
import java.util.Scanner;

public class ClienteMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final ClienteService servicio = new ClienteService();

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n--- MENU CLIENTES ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error: ingrese solo numeros");
                scanner.next();
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> servicio.registrarCliente(scanner);
                case 2 -> servicio.listaClientes();
            }

        } while (opcion != 0);
    }
}
