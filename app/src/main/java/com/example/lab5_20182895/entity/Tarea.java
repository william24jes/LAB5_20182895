package com.example.lab5_20182895.entity;

import java.io.Serializable;
import java.util.Date;

public class Tarea implements Serializable {
    private String titulo;
    private String descripcion;
    private Date fecha;

    public Tarea(String title, String descripcion, Date fecha) {
        this.titulo = title;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // Getters y setters


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Descripción: " + descripcion + ", Fecha de vencimiento: " + fecha.toString();
    }
}
