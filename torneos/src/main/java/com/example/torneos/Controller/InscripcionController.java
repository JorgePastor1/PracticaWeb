package com.example.torneos.Controller;

import com.example.torneos.Service.EquipoService;
import com.example.torneos.Service.InscripcionService;
import com.example.torneos.Service.TorneoService;
import com.example.torneos.model.Equipo;
import com.example.torneos.model.Torneo;
import com.example.torneos.model.Inscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private TorneoService torneoService;

    // Muestra los torneos disponibles (ListaInscripciones.html)
    @GetMapping
    public String mostrarTorneosDisponibles(Model model) {
        List<Torneo> torneos = torneoService.obtenerTodos();
        if (torneos == null)
            torneos = new ArrayList<>();
        model.addAttribute("torneos", torneoService.obtenerTodos());
        return "ListaInscripciones";
    }

    // Procesa la inscripción
    @PostMapping
    public String procesarInscripcion(@RequestParam Long equipoId, @RequestParam Long torneoId,
            RedirectAttributes redirectAttributes) {
        Equipo equipo = equipoService.buscarPorId(equipoId);
        Torneo torneo = torneoService.buscarPorId(torneoId);

        if (equipo == null || torneo == null) {
            redirectAttributes.addFlashAttribute("error", "El equipo o el torneo no son válidos.");
            return "redirect:/inscripciones/nueva";
        }

        inscripcionService.inscribir(equipo, torneo);
        return "redirect:/inscripciones";
    }

    // Formulario para editar inscripción
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Inscripcion inscripcion = inscripcionService.buscarPorId(id);
        model.addAttribute("inscripcion", inscripcion);
        model.addAttribute("equipos", equipoService.obtenerTodos());
        model.addAttribute("torneos", torneoService.obtenerTodos());
        return "inscripciones/editar";
    }

    // Guarda los cambios en una inscripción
    @PostMapping("/{id}/editar")
    public String guardarCambios(@PathVariable Long id,
            @RequestParam Long equipoId,
            @RequestParam Long torneoId,
            @RequestParam String estado,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            RedirectAttributes redirectAttributes) {
        try {
            Equipo equipo = equipoService.buscarPorId(equipoId);
            Torneo torneo = torneoService.buscarPorId(torneoId);

            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setId(id);
            inscripcion.setEquipo(equipo);
            inscripcion.setTorneo(torneo);
            inscripcion.setEstado(estado);
            inscripcion.setFechaInscripcion(fecha);

            inscripcionService.actualizar(inscripcion);
            return "redirect:/inscripciones";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo actualizar la inscripción.");
            return "redirect:/inscripciones/" + id + "/editar";
        }
    }
}
