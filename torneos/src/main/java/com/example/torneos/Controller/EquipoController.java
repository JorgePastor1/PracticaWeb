package com.example.torneos.Controller;

import com.example.torneos.Service.EquipoService;
import com.example.torneos.model.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    // Obtener todos los equipos
    @GetMapping
    public List<Equipo> listarEquipos() {
        return equipoService.obtenerTodos();
    }

    // Obtener un equipo específico por ID
    @GetMapping("/{id}")
    public Equipo obtenerEquipo(@PathVariable Long id) {
        return equipoService.buscarPorId(id);
    }

    // Crear un nuevo equipo
    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo equipo) {
        return equipoService.guardar(equipo);
    }

    // Actualizar parcialmente un equipo (PATCH)
    @PatchMapping("/{id}")
    public Equipo actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        Equipo equipoExistente = equipoService.buscarPorId(id);
        if (equipoExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado");
        }

        // Actualizamos solo los campos que fueron enviados en la solicitud PATCH
        if (equipo.getNombre() != null) {
            equipoExistente.setNombre(equipo.getNombre());
        }
        if (equipo.getCiudad() != null) {
            equipoExistente.setCiudad(equipo.getCiudad());
        }
        if (equipo.getCategoria() != null) {
            equipoExistente.setCategoria(equipo.getCategoria());
        }
        if (equipo.getNumJugadores() > 0) {
            equipoExistente.setNumJugadores(equipo.getNumJugadores());
        }
        if (equipo.getNombreCapitan() != null) {
            equipoExistente.setNombreCapitan(equipo.getNombreCapitan());
        }

        // Guardamos los cambios
        equipoService.guardar(equipoExistente);
        return equipoExistente;  // Retornamos el equipo actualizado en formato JSON
    }

    // Eliminar un equipo
    @DeleteMapping("/{id}")
    public void eliminarEquipo(@PathVariable Long id) {
        equipoService.eliminar(id);
    }
}
