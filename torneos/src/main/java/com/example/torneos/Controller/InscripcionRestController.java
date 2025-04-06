package com.example.torneos.Controller;

import com.example.torneos.Service.InscripcionService;
import com.example.torneos.model.Inscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionRestController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public List<Inscripcion> listarInscripciones() {
        return inscripcionService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Inscripcion obtenerPorId(@PathVariable Long id) {
        return inscripcionService.buscarPorId(id);
    }

    @PostMapping
    public Inscripcion crear(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.guardar(inscripcion);
    }

    @PutMapping("/{id}")
    public Inscripcion actualizar(@PathVariable Long id, @RequestBody Inscripcion inscripcion) {
        inscripcion.setId(id);
        return inscripcionService.actualizar(inscripcion);
    }

    @PatchMapping("/{id}")
    public Inscripcion patch(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return inscripcionService.actualizarParcial(id, updates);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
    }
}