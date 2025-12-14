package com.dayana.service;

import com.dayana.model.Prestamo;

import java.util.List;
import java.util.stream.Collectors;

public class ReporteService {

    private List<Prestamo> prestamos;

    public ReporteService(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public List<Prestamo> prestamosActivos() {
        return prestamos.stream()
                .filter(p -> p.getEstado().equalsIgnoreCase("PENDIENTE"))
                .collect(Collectors.toList());
    }

    public List<Prestamo> prestamosPagados() {
        return prestamos.stream()
                .filter(p -> p.getEstado().equalsIgnoreCase("PAGADO"))
                .collect(Collectors.toList());
    }

    public double totalPrestado() {
        return prestamos.stream()
                .mapToDouble(Prestamo::getMonto)
                .sum();
    }
}
