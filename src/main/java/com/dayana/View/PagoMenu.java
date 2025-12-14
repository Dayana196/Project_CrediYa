package com.dayana.view;

import com.dayana.service.PrestamoService;
import com.dayana.service.PagoService;
import com.dayana.model.Prestamo;
import com.dayana.model.Pago;

import java.util.Scanner;

public class PagoMenu {

    private PrestamoService prestamoService;
    private PagoService pagoService;
    private Scanner scanner = new Scanner(System.in);

    public PagoMenu(PrestamoService prestamoService, PagoService pagoService) {
        this.prestamoService = prestamoService;
        this.pagoService = pagoService;
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n--- MENU PAGOS ---");
            System.out.println("1. Registrar pago");
            System.out.println("2. Listar pagos");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Ingrese solo numeros");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> registrarPago();
                case 2 -> listarPagos();
            }

        } while (opcion != 0);
    }

    private void registrarPago() {
        System.out.print("ID del prestamo: ");
        int prestamoId = scanner.nextInt();
        scanner.nextLine();

        Prestamo prestamo = prestamoService.buscarPorId(prestamoId);
        if (prestamo == null) {
            System.out.println("Prestamo no encontrado");
            return;
        }

        System.out.print("Monto del pago: ");
        double monto = scanner.nextDouble();

        Pago pago = new Pago(prestamo, monto);
        pagoService.registrarPago(pago);

        System.out.println("Pago registrado correctamente");
    }

    private void listarPagos() {
        if (pagoService.listarPagos().isEmpty()) {
            System.out.println("No hay pagos registrados");
            return;
        }

        System.out.println("\n--------------------------------------------");
        System.out.printf("%-4s %-10s %-10s%n", "ID", "PRESTAMO", "MONTO");
        System.out.println("--------------------------------------------");

        pagoService.listarPagos().forEach(p -> {
            System.out.printf("%-4d %-10d %-10.2f%n",
                    p.getId(),
                    p.getPrestamo().getId(),
                    p.getMonto());
        });

        System.out.println("--------------------------------------------");
    }
}
