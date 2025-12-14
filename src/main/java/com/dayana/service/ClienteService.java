package com.dayana.service;

import com.dayana.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public ClienteService() {
        cargarClientes();
    }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
        guardarClientes();
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Cliente buscarPorDocumento(String documento) {
        return clientes.stream()
                .filter(c -> c.getDocumento().equals(documento))
                .findFirst()
                .orElse(null);
    }

    private void guardarClientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/clientes.txt"))) {
            for (Cliente c : clientes) {
                writer.write(c.getId() + "," + c.getNombre() + "," + c.getDocumento() + ","
                        + c.getCorreo() + "," + c.getTelefono());
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Error al guardar clientes: " + ex.getMessage());
        }
    }

    private void cargarClientes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/clientes.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Cliente c = new Cliente(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        datos[2],
                        datos[3],
                        datos[4]
                );
                clientes.add(c);
            }
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            System.out.println("Error al cargar clientes: " + ex.getMessage());
        }
    }

    public Cliente buscarPorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
