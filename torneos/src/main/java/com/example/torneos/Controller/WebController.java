package com.example.torneos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "index"; // redirige a index.html
    }

    @GetMapping("/torneos")
    public String mostrarTorneos(Model model) {
        List<String> torneos = Arrays.asList("Torneo Primavera", "Copa Invierno", "Liga Verano");
        model.addAttribute("torneos", torneos);
        return "torneos"; // redirige a torneos.html
    }

    @GetMapping("/equipos")
    public String mostrarEquipos(Model model) {
        List<String> torneos = Arrays.asList("Torneo Primavera", "Copa Invierno", "Liga Verano");
        model.addAttribute("equipos", torneos);
        return "equipos"; // redirige a torneos.html
    }
}
