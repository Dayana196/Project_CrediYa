package com.dayana.service;

import com.dayana.Conector.ConexionMYSQL; // <--- IMPORT CORRECTO
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.io.*;

public class ClienteService {

    public void registrarCliente(Scanner input){
        String query = "INSERT INTO clientes(nombre, documento, correo, telefono) VALUES(?, ?, ?, ?)";

        try (Connection conexion = ConexionMYSQL.conectar()) {  // <-- NOMBRE CORRECTO
            if (conexion == null) {
                System.out.println("No hay conexión con la base de datos");
                return;
            }

            System.out.println("Ingrese los datos del cliente a registrar");

            System.out.print("Nombre: ");
            String nombre = input.nextLine();

            System.out.print("Documento: ");
            String documento = input.nextLine();

            System.out.print("Correo: ");
            String correo = input.nextLine();

            System.out.print("Telefono: ");
            String telefono = input.nextLine();

            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, nombre);
                stmt.setString(2, documento);
                stmt.setString(3, correo);
                stmt.setString(4, telefono);

                int filas = stmt.executeUpdate();
                System.out.println("Cliente fue registrado correctamente");
                System.out.println("Filas afectadas: " + filas);
            }

        } catch (Exception e) {
            System.out.println("Error al registrar el cliente");
            e.printStackTrace();
        }
    }

    public void listaClientes() {
        String query = "SELECT * FROM clientes";
        String archivo = "data/clientes.txt";
    
        try (Connection conexion = ConexionMYSQL.conectar();
             PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();
             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo)))) {
    
            boolean hayCliente = false;
    
            System.out.println("-------- Lista de Clientes --------");
            pw.println("-------- Lista de Clientes --------");
    
            while (rs.next()) {
                hayCliente = true;
    
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String documento = rs.getString("documento");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
    
                // Mostrar por consola
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Documento: " + documento);
                System.out.println("Correo: " + correo);
                System.out.println("Telefono: " + telefono);
                System.out.println("---------------------------------");
    
                // Guardar en archivo
                pw.println("ID: " + id);
                pw.println("Nombre: " + nombre);
                pw.println("Documento: " + documento);
                pw.println("Correo: " + correo);
                pw.println("Telefono: " + telefono);
                pw.println("---------------------------------");
            }
    
            if (!hayCliente) {
                System.out.println("No hay clientes registrados");
                pw.println("No hay clientes registrados");
            }
    
        } catch (Exception e) {
            System.out.println("Error al listar clientes");
            e.printStackTrace();
        }
    }
    // listaClientes() permanece igual, solo reemplazar conexionMysql -> ConexionMYSQL
}
