package com.example.torneos.Controller;

import com.example.torneos.Service.EquipoService;
import com.example.torneos.Service.InscripcionService;
import com.example.torneos.Service.TorneoService;
import com.example.torneos.model.Equipo;
import com.example.torneos.model.Inscripcion;
import com.example.torneos.model.Torneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionRestController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private TorneoService torneoService;

    // Obtener todas las inscripciones
    @GetMapping
    public List<Inscripcion> listarInscripciones() {
        return inscripcionService.obtenerTodas();
    }

    // Obtener una inscripción por ID
    @GetMapping("/{id}")
    public Inscripcion obtenerPorId(@PathVariable Long id) {
        return inscripcionService.buscarPorId(id);
    }

    // Crear una inscripción a partir de IDs de equipo y torneo
    @PostMapping
    public Inscripcion crearInscripcion(@RequestParam Long equipoId, @RequestParam Long torneoId) {
        Equipo equipo = equipoService.buscarPorId(equipoId);
        Torneo torneo = torneoService.buscarPorId(torneoId);

        if (equipo != null && torneo != null) {
            inscripcionService.inscribir(equipo, torneo);
            // Buscamos la inscripción recién creada
            List<Inscripcion> todas = inscripcionService.obtenerTodas();
            return todas.get(todas.size() - 1); // última añadida
        } else {
            return null; // o lanzar excepción
        }
    }

    // Actualización completa
    @PutMapping("/{id}")
    public Inscripcion actualizar(@PathVariable Long id, @RequestBody Inscripcion inscripcion) {
        inscripcion.setId(id);
        inscripcionService.actualizar(inscripcion);
        return inscripcionService.buscarPorId(id);
    }

    // Actualización parcial
    @PatchMapping("/{id}")
    public Inscripcion patch(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return inscripcionService.actualizarParcial(id, updates);
    }
}
