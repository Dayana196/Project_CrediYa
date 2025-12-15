package com.dayana.view;

import java.util.Scanner;

import com.dayana.service.PrestamosService;

public class PrestamosMenu {
    PrestamosService servicio = new PrestamosService();

    public void prestamosMenu() {
        Scanner input = new Scanner(System.in);
                int opcion;

        do {
            System.out.println("""
                ------------- Prestamos Menu -------------
                    1. Crear Prestamo
                    2. SimulaciÃ³n de Prestamo
                    3. Cambiar Estado Prestamo
                    4. Consultar Prestamo
                    5. Lista de Prestamos
                    0. Volver al Menu Principal
                ----------------------------------------
                """);
            System.out.print("Seleccione una opcion: ");
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1 -> servicio.crearPrestamoCliente(input);
                case 2 -> servicio.SimulacionPrestamos(input);
                case 3 -> servicio.cambiarEstado(input);
                case 4 -> prestamosSubMenu(input);
                case 5 -> servicio.listaPrestamo();
                case 0 -> System.out.println("Volver al Menu Principal");
                default -> System.out.println("Opcion no valida, por favor intente de nuevo.");
            }
        } while (opcion != 0);
        
    }

    public void prestamosSubMenu(Scanner input) {
        int opcion;

        do {
            System.out.println("""
                ------------- Prestamos Menu -------------
                    1. Prestamos Clientes
                    2. Prestamos Realizado por Empleado
                    0. Salor al menu prestamo
                ----------------------------------------
                """);
            System.out.print("Seleccione una opcion: ");
            opcion = input.nextInt();
            input.nextLine();

            switch (opcion) {
                case 1 -> servicio.consultarPrestamosCliente(input);
                case 2 -> servicio.consultarPrestamosEmpleado(input);
                case 0 -> System.out.println("Volver al Menu Principal");
                default -> System.out.println("Opcion no valida, por favor intente de nuevo.");
            }
        } while (opcion != 0);
        
    }
}
