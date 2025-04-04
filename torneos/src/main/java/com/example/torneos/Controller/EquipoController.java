package com.example.torneos.Controller;

import com.example.torneos.Service.EquipoService;
import com.example.torneos.model.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public String listarEquipos(Model model) {
        model.addAttribute("equipos", equipoService.obtenerTodos());
        return "equipos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "FormularioEquipo";
    }

    @PostMapping
    public String guardarEquipo(@ModelAttribute Equipo equipo) {
        equipoService.guardar(equipo);
        return "redirect:/equipos";
    }

}
