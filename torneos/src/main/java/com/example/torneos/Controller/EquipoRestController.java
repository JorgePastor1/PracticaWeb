package com.example.torneos.Controller;

import com.example.torneos.Service.EquipoService;
import com.example.torneos.model.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/equipos")
public class EquipoRestController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public List<Equipo> listarEquipos() {
        return equipoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Equipo obtenerEquipoPorId(@PathVariable Long id) {
        return equipoService.buscarPorId(id);
    }

    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo equipo) {
        return equipoService.guardar(equipo);
    }

    @PutMapping("/{id}")
    public Equipo actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        equipo.setId(id);
        return equipoService.actualizar(equipo);
    }

    @PatchMapping("/{id}")
    public Equipo patchEquipo(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return equipoService.actualizarParcial(id, updates);
    }

    @DeleteMapping("/{id}")
    public void eliminarEquipo(@PathVariable Long id) {
        equipoService.eliminar(id);
    }
}
