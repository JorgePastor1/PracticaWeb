package com.example.torneos.controller;

import com.example.torneos.model.Equipo;
import com.example.torneos.model.Inscripcion;
import com.example.torneos.model.Torneo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/inscripciones")
public class InscripcionController {

    private List<Inscripcion> inscripciones = new ArrayList<>();
    private List<Equipo> equiposDisponibles = new ArrayList<>();
    private List<Torneo> torneosDisponibles = new ArrayList<>();
    private Long siguienteId = 1L;

    public InscripcionController() {
        // Datos simulados para elegir
        equiposDisponibles.add(new Equipo(1L, "Los Halcones", "Senior", "Sevilla", 10, "Laura García"));
        torneosDisponibles.add(new Torneo(1L, LocalDate.of(2025, 6, 20), "Baloncesto", "Sevilla", new ArrayList<>(), 80.0, "Sin clasificar"));
    }

    @GetMapping
    public String listarInscripciones(Model model) {
        model.addAttribute("inscripciones", inscripciones);
        return "inscripciones/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("inscripcion", new Inscripcion());
        model.addAttribute("equipos", equiposDisponibles);
        model.addAttribute("torneos", torneosDisponibles);
        return "inscripciones/formulario";
    }

    @PostMapping
    public String guardarInscripcion(@ModelAttribute Inscripcion inscripcion, @RequestParam Long equipoId, @RequestParam Long torneoId) {
        inscripcion.setId(siguienteId++);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setEstado("pendiente");

        // Asociar equipo y torneo
        Equipo equipoSeleccionado = equiposDisponibles.stream()
                .filter(e -> e.getId().equals(equipoId))
                .findFirst()
                .orElse(null);

        Torneo torneoSeleccionado = torneosDisponibles.stream()
                .filter(t -> t.getId().equals(torneoId))
                .findFirst()
                .orElse(null);

        inscripcion.setEquipo(equipoSeleccionado);
        inscripcion.setTorneo(torneoSeleccionado);

        inscripciones.add(inscripcion);

        return "redirect:/inscripciones";
    }
}
