package com.dayana.view;

import com.dayana.service.GeneradorReportes;
import java.util.Scanner;

public class ReportesMenu {
    GeneradorReportes generador = new GeneradorReportes();

    public void mostrarMenu() {
        try (Scanner input = new Scanner(System.in)) {
            int opcion;

            do {
                System.out.println("""
                    ------------- Menu de Reportes -------------
                        1. Generar Reporte de Prestamos
                        0. Volver al Menu Principal
                    ----------------------------------------
                    """);
                System.out.print("Seleccione una opcion: ");
                opcion = input.nextInt();
                input.nextLine();

                switch (opcion) {
                    case 1 -> generador.generarReportePrestamos();
                    case 0 -> System.out.println("Volver al Menu Principal");
                    default -> System.out.println("Opcion no valida, por favor intente de nuevo.");
                }
            } while (opcion != 0);
        }
    }
}

