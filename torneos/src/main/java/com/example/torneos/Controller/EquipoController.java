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

    // Mostrar lista de equipos
    @GetMapping
    public String listarEquipos(Model model) {
        model.addAttribute("equipos", equipoService.obtenerTodos());
        return "ListaEquipos";
    }

    // Mostrar formulario para crear un nuevo equipo
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "FormularioEquipo";
    }

    // Procesar formulario para guardar equipo
    @PostMapping
    public String guardarEquipo(@ModelAttribute Equipo equipo) {
        equipoService.guardar(equipo);
        return "redirect:/equipos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Equipo equipo = equipoService.buscarPorId(id);
        if (equipo != null) {
            model.addAttribute("equipo", equipo);
            return "FormularioEditarEquipo";
        } else {
            return "redirect:/equipos";
        }
    }


    // Procesar edición del equipo
    @PostMapping("/actualizar")
    public String actualizarEquipo(@ModelAttribute Equipo equipo) {
        equipoService.actualizar(equipo);
        return "redirect:/equipos";
    }

    // Eliminar equipo por ID
    @GetMapping("/eliminar/{id}")
    public String eliminarEquipo(@PathVariable Long id) {
        equipoService.eliminar(id);
        return "redirect:/equipos";
    }
}
