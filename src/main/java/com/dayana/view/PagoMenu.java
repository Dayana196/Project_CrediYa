package com.dayana.view;

import java.util.Scanner;
import com.dayana.service.PagoService;

public class PagoMenu {

    private final PagoService servicio = new PagoService();
    private final Scanner input = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n========== MENÚ PAGOS ==========");
            System.out.println("1. Registrar abono a préstamo");
            System.out.println("2. Lista de pagos");
            System.out.println("3. Consultar pagos por cliente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            while (!input.hasNextInt()) {
                System.out.println("Error: ingrese solo números");
                input.next();
            }
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1 -> servicio.registrarPago(input);
                case 2 -> servicio.listarPagos();
                case 3 -> servicio.consultarPagosCliente(input);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida, intente nuevamente");
            }

        } while (opcion != 0);
    }
}
