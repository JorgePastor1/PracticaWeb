package com.example.torneos.Controller;

import com.example.torneos.Service.TorneoService;
import com.example.torneos.model.Torneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/torneos")
public class TorneoRestController {

    @Autowired
    private TorneoService torneoService;

    @GetMapping
    public List<Torneo> listarTorneos() {
        return torneoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Torneo obtenerTorneoPorId(@PathVariable Long id) {
        return torneoService.buscarPorId(id);
    }

    @PostMapping
    public Torneo crearTorneo(@RequestBody Torneo torneo) {
        return torneoService.guardar(torneo);
    }

    @PutMapping("/{id}")
    public Torneo actualizarTorneo(@PathVariable Long id, @RequestBody Torneo torneo) {
        torneo.setId(id);
        return torneoService.actualizar(torneo);
    }

    @PatchMapping("/{id}")
    public Torneo patchTorneo(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return torneoService.actualizarParcial(id, updates);
    }

    @DeleteMapping("/{id}")
    public void eliminarTorneo(@PathVariable Long id) {
        torneoService.eliminar(id);
    }
}
