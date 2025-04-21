package com.example.torneos.Controller;

import com.example.torneos.Service.EquipoService;
import com.example.torneos.Service.InscripcionService;
import com.example.torneos.Service.TorneoService;
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

    @GetMapping("")
    public String mostrarInscripciones(Model model) {
        List<Torneo> torneos = torneoService.obtenerTodos(); // Asegúrate de que esto no esté vacío
        model.addAttribute("torneos", torneos);
        return "ListaInscripciones"; // o el nombre de tu HTML para inscripciones
    }

    // Formulario para inscribir un equipo a un torneo
    @GetMapping("/nueva")
    public String mostrarFormulario(Model model, @RequestParam(name = "torneoId", required = false) Long torneoId) {
        model.addAttribute("inscripcion", new Inscripcion());
        model.addAttribute("equipos", equipoService.obtenerTodos());
        model.addAttribute("torneos", torneoService.obtenerTodos());
        model.addAttribute("torneoSeleccionado", torneoId);
        return "FormularioInscripciones";
    }

    // Procesar la inscripción
    @PostMapping
    public String guardarInscripcion(@ModelAttribute Inscripcion inscripcion) {
        inscripcionService.guardar(inscripcion);
        return "redirect:/inscripciones";
    }

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

    // Mostrar inscripciones por torneo
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