package com.example.torneos.Service;

import com.example.torneos.model.Equipo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return equipos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void actualizar(Equipo equipoActualizado) {
        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i).getId().equals(equipoActualizado.getId())) {
                equipos.set(i, equipoActualizado);
                return;
            }
        }
    }

    public void eliminar(Long id) {
        equipos.removeIf(e -> e.getId().equals(id));
    }
    public Equipo actualizarParcial(Long id, Map<String, Object> updates) {
        Equipo equipo = buscarPorId(id);
        if (equipo == null) {
            return null;
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre":
                    equipo.setNombre((String) value);
                    break;
                case "categoria":
                    equipo.setCategoria((String) value);
                    break;
                case "ciudad":
                    equipo.setCiudad((String) value);
                    break;
                case "numJugadores":
                    equipo.setNumJugadores((Integer) value);
                    break;
                default:
                    // campo no válido, se ignora
                    break;
            }
        });

        return equipo;
    }

}