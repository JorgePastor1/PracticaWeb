package com.example.torneos.Controller;

import com.example.torneos.Service.InscripcionService;
import com.example.torneos.model.Inscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionRestController {

    @Autowired
    private InscripcionService inscripcionService;

    // Obtener todas las inscripciones
    @GetMapping
    public List<Inscripcion> listarInscripciones() {
        return inscripcionService.obtenerTodas();
    }

    // Obtener una inscripción por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerInscripcion(@PathVariable Long id) {
        Inscripcion inscripcion = inscripcionService.buscarPorId(id);
        if (inscripcion != null) {
            return ResponseEntity.ok(inscripcion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una inscripción a partir de IDs de equipo y torneo
    @PostMapping
    public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        try {
            Inscripcion creada = inscripcionService.guardar(inscripcion);
            return ResponseEntity.ok(creada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Actualización completa
    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizarInscripcion(
            @PathVariable Long id,
            @RequestBody Inscripcion inscripcion) {
        inscripcion.setId(id);
        return ResponseEntity.ok(inscripcionService.actualizar(inscripcion));
    }

    // Actualización parcial
    @PatchMapping("/{id}")
    public Inscripcion actualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return inscripcionService.actualizarParcial(id, updates);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
