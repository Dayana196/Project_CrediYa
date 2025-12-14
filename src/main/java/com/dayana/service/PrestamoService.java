package com.dayana.service;

import com.dayana.model.Prestamo;
import com.dayana.model.Cliente;
import com.dayana.model.Empleado;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class PrestamoService {

    private List<Prestamo> prestamos = new ArrayList<>();
    private ClienteService clienteService;
    private EmpleadoService empleadoService;
    private final String archivo = "data/prestamos.txt";

    
    public PrestamoService(ClienteService clienteService, EmpleadoService empleadoService) {
        this.clienteService = clienteService;
        this.empleadoService = empleadoService;
        cargarPrestamos(); 
    }

    public void registrarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        guardarPrestamos();
    }

    public List<Prestamo> listarPrestamos() {
        return prestamos;
    }

    
    private void guardarPrestamos() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
        for (Prestamo p : prestamos) {
            writer.write(p.getId() + "," + p.getCliente().getId() + "," + p.getEmpleado().getId() + ","
                    + p.getMonto() + "," + p.getInteres() + "," + p.getPlazo() + "," + p.getSaldoPendiente() + ","
                    + p.getEstado());
            writer.newLine();
        }
    } catch (IOException ex) {
        System.out.println("Error al guardar préstamos: " + ex.getMessage());
    }
}


   private void cargarPrestamos() {
    File f = new File(archivo);
    if (!f.exists()) return;

    try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length < 8) continue;

            int id = Integer.parseInt(datos[0]);
            int clienteId = Integer.parseInt(datos[1]);
            int empleadoId = Integer.parseInt(datos[2]);
            double monto = Double.parseDouble(datos[3]);
            double interes = Double.parseDouble(datos[4]);
            int plazo = Integer.parseInt(datos[5]);
            double saldoPendiente = Double.parseDouble(datos[6]);
            String estado = datos[7];

            Cliente cliente = clienteService.buscarPorId(clienteId);
            Empleado empleado = empleadoService.buscarPorId(empleadoId);
            if (cliente == null || empleado == null) continue;

            Prestamo prestamo = new Prestamo(id, cliente, empleado, monto, interes, plazo);
            prestamo.setSaldoPendiente(saldoPendiente);
            if ("Pagado".equalsIgnoreCase(estado)) prestamo.setSaldoPendiente(0);
            prestamos.add(prestamo);
        }
    } catch (IOException ex) {
        System.out.println("Error al cargar préstamos: " + ex.getMessage());
    }
}


    public Prestamo buscarPorId(int id) {
        return prestamos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
