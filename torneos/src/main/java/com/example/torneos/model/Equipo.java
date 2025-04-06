package com.example.torneos.model;

// Model class representing a team (Equipo)
public class Equipo {

    // Fields representing team properties
    private Long id;
    private String nombre;         // Team name
    private String categoria;      // Category (e.g., U18, amateur, etc.)
    private String ciudad;         // City the team is from
    private int numJugadores;      // Number of players

    // Default constructor (needed for frameworks like Spring)
    public Equipo() {
    }

    // Parameterized constructor for easier object creation
    public Equipo(Long id, String nombre, String categoria, String ciudad, int numJugadores) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.numJugadores = numJugadores;
    }

    // Getters and setters for each field

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    // String representation of the object, useful for debugging/logging
    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", numJugadores=" + numJugadores + '\'' +
                '}';
    }
}
