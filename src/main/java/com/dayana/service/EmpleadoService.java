package com.dayana.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.dayana.Conector.ConexionMYSQL;

public class EmpleadoService {

    public void registrarEmpleado(Scanner input){
        String query = "INSERT INTO empleado(nombre, documento, rol, correo, salario) VALUES(?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionMYSQL.conectar()) {
            if (conexion == null) {
                System.out.println("No hay conexión con la base de datos");
                return;
            }

            System.out.println("Ingrese los datos del empleado a registrar");

            System.out.print("Nombre: ");
            String nombre = input.nextLine();

            System.out.print("Documento: ");
            String documento = input.nextLine();

            System.out.print("Rol: ");
            String rol = input.nextLine();

            System.out.print("Correo: ");
            String correo = input.nextLine();

            System.out.print("Salario: ");
            double salario = input.nextDouble();
            input.nextLine(); // limpiar buffer

            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, nombre);
                stmt.setString(2, documento);
                stmt.setString(3, rol);
                stmt.setString(4, correo);
                stmt.setDouble(5, salario);

                int filas = stmt.executeUpdate();
                System.out.println("Empleado fue registrado correctamente");
                System.out.println("Filas afectadas: " + filas);
            }

        } catch (Exception e) {
            System.out.println("Error al registrar el empleado");
            e.printStackTrace();
        }
    }

    public void listaEmpleado() {
        String query = "SELECT * FROM empleado";
        String archivo = "data/empleado.txt";

        try (Connection conexion = ConexionMYSQL.conectar();
             PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();
             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo)))) {

            boolean hayEmpleado = false;

            System.out.println("-------- Lista de Empleados --------");
            pw.println("-------- Lista de Empleados --------");

            while (rs.next()) {
                hayEmpleado = true;

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String documento = rs.getString("documento");
                String rol = rs.getString("rol");
                String correo = rs.getString("correo");
                double salario = rs.getDouble("salario");

                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Documento: " + documento);
                System.out.println("Rol: " + rol);
                System.out.println("Correo: " + correo);
                System.out.println("Salario: " + salario);
                System.out.println("---------------------------------");

                pw.println("ID: " + id);
                pw.println("Nombre: " + nombre);
                pw.println("Documento: " + documento);
                pw.println("Rol: " + rol);
                pw.println("Correo: " + correo);
                pw.println("Salario: " + salario);
                pw.println("---------------------------------");
            }

            if (!hayEmpleado) {
                System.out.println("No hay empleados registrados");
                pw.println("No hay empleados registrados");
            }

        } catch (Exception e) {
            System.out.println("Error al listar empleados");
            e.printStackTrace();
        }
    }
}
