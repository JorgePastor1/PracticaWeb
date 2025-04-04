package com.example.torneos.Controller;

import com.example.torneos.Service.TorneoService;
import com.example.torneos.model.Torneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String guardarTorneo(@ModelAttribute Torneo torneo) {
        torneoService.guardar(torneo);
        return "redirect:/torneos";
    }

}
