package com.example.torneos.controller;

import com.example.torneos.model.Equipo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    private List<Equipo> equipos = new ArrayList<>();
    private Long siguienteId = 1L;

    public EquipoController() {
        // Ejemplo de equipo inicial
        equipos.add(new Equipo(siguienteId++, "Los Tigres", "Juvenil", "Barcelona", 11, "Carlos Pérez"));
    }

    @GetMapping
    public String listarEquipos(Model model) {
        model.addAttribute("equipos", equipos);
        return "equipos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "equipos/formulario";
    }

    @PostMapping
    public String guardarEquipo(@ModelAttribute Equipo equipo) {
        equipo.setId(siguienteId++);
        equipos.add(equipo);
        return "redirect:/equipos";
    }
}
