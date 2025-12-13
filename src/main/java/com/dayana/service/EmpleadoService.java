package com.dayana.service;

import com.dayana.model.Empleado;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {

    private List<Empleado> empleados = new ArrayList<>();

    public void registrarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public List<Empleado> listarEmpleados() {
        return empleados;
    }

    public Empleado buscarPorDocumento(String documento) {
        for (Empleado e : empleados) {
            if (e.getDocumento().equals(documento)) {
                return e;
            }
        }
        return null;
    }
}
