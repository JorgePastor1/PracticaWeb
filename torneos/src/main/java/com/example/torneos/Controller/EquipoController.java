package com.example.torneos.Controller;

import com.example.torneos.Service.EquipoService;
import com.example.torneos.model.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public List<Equipo> listarEquipos() {
        return equipoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Equipo obtenerEquipo(@PathVariable Long id) {
        return equipoService.buscarPorId(id);
    }

    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo equipo) {
        return equipoService.guardar(equipo);
    }
}
