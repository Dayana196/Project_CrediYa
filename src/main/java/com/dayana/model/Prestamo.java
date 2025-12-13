package com.dayana.model;

public class Prestamo {

    private int id;
    private Cliente cliente;
    private Empleado empleado;
    private double monto;
    private double interes;       // ejemplo: 0.10 = 10%
    private int plazoMeses;
    private double saldoPendiente;
    private String estado;         // PENDIENTE / PAGADO

    public Prestamo(int id, Cliente cliente, Empleado empleado,
                    double monto, double interes, int plazoMeses) {

        this.id = id;
        this.cliente = cliente;
        this.empleado = empleado;
        this.monto = monto;
        this.interes = interes;
        this.plazoMeses = plazoMeses;
        this.saldoPendiente = calcularMontoTotal();
        this.estado = "PENDIENTE";
    }

    public double calcularMontoTotal() {
        return monto + (monto * interes);
    }

    public double calcularCuotaMensual() {
        return calcularMontoTotal() / plazoMeses;
    }

    public void aplicarPago(double valor) {
        saldoPendiente -= valor;
        if (saldoPendiente <= 0) {
            saldoPendiente = 0;
            estado = "PAGADO";
        }
    }

    // Getters
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Empleado getEmpleado() { return empleado; }
    public double getMonto() { return monto; }
    public double getInteres() { return interes; }
    public int getPlazoMeses() { return plazoMeses; }
    public double getSaldoPendiente() { return saldoPendiente; }
    public String getEstado() { return estado; }
}
