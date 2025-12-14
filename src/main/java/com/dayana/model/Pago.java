package com.dayana.model;

public class Pago {
    private static int contador = 1;
    private int id;
    private Prestamo prestamo;
    private double monto;

    public Pago(Prestamo prestamo, double monto) {
        this.id = contador++;
        this.prestamo = prestamo;
        this.monto = monto;
    }

   
    public int getId() { return id; }
    public Prestamo getPrestamo() { return prestamo; }
    public double getMonto() { return monto; }
}
