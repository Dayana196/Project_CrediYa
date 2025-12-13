package com.dayana.view;

import com.dayana.model.Empleado;      
import com.dayana.service.EmpleadoService;
import java.util.Scanner;

public class EmpleadoMenu {

    private EmpleadoService empleadoService;
    private Scanner scanner = new Scanner(System.in);

    public EmpleadoMenu(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n--- MENÚ EMPLEADOS ---");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Listar empleados");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error: ingrese solo números");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> registrarEmpleado();
                case 2 -> listarEmpleados();
            }

        } while (opcion != 0);
    }

    private void registrarEmpleado() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Documento: ");
        String documento = scanner.nextLine();

        System.out.print("Rol: ");
        String rol = scanner.nextLine();

        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        System.out.print("Salario: ");
        double salario = scanner.nextDouble();

        Empleado empleado = new Empleado(
                id, nombre, documento, rol, correo, salario
        );

        empleadoService.registrarEmpleado(empleado);

        System.out.println("✔ Empleado registrado correctamente");
    }

    private void listarEmpleados() {

        if (empleadoService.listarEmpleados().isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        System.out.println("\n---------------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-16s %-14s %-12s %-22s %-10s%n",
                "ID", "NOMBRE", "DOCUMENTO", "ROL", "CORREO", "SALARIO");
        System.out.println("-----------------------------------------------------------------------------------------------");

        empleadoService.listarEmpleados().forEach(e -> {
            System.out.printf("%-4d %-16s %-14s %-12s %-22s %-10.2f%n",
                    e.getId(),
                    e.getNombre(),
                    e.getDocumento(),
                    e.getRol(),
                    e.getCorreo(),
                    e.getSalario());
        });

        System.out.println("--------------------------------------------------------------------------");
    }
}
