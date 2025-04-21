package com.example.torneos.Controller;

import com.example.torneos.Service.EquipoService;
import com.example.torneos.Service.InscripcionService;
import com.example.torneos.Service.TorneoService;
import com.example.torneos.model.Equipo;
import com.example.torneos.model.Inscripcion;
import com.example.torneos.model.Torneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // ✅ Este método soluciona el error: carga la vista con los torneos
    @GetMapping
    public String mostrarTorneosDisponibles(Model model) {
        List<Torneo> torneos = torneoService.obtenerTodos();
        if (torneos == null)
            torneos = new ArrayList<>();
        model.addAttribute("torneos", torneos);
        return "ListaInscripciones";
    }

    // Formulario para inscribir un equipo a un torneo
    @GetMapping("/nueva")
    public String mostrarFormularioInscripcion(@RequestParam("torneoId") Long torneoId, Model model) {
        Torneo torneo = torneoService.buscarPorId(torneoId);
        if (torneo == null) {
            return "redirect:/inscripciones";
        }

        model.addAttribute("torneo", torneo);
        model.addAttribute("equipos", equipoService.obtenerTodos());
        return "FormularioInscripciones";
    }

    // Procesar la inscripción
    @PostMapping
    public String procesarInscripcion(@RequestParam Long equipoId, @RequestParam Long torneoId,
            RedirectAttributes redirectAttributes) {
        Equipo equipo = equipoService.buscarPorId(equipoId);
        Torneo torneo = torneoService.buscarPorId(torneoId);

        if (equipo == null || torneo == null) {
            redirectAttributes.addFlashAttribute("error", "El equipo o el torneo no son válidos.");
            return "redirect:/inscripciones/nueva?torneoId=" + torneoId;
        }

        inscripcionService.inscribir(equipo, torneo);
        return "redirect:/inscripciones";
    }

    // Mostrar equipos inscritos en un torneo
    @GetMapping("/torneo/{id}")
    public String mostrarInscripcionesPorTorneo(@PathVariable Long id, Model model) {
        Torneo torneo = torneoService.buscarPorId(id);
        if (torneo == null) {
            return "redirect:/inscripciones";
        }

        List<Inscripcion> inscripciones = inscripcionService.obtenerTodas()
                .stream()
                .filter(i -> i.getTorneo().getId().equals(id))
                .toList();

        model.addAttribute("torneo", torneo);
        model.addAttribute("inscripciones", inscripciones);
        return "MostrarInscripciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarInscripcion(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            inscripcionService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Inscripción eliminada correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar la inscripción.");
        }
        return "redirect:/inscripciones";
    }
}
