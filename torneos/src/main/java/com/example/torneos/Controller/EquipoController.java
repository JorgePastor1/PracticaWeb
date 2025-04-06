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

    // Display list of teams
    @GetMapping
    public String listarEquipos(Model model) {
        model.addAttribute("equipos", equipoService.obtenerTodos());
        return "ListaEquipos"; // Returns the view for displaying the list
    }

    // Show form to create a new team
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "FormularioEquipo"; // Returns the form view for a new team
    }

    // Process form to save a new team
    @PostMapping
    public String guardarEquipo(@ModelAttribute Equipo equipo) {
        equipoService.guardar(equipo);
        return "redirect:/equipos"; // Redirect to the team list after saving
    }

    // Show form to edit an existing team by ID
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Equipo equipo = equipoService.buscarPorId(id);
        if (equipo != null) {
            model.addAttribute("equipo", equipo);
            return "FormularioEditarEquipo"; // Returns the edit form view
        } else {
            return "redirect:/equipos"; // Redirect if team not found
        }
    }

    // Process the team update form
    @PostMapping("/actualizar")
    public String actualizarEquipo(@ModelAttribute Equipo equipo) {
        equipoService.actualizar(equipo);
        return "redirect:/equipos"; // Redirect to team list after update
    }

    // Delete a team by ID
    @GetMapping("/eliminar/{id}")
    public String eliminarEquipo(@PathVariable Long id) {
        equipoService.eliminar(id);
        return "redirect:/equipos"; // Redirect to team list after deletion
    }
}
