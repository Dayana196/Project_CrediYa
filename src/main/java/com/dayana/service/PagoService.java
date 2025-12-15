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

public class PagoService {

    // Registrar un pago a un préstamo
    public void registrarPago(Scanner input) {
        String query = "INSERT INTO pagos(prestamo_id, fecha_pago, monto) VALUES(?, ?, ?)";

        try (Connection conexion = ConexionMYSQL.conectar()) {
            if (conexion == null) {
                System.out.println("No hay conexión con la base de datos");
                return;
            }

            System.out.println("Ingrese el ID del préstamo a pagar:");
            int prestamoId = input.nextInt();

            System.out.println("Ingrese el monto del pago:");
            double monto = input.nextDouble();
            input.nextLine();

            LocalDate fechaPago = LocalDate.now();

            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, prestamoId);
                stmt.setDate(2, java.sql.Date.valueOf(fechaPago));
                stmt.setDouble(3, monto);

                int filas = stmt.executeUpdate();
                System.out.println("Pago registrado correctamente. Filas afectadas: " + filas);
            }

        } catch (Exception e) {
            System.out.println("Error al registrar el pago");
            e.printStackTrace();
        }
    }

    // Listar todos los pagos
    public void listarPagos() {
        String query = "SELECT p.id, p.prestamo_id, p.fecha_pago, p.monto, c.nombre AS cliente " +
                       "FROM pagos p JOIN prestamos pr ON p.prestamo_id = pr.id " +
                       "JOIN clientes c ON pr.cliente_id = c.id";
        String archivo = "data/pagos.txt";

        try (Connection conexion = ConexionMYSQL.conectar();
             PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();
             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo)))) {

            boolean hayPagos = false;

            System.out.println("-------- Lista de Pagos --------");
            pw.println("-------- Lista de Pagos --------");

            while (rs.next()) {
                hayPagos = true;
                int id = rs.getInt("id");
                int prestamoId = rs.getInt("prestamo_id");
                String cliente = rs.getString("cliente");
                double monto = rs.getDouble("monto");
                String fecha = rs.getString("fecha_pago");

                System.out.println("ID Pago: " + id);
                System.out.println("Prestamo ID: " + prestamoId);
                System.out.println("Cliente: " + cliente);
                System.out.println("Monto: " + monto);
                System.out.println("Fecha: " + fecha);
                System.out.println("---------------------------------");

                pw.println("ID Pago: " + id);
                pw.println("Prestamo ID: " + prestamoId);
                pw.println("Cliente: " + cliente);
                pw.println("Monto: " + monto);
                pw.println("Fecha: " + fecha);
                pw.println("---------------------------------");
            }

            if (!hayPagos) {
                System.out.println("No hay pagos registrados");
                pw.println("No hay pagos registrados");
            }

        } catch (Exception e) {
            System.out.println("Error al listar los pagos");
            e.printStackTrace();
        }
    }

    // Consultar pagos de un cliente por documento
    public void consultarPagosCliente(Scanner input) {
        String query = "SELECT p.id, p.prestamo_id, p.fecha_pago, p.monto, c.nombre AS cliente " +
                       "FROM pagos p " +
                       "JOIN prestamos pr ON p.prestamo_id = pr.id " +
                       "JOIN clientes c ON pr.cliente_id = c.id " +
                       "WHERE c.documento = ?";

        try (Connection conexion = ConexionMYSQL.conectar();
             PreparedStatement stmt = conexion.prepareStatement(query)) {

            System.out.println("Ingrese el documento del cliente:");
            String documento = input.nextLine();
            stmt.setString(1, documento);

            ResultSet rs = stmt.executeQuery();
            boolean hayPagos = false;

            while (rs.next()) {
                hayPagos = true;
                System.out.println("ID Pago: " + rs.getInt("id"));
                System.out.println("Prestamo ID: " + rs.getInt("prestamo_id"));
                System.out.println("Cliente: " + rs.getString("cliente"));
                System.out.println("Monto: " + rs.getDouble("monto"));
                System.out.println("Fecha: " + rs.getString("fecha_pago"));
                System.out.println("---------------------------------");
            }

            if (!hayPagos) {
                System.out.println("No hay pagos registrados para este cliente");
            }

        } catch (Exception e) {
            System.out.println("Error al consultar los pagos del cliente");
            e.printStackTrace();
        }
    }
}

