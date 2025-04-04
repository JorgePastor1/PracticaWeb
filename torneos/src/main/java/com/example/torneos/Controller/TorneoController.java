package com.example.torneos.Controller;

import com.example.torneos.model.Equipo;
import com.example.torneos.model.Torneo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/torneos")
public class TorneoController {

    private List<Torneo> torneos = new ArrayList<>();
    private Long siguienteId = 1L;

    public TorneoController() {
        // Torneo de ejemplo
        torneos.add(new Torneo(
                siguienteId++,
                LocalDate.of(2025, 5, 15),
                "Fútbol",
                "Madrid",
                new ArrayList<>(),
                100.0,
                "Sin clasificar"
        ));
    }

    @GetMapping
    public String listarTorneos(Model model) {
        model.addAttribute("torneos", torneos);
        return "torneos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("torneo", new Torneo());
        return "torneos/formulario";
    }

    @PostMapping
    public String guardarTorneo(@ModelAttribute Torneo torneo) {
        torneo.setId(siguienteId++);
        torneo.setEquipos(new ArrayList<>()); // inicialmente sin equipos
        torneos.add(torneo);
        return "redirect:/torneos";
    }
}
