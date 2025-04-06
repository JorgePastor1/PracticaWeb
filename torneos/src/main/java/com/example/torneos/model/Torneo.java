package com.example.torneos.model;

import java.time.LocalDate;
import java.util.List;

// Model class representing a tournament
public class Torneo {

    // Fields
    private Long id;                          // Unique identifier for the tournament
    private LocalDate fecha;                 // Date of the tournament
    private String deporte;                  // Type of sport (e.g., football, basketball)
    private String ciudad;                   // City where the tournament takes place
    private List<Equipo> equipos;            // List of registered teams
    private double costeInscripcion;         // Registration cost per team
    private String clasificacion;            // Optional: current ranking or classification

    // Default constructor (required for frameworks and serialization)
    public Torneo() {
    }

    // Parameterized constructor for creating fully initialized instances
    public Torneo(Long id, LocalDate fecha, String deporte, String ciudad, List<Equipo> equipos, double costeInscripcion, String clasificacion) {
        this.id = id;
        this.fecha = fecha;
        this.deporte = deporte;
        this.ciudad = ciudad;
        this.equipos = equipos;
        this.costeInscripcion = costeInscripcion;
        this.clasificacion = clasificacion;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public double getCosteInscripcion() {
        return costeInscripcion;
    }

    public void setCosteInscripcion(double costeInscripcion) {
        this.costeInscripcion = costeInscripcion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    // Custom string representation of the tournament
    @Override
    public String toString() {
        return "Torneo{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", deporte='" + deporte + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", equipos=" + equipos +
                ", costeInscripcion=" + costeInscripcion +
                ", clasificacion='" + clasificacion + '\'' +
                '}';
    }
}
