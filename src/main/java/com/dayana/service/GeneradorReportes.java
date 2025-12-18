package com.dayana.service;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;


import com.dayana.Conector.ConexionMYSQL;
public class GeneradorReportes {

    public void generarReportePrestamos() {
        String query = "SELECT id, cliente_nombre, monto, tasa_interes, cuotas, estado, fecha_solicitud FROM reportes";

        try (Connection conexion = ConexionMYSQL.conectar()) {
            if (conexion == null) {
                System.out.println("No hay conexión con la base de datos");
                return;
            }

            try (PreparedStatement stmt = conexion.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery();
                 PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("reporte_prestamos_" + LocalDate.now() + ".txt")))) {

                writer.println("Reporte de Prestamos");
                writer.println("====================");
                writer.printf("%-5s %-20s %-10s %-15s %-10s %-15s %-15s%n", "ID", "Cliente", "Monto", "Tasa Interes", "Cuotas", "Estado", "Fecha Solicitud");
                writer.println("---------------------------------------------------------------------------------------");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String clienteNombre = rs.getString("cliente_id");
                    double monto = rs.getDouble("monto");
                    double tasaInteres = rs.getDouble("tasa_interes");
                    int cuotas = rs.getInt("cuotas");
                    String estado = rs.getString("estado");
                    LocalDate fechaSolicitud = rs.getDate("fecha_solicitud").toLocalDate();

                    writer.printf("%-5d %-20s %-10.2f %-15.2f %-10d %-15s %-15s%n", id, clienteNombre, monto, tasaInteres, cuotas, estado, fechaSolicitud);
                }

                System.out.println("Reporte generado exitosamente.");

            } catch (Exception e) {
                System.out.println("Error al generar el reporte");
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Error de conexión a la base de datos");
            e.printStackTrace();
        }
    }


public void SimulacionPrestamos(Scanner input){
    System.out.println("Ingrese los datos que quieres simular del prestamo");

    System.out.println("Monto del prestamo");
    double monto = input.nextDouble();

    System.out.println("Tasa de interes");
    double interes = input.nextDouble();

    System.out.println("Cantidad de cuotas del prestamo");
    int cuotas = input.nextInt();

    if (monto <= 0 || interes <= 0 || cuotas <= 0) {
        System.out.println("Datos invalido, revise monto, interes y cuotas");
        return;
    }

    input.nextLine();

    double montoTotal = monto + (monto * interes / 100);
    double cuotaMensual = montoTotal / cuotas;

    System.out.println();

    System.out.println("-------- Total a pagar --------");
    System.out.printf("  Monto total con interes: %,.2f%n",  montoTotal);
    System.out.printf("  Valor de cada cuota mensual: %,.2f%n", cuotaMensual);
    System.out.println("-------------------------------");

    System.out.println();

    System.out.println("Precione Enter para continuar");
    input.nextLine();
}

public void cambiarEstado(Scanner input){
    try (Connection conexion = ConexionMYSQL.conectar()) {
        System.out.println("Ingrese el ID del prestamos a actualizar: ");
        int prestamoId = input.nextInt();
        input.nextLine();

        System.out.println("Ingrese el nuevo estado: ");
        String nuevoEstado = input.nextLine();

        String query = "UPDATE prestamos SET estado = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, prestamoId);

            int filas = stmt.executeUpdate();
            if (filas > 0 ) {
                System.out.println("El estado ha actualizado corrextamente");
            } else {
                System.out.println("No se encontro el prestamos con ese id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
}



