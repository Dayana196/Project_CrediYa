package com.dayana.model;

public class Empleado {

    private int id;
    private String nombre;
    private String documento;
    private String rol;
    private String correo;
    private double salario;

    public Empleado(int id, String nombre, String documento,
                    String rol, String correo, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.rol = rol;
        this.correo = correo;
        this.salario = salario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getRol() {
        return rol;
    }

    public String getCorreo() {
        return correo;
    }

    public double getSalario() {
        return salario;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", rol='" + rol + '\'' +
                ", correo='" + correo + '\'' +
                ", salario=" + salario +
                '}';
    }
}
