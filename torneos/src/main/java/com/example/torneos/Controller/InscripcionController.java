package com.example.torneos.Controller;

import com.example.torneos.Service.EquipoService;
import com.example.torneos.Service.InscripcionService;
import com.example.torneos.Service.TorneoService;
import com.example.torneos.model.Equipo;
import com.example.torneos.model.Torneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private TorneoService torneoService;

    @GetMapping
    public String listarInscripciones(Model model) {
        model.addAttribute("inscripciones", inscripcionService.obtenerTodas());
        return "inscripciones/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("equipos", equipoService.obtenerTodos());
        model.addAttribute("torneos", torneoService.obtenerTodos());
        return "inscripciones/formulario";
    }

    @PostMapping
    public String procesarInscripcion(@RequestParam Long equipoId, @RequestParam Long torneoId, RedirectAttributes redirectAttributes) {
        Equipo equipo = equipoService.buscarPorId(equipoId);
        Torneo torneo = torneoService.buscarPorId(torneoId);

        if (equipo == null || torneo == null) {
            redirectAttributes.addFlashAttribute("error", "El equipo o el torneo no son válidos.");
            return "redirect:/inscripciones/nueva";
        }

        inscripcionService.inscribir(equipo, torneo);
        return "redirect:/inscripciones";
    }
}
