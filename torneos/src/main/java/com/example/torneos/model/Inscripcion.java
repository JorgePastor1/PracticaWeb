package com.example.torneos.model;

import java.time.LocalDate;

// Model class representing a registration (Inscripcion) of a team to a tournament
public class Inscripcion {

    // Fields
    private Long id;                          // Unique ID for the registration
    private Equipo equipo;                    // Team being registered
    private Torneo torneo;                    // Tournament to which the team is registering
    private LocalDate fechaInscripcion;       // Date of registration
    private String estado;                    // Status: e.g., pending, confirmed, rejected, etc.

    // Default constructor (required by frameworks like Spring)
    public Inscripcion() {
    }

    // Parameterized constructor for easier instantiation
    public Inscripcion(Long id, Equipo equipo, Torneo torneo, LocalDate fechaInscripcion, String estado) {
        this.id = id;
        this.equipo = equipo;
        this.torneo = torneo;
        this.fechaInscripcion = fechaInscripcion;
        this.estado = estado;
    }

    // Getters and setters

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

    // String representation of the registration for logging or debugging
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
