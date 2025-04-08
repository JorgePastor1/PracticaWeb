package com.example.torneos.model;

import java.time.LocalDate;

public class Torneo {

    private Long id;
    private String nombre;
    private String lugar;
    private LocalDate fecha;
    private String categoria; // ✅ Nuevo campo

    public Torneo() {
    }

    public Torneo(Long id, String nombre, String lugar, LocalDate fecha, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Torneo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", lugar='" + lugar + '\'' +
                ", fecha=" + fecha +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
