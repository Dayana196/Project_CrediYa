package com.dayana.view;

import com.dayana.model.Prestamo;
import com.dayana.model.Cliente;
import com.dayana.model.Empleado;
import com.dayana.service.PrestamoService;
import com.dayana.service.ClienteService;
import com.dayana.service.EmpleadoService;

import java.util.Scanner;

public class PrestamoMenu {

    private PrestamoService prestamoService;
    private ClienteService clienteService;
    private EmpleadoService empleadoService;
    private Scanner scanner = new Scanner(System.in);

    public PrestamoMenu(ClienteService clienteService,
                        EmpleadoService empleadoService,
                        PrestamoService prestamoService) {
        this.clienteService = clienteService;
        this.empleadoService = empleadoService;
        this.prestamoService = prestamoService;
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n--- MENU PRESTAMOS ---");
            System.out.println("1. Crear prestamo");
            System.out.println("2. Listar prestamos");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Ingrese solo numeros");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearPrestamo();
                case 2 -> listarPrestamos();
            }

        } while (opcion != 0);
    }

    private void crearPrestamo() {

        System.out.print("Documento del cliente: ");
        String docCliente = scanner.nextLine();
        Cliente cliente = clienteService.buscarPorDocumento(docCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }

        System.out.print("Documento del empleado: ");
        String docEmpleado = scanner.nextLine();
        Empleado empleado = empleadoService.buscarPorDocumento(docEmpleado);

        if (empleado == null) {
            System.out.println("Empleado no encontrado");
            return;
        }

        System.out.print("ID prestamo: ");
        int id = scanner.nextInt();

        System.out.print("Monto: ");
        double monto = scanner.nextDouble();

        System.out.print("Interes (ej: 0.1 = 10%): ");
        double interes = scanner.nextDouble();

        System.out.print("Plazo en meses: ");
        int plazo = scanner.nextInt();

        Prestamo prestamo = new Prestamo(id, cliente, empleado, monto, interes, plazo);

        prestamoService.registrarPrestamo(prestamo);

        System.out.println("Prestamo creado");
        System.out.println("Monto total: " + prestamo.calcularMontoTotal());
        System.out.println("Cuota mensual: " + prestamo.calcularCuotaMensual());
    }

    private void listarPrestamos() {
        if (prestamoService.listarPrestamos().isEmpty()) {
            System.out.println("No hay prestamos registrados");
            return;
        }

        System.out.println("\n----------------------------------------------------------------------------");
        System.out.printf("%-4s %-15s %-15s %-10s %-10s %-12s %-10s%n",
                "ID", "CLIENTE", "EMPLEADO", "MONTO", "CUOTA", "SALDO", "ESTADO");
        System.out.println("----------------------------------------------------------------------------");

        prestamoService.listarPrestamos().forEach(p -> {
            System.out.printf("%-4d %-15s %-15s %-10.2f %-10.2f %-12.2f %-10s%n",
                    p.getId(),
                    p.getCliente().getNombre(),
                    p.getEmpleado().getNombre(),
                    p.getMonto(),
                    p.calcularCuotaMensual(),
                    p.getSaldoPendiente(),
                    p.getEstado());
        });

        System.out.println("----------------------------------------------------------------------------");
    }
}
