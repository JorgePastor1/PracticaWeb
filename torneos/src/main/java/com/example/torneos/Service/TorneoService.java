package com.example.torneos.Service;

import com.example.torneos.model.Torneo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TorneoService {

    private final List<Torneo> torneos = new ArrayList<>();
    private Long siguienteId = 1L;

    public TorneoService() {
        torneos.add(new Torneo(
                siguienteId++, LocalDate.of(2025, 5, 15), "Fútbol", "Madrid", new ArrayList<>(), 100.0, "Torneo de Invierno"
        ));
    }

    public List<Torneo> obtenerTodos() {
        return torneos;
    }

    public void guardar(Torneo torneo) {
        torneo.setId(siguienteId++);
        torneo.setEquipos(new ArrayList<>());
        torneos.add(torneo);
    }

    public Torneo buscarPorId(Long id) {
        return torneos.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    // ✅ Método para actualizar torneo
    public void actualizar(Torneo torneoActualizado) {
        for (int i = 0; i < torneos.size(); i++) {
            if (torneos.get(i).getId().equals(torneoActualizado.getId())) {
                torneos.set(i, torneoActualizado);
                return;
            }
        }
    }

    // ❌ Método para eliminar torneo
    public void eliminar(Long id) {
        torneos.removeIf(t -> t.getId().equals(id));
    }
}
