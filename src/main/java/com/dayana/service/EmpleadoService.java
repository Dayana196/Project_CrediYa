package com.dayana.service;

import com.dayana.model.Empleado;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class EmpleadoService {

    private List<Empleado> empleados = new ArrayList<>();

    public EmpleadoService() {
        cargarEmpleados();
    }

    public void registrarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        guardarEmpleados();
    }

    public List<Empleado> listarEmpleados() {
        return empleados;
    }

    public Empleado buscarPorDocumento(String documento) {
        return empleados.stream()
                .filter(e -> e.getDocumento().equals(documento))
                .findFirst()
                .orElse(null);
    }

    private void guardarEmpleados() {
    try {
        
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("data/empleados.txt"));
        for (Empleado e : empleados) {
            writer.write(e.getId() + "," + e.getNombre() + "," + e.getDocumento() + ","
                    + e.getRol() + "," + e.getCorreo() + "," + e.getSalario());
            writer.newLine();
        }
        writer.close();
    } catch (IOException ex) {
        System.out.println("Error al guardar empleados: " + ex.getMessage());
    }
}


    private void cargarEmpleados() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/empleados.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Empleado e = new Empleado(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        datos[2],
                        datos[3],
                        datos[4],
                        Double.parseDouble(datos[5])
                );
                empleados.add(e);
            }
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            System.out.println("Error al cargar empleados: " + ex.getMessage());
        }
    }

    public Empleado buscarPorId(int id) {
        return empleados.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
