package com.example.torneos.Service;

import com.example.torneos.model.Equipo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipoService {

    private final List<Equipo> equipos = new ArrayList<>();
    private Long siguienteId = 1L;

    public EquipoService() {
        equipos.add(new Equipo(siguienteId++, "Los Tigres", "Juvenil", "Barcelona", 11, "Carlos Pérez"));
    }

    public List<Equipo> obtenerTodos() {
        return equipos;
    }

    public void guardar(Equipo equipo) {
        equipo.setId(siguienteId++);
        equipos.add(equipo);
    }

    public Equipo buscarPorId(Long id) {
        return equipos.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }
}
