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

@Controller
@RequestMapping("/inscripciones") // Base path for all the endpoints in this controller
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private TorneoService torneoService;

    // Display all registrations
    @GetMapping
    public String listarInscripciones(Model model) {
        model.addAttribute("inscripciones", inscripcionService.obtenerTodas());
        return "inscripciones/lista"; // View to list all inscriptions
    }

    // Show form to create a new registration
    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("equipos", equipoService.obtenerTodos()); // Add list of teams to model
        model.addAttribute("torneos", torneoService.obtenerTodos()); // Add list of tournaments to model
        return "inscripciones/formulario"; // View to render the registration form
    }

    // Process registration form
    @PostMapping
    public String procesarInscripcion(@RequestParam Long equipoId, @RequestParam Long torneoId, RedirectAttributes redirectAttributes) {
        Equipo equipo = equipoService.buscarPorId(equipoId);
        Torneo torneo = torneoService.buscarPorId(torneoId);

        // If either team or tournament does not exist, show error and redirect
        if (equipo == null || torneo == null) {
            redirectAttributes.addFlashAttribute("error", "El equipo o el torneo no son válidos.");
            return "redirect:/inscripciones/nueva";
        }

        // Register the team in the tournament
        inscripcionService.inscribir(equipo, torneo);
        return "redirect:/inscripciones"; // Redirect to the list of registrations
    }

    // Show form to edit an existing registration
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Inscripcion inscripcion = inscripcionService.buscarPorId(id);
        model.addAttribute("inscripcion", inscripcion);
        model.addAttribute("equipos", equipoService.obtenerTodos());
        model.addAttribute("torneos", torneoService.obtenerTodos());
        return "inscripciones/editar"; // View to edit registration
    }

    // Process update of a registration
    @PostMapping("/{id}/editar")
    public String guardarCambios(@PathVariable Long id,
                                  @RequestParam Long equipoId,
                                  @RequestParam Long torneoId,
                                  @RequestParam String estado,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
                                  RedirectAttributes redirectAttributes) {
        try {
            // Try updating the registration
            inscripcionService.actualizar(id, equipoId, torneoId, estado, fecha);
            return "redirect:/inscripciones"; // Success: redirect to list
        } catch (Exception e) {
            // If update fails, show error and return to edit form
            redirectAttributes.addFlashAttribute("error", "No se pudo actualizar la inscripción.");
            return "redirect:/inscripciones/" + id + "/editar";
        }
    }
}
