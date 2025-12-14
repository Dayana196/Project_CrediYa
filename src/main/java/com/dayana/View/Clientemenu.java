package com.dayana.view;

import com.dayana.model.Cliente;
import com.dayana.service.ClienteService;
import java.util.Scanner;

public class ClienteMenu {

    private ClienteService clienteService;
    private Scanner scanner = new Scanner(System.in);

    public ClienteMenu(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

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
                case 1 -> registrarCliente();
                case 2 -> listarClientes();
            }

        } while (opcion != 0);
    }

    private void registrarCliente() {

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Documento: ");
        String documento = scanner.nextLine();

        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        System.out.print("Telefono: ");
        String telefono = scanner.nextLine();

        Cliente cliente = new Cliente(id, nombre, documento, correo, telefono);
        clienteService.registrarCliente(cliente);

        System.out.println("Cliente registrado correctamente");
    }

    private void listarClientes() {

        if (clienteService.listarClientes().isEmpty()) {
            System.out.println("No hay clientes registrados");
            return;
        }

        System.out.println("\n-------------------------------------------------------------------");
        System.out.printf("%-4s %-18s %-15s %-25s %-10s%n",
                "ID", "NOMBRE", "DOCUMENTO", "CORREO", "TELEFONO");
        System.out.println("-------------------------------------------------------------------");

        clienteService.listarClientes().forEach(c -> {
            System.out.printf("%-4d %-18s %-15s %-25s %-10s%n",
                    c.getId(),
                    c.getNombre(),
                    c.getDocumento(),
                    c.getCorreo(),
                    c.getTelefono());
        });

        System.out.println("-------------------------------------------------------------------");
    }
}
