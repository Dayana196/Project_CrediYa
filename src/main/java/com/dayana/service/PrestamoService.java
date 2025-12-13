package com.dayana.service;

import com.dayana.model.Prestamo;
import java.util.ArrayList;
import java.util.List;

public class PrestamoService {

    private List<Prestamo> prestamos = new ArrayList<>();

    public void registrarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public List<Prestamo> listarPrestamos() {
        return prestamos;
    }
}
