package com.dayana.service;

import com.dayana.model.Pago;
import com.dayana.model.Prestamo;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class PagoService {

    private List<Pago> pagos = new ArrayList<>();
    private PrestamoService prestamoService;
    private final String archivo = "data/pagos.txt";

    public PagoService(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
        cargarPagos();
    }

    public void registrarPago(Pago pago) {
        pagos.add(pago);

        
        Prestamo prestamo = pago.getPrestamo();
        prestamo.setSaldoPendiente(prestamo.getSaldoPendiente() - pago.getMonto());

        guardarPagos();
    }

    public List<Pago> listarPagos() {
        return pagos;
    }

    private void guardarPagos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Pago p : pagos) {
                writer.write(p.getPrestamo().getId() + "," + p.getMonto());
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Error al guardar pagos: " + ex.getMessage());
        }
    }

    private void cargarPagos() {
        File f = new File(archivo);
        if (!f.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                int prestamoId = Integer.parseInt(datos[0]);
                double monto = Double.parseDouble(datos[1]);

                Prestamo prestamo = prestamoService.buscarPorId(prestamoId);
                if (prestamo != null) {
                    prestamo.setSaldoPendiente(prestamo.getSaldoPendiente() - monto);
                    Pago pago = new Pago(prestamo, monto);
                    pagos.add(pago);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al cargar pagos: " + ex.getMessage());
        }
    }
}
