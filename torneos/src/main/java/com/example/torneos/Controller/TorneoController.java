package com.example.torneos.Controller;

import com.example.torneos.Service.TorneoService;
import com.example.torneos.model.Torneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/torneos")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    @GetMapping
    public String listarTorneos(Model model) {
        model.addAttribute("torneos", torneoService.obtenerTodos());
        return "ListaTorneos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("torneo", new Torneo());
        return "FormularioTorneos";
    }

    @PostMapping
    public String guardarTorneo(@ModelAttribute Torneo torneo, Model model) {
        if (torneo.getFecha() != null && torneo.getFecha().isBefore(LocalDate.now())) {
            model.addAttribute("torneo", torneo);
            model.addAttribute("error", "La fecha del torneo debe ser posterior a la actual.");
            return "FormularioTorneos";
        }

        torneoService.guardar(torneo);
        return "redirect:/torneos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Torneo torneo = torneoService.buscarPorId(id);
        if (torneo != null) {
            model.addAttribute("torneo", torneo);
            return "FormularioEditarTorneo";
        }
        return "redirect:/torneos";
    }

    @PostMapping("/actualizar")
    public String actualizarTorneo(@ModelAttribute Torneo torneo, Model model) {
        if (torneo.getFecha() != null && torneo.getFecha().isBefore(LocalDate.now())) {
            model.addAttribute("torneo", torneo);
            model.addAttribute("error", "La fecha del torneo debe ser posterior a la actual.");
            return "FormularioEditarTorneo";
        }

        torneoService.actualizar(torneo);
        return "redirect:/torneos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarTorneo(@PathVariable Long id) {
        torneoService.eliminar(id);
        return "redirect:/torneos";
    }
}
