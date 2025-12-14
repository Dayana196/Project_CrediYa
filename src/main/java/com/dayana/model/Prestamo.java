package com.dayana.model;

public class Prestamo {

    private int id;
    private Cliente cliente;
    private Empleado empleado;
    private double monto;
    private double interes;
    private int plazo;
    private double saldoPendiente;
    private String estado;

    public Prestamo(int id, Cliente cliente, Empleado empleado, double monto, double interes, int plazo) {
        this.id = id;
        this.cliente = cliente;
        this.empleado = empleado;
        this.monto = monto;
        this.interes = interes;
        this.plazo = plazo;
        this.saldoPendiente = monto;
        this.estado = "Pendiente";
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Empleado getEmpleado() { return empleado; }
    public double getMonto() { return monto; }
    public double getInteres() { return interes; }
    public int getPlazo() { return plazo; }

    public double getSaldoPendiente() { return saldoPendiente; }
    public void setSaldoPendiente(double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
        if (this.saldoPendiente <= 0) {
            this.estado = "Pagado";
        }
    }

    public String getEstado() { return estado; }

    public double calcularMontoTotal() { return monto + (monto * interes); }
    public double calcularCuotaMensual() { return calcularMontoTotal() / plazo; }
}
