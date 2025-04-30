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

    // Mostrar lista de torneos disponibles para inscripciones
    @GetMapping("")
    public String mostrarInscripciones(Model model) {
        List<Torneo> torneos = torneoService.obtenerTodos();
        model.addAttribute("torneos", torneos);
        return "ListaInscripciones";
    }

    // ✅ Formulario para inscribir un equipo en un torneo específico
    @GetMapping("/nueva")
    public String mostrarFormularioSimplificado(@RequestParam("torneoId") Long torneoId, Model model) {
        Torneo torneo = torneoService.buscarPorId(torneoId);
        if (torneo == null) {
            return "redirect:/inscripciones";
        }

        List<Equipo> equiposDisponibles = equipoService.obtenerEquiposDisponibles(torneoId);

        model.addAttribute("torneo", torneo);           // Necesario para mostrar el nombre
        model.addAttribute("equipos", equiposDisponibles); // Solo equipos no inscritos
        return "FormularioInscripciones";               // El nombre de tu plantilla Thymeleaf
    }

    // ✅ Procesar la inscripción con solo torneoId y equipoId
    @PostMapping
    public String guardarInscripcion(@RequestParam Long torneoId, @RequestParam Long equipoId) {
        inscripcionService.inscribirEquipoEnTorneo(equipoId, torneoId); // Método creado en InscripcionService
        return "redirect:/inscripciones";
    }

    // Formulario para editar inscripción existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Inscripcion inscripcion = inscripcionService.buscarPorId(id);
        if (inscripcion != null) {
            model.addAttribute("inscripcion", inscripcion);
            model.addAttribute("equipos", equipoService.obtenerTodos());
            model.addAttribute("torneos", torneoService.obtenerTodos());
            return "FormularioEditarInscripcion";
        }
        return "redirect:/inscripciones";
    }

    // Actualizar inscripción
    @PostMapping("/actualizar")
    public String actualizarInscripcion(@ModelAttribute Inscripcion inscripcion) {
        inscripcionService.actualizar(inscripcion);
        return "redirect:/inscripciones";
    }

    // Eliminar inscripción
    @GetMapping("/eliminar/{id}")
    public String eliminarInscripcion(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return "redirect:/inscripciones";
    }

    // Mostrar inscripciones de un torneo específico
    @GetMapping("/torneo/{id}")
    public String listarPorTorneo(@PathVariable Long id, Model model) {
        Torneo torneo = torneoService.buscarPorId(id);
        if (torneo != null) {
            model.addAttribute("torneo", torneo);
            model.addAttribute("inscripciones", torneo.getInscripciones());
            return "MostrarInscripciones";
        }
        return "redirect:/torneos";
    }
}
