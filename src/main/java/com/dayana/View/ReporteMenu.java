package com.dayana.view;

import com.dayana.service.ReporteService;

import java.util.Scanner;

public class ReporteMenu {

    private ReporteService reporteService;
    private Scanner scanner = new Scanner(System.in);

    public ReporteMenu(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    public void mostrarMenu() {

        int opcion;

        do {
            System.out.println("\n--- MENÚ REPORTES ---");
            System.out.println("1. Préstamos activos");
            System.out.println("2. Préstamos pagados");
            System.out.println("3. Total prestado");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> reporteService.prestamosActivos()
                        .forEach(p -> System.out.println(p.getId()));
                case 2 -> reporteService.prestamosPagados()
                        .forEach(p -> System.out.println(p.getId()));
                case 3 -> System.out.println("Total: " + reporteService.totalPrestado());
            }

        } while (opcion != 0);
    }
}
