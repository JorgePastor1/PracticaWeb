package com.example.torneos.Service;

import com.example.torneos.model.Torneo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TorneoService {

    private final List<Torneo> torneos = new ArrayList<>();
    private Long siguienteId = 1L;

    public TorneoService() {
        torneos.add(new Torneo(
                siguienteId++, "Torneo de Invierno", "Madrid", LocalDate.of(2025, 5, 15), "Fútbol", "Juvenil"));
    }

    public List<Torneo> obtenerTodos() {
        return torneos;
    }

    public Torneo guardar(Torneo torneo) {
        torneo.setId(siguienteId++);
        torneos.add(torneo);
        return torneo;
    }

    public Torneo buscarPorId(Long id) {
        return torneos.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public Torneo actualizar(Torneo torneoActualizado) {
        for (int i = 0; i < torneos.size(); i++) {
            if (torneos.get(i).getId().equals(torneoActualizado.getId())) {
                torneos.set(i, torneoActualizado);
                return torneoActualizado;
            }
        }
        return torneoActualizado;
    }

    public void eliminar(Long id) {
        torneos.removeIf(t -> t.getId().equals(id));
    }

    public Torneo actualizarParcial(Long id, Map<String, Object> updates) {
        Torneo torneo = buscarPorId(id);
        if (torneo == null) {
            return null;
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre":
                    torneo.setNombre((String) value);
                    break;
                case "lugar":
                    torneo.setLugar((String) value);
                    break;
                case "fecha":
                    torneo.setFecha(LocalDate.parse((String) value));
                    break;
                case "deporte":
                    torneo.setDeporte((String) value);
                    break;
                case "categoria":
                    torneo.setCategoria((String) value);
                    break;
                default:
                    break;
            }
        });

        return torneo;
    }
}
