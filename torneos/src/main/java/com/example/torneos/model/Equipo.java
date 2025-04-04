package com.example.torneos.model;

public class Equipo {

    private Long id;
    private String nombre;
    private String categoria;
    private String ciudad;
    private int numJugadores;
    private String nombreCapitan;

    public Equipo() {
    }

    public Equipo(Long id, String nombre, String categoria, String ciudad, int numJugadores, String nombreCapitan) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.numJugadores = numJugadores;
        this.nombreCapitan = nombreCapitan;
    }

    // Getters y Setters

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

    public String getNombreCapitan() {
        return nombreCapitan;
    }

    public void setNombreCapitan(String nombreCapitan) {
        this.nombreCapitan = nombreCapitan;
    }

    // Método toString

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", numJugadores=" + numJugadores +
                ", nombreCapitan='" + nombreCapitan + '\'' +
                '}';
    }
}
