package com.example.torneos.Controller;

import com.example.torneos.Service.TorneoService;
import com.example.torneos.model.Torneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/torneos") // Base URL path for all endpoints in this controller
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    // 📝 Show list of all tournaments
    @GetMapping
    public String listarTorneos(Model model) {
        model.addAttribute("torneos", torneoService.obtenerTodos()); // Add list of tournaments to the model
        return "ListaTorneos"; // View that displays the tournament list
    }

    // ➕ Show form to create a new tournament
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("torneo", new Torneo()); // Add a new empty tournament object to the model
        return "FormularioTorneos"; // View that renders the tournament creation form
    }

    // 💾 Save a new tournament
    @PostMapping
    public String guardarTorneo(@ModelAttribute Torneo torneo) {
        torneoService.guardar(torneo); // Call the service to save the tournament
        return "redirect:/torneos"; // Redirect to the list after saving
    }

    // 🔄 Show edit form for a specific tournament
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Torneo torneo = torneoService.buscarPorId(id); // Search tournament by ID
        if (torneo != null) {
            model.addAttribute("torneo", torneo); // Add found tournament to the model
            return "FormularioEditarTorneo"; // View to render the edit form
        }
        return "redirect:/torneos"; // If not found, redirect to the list
    }

    // ✅ Process tournament update
    @PostMapping("/actualizar")
    public String actualizarTorneo(@ModelAttribute Torneo torneo) {
        torneoService.actualizar(torneo); // Update tournament using the service
        return "redirect:/torneos"; // Redirect to the list after update
    }

    // ❌ Delete tournament by ID
    @GetMapping("/eliminar/{id}")
    public String eliminarTorneo(@PathVariable Long id) {
        torneoService.eliminar(id); // Call service to delete the tournament
        return "redirect:/torneos"; // Redirect to the list after deletion
    }
}
