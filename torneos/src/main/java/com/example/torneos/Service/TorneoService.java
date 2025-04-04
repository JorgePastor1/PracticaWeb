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
                siguienteId++, LocalDate.of(2025, 5, 15), "Fútbol", "Madrid", new ArrayList<>(), 100.0, "Sin clasificar"
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
}
