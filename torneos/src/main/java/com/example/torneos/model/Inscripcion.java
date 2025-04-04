package com.example.torneos.model;

import java.time.LocalDate;

public class Inscripcion {

    private Long id;
    private Equipo equipo;
    private Torneo torneo;
    private LocalDate fechaInscripcion;
    private String estado; // pendiente, confirmada, rechazada, etc.

    public Inscripcion() {
    }

    public Inscripcion(Long id, Equipo equipo, Torneo torneo, LocalDate fechaInscripcion, String estado) {
        this.id = id;
        this.equipo = equipo;
        this.torneo = torneo;
        this.fechaInscripcion = fechaInscripcion;
        this.estado = estado;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", equipo=" + equipo.getNombre() +
                ", torneo=" + torneo.getDeporte() + " - " + torneo.getCiudad() +
                ", fechaInscripcion=" + fechaInscripcion +
                ", estado='" + estado + '\'' +
                '}';
    }
}
