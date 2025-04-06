package com.example.torneos.Service;

import com.example.torneos.model.Equipo;
import com.example.torneos.model.Inscripcion;
import com.example.torneos.model.Torneo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InscripcionService {

    private final List<Inscripcion> inscripciones = new ArrayList<>();
    private Long siguienteId = 1L;

    public List<Inscripcion> obtenerTodas() {
        return inscripciones;
    }

    public void inscribir(Equipo equipo, Torneo torneo) {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(siguienteId++);
        inscripcion.setEquipo(equipo);
        inscripcion.setTorneo(torneo);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setEstado("pendiente");

        inscripciones.add(inscripcion);
    }

    public Inscripcion buscarPorId(Long id) {
        return inscripciones.stream()
                .filter(inscripcion -> inscripcion.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void actualizar(Long id, Long equipoId, Long torneoId, String estado, LocalDate fechaInscripcion) {
        Inscripcion inscripcion = buscarPorId(id);
        if (inscripcion != null) {
            Equipo equipo = new Equipo();
            equipo.setId(equipoId);

            Torneo torneo = new Torneo();
            torneo.setId(torneoId);

            inscripcion.setEquipo(equipo);
            inscripcion.setTorneo(torneo);
            inscripcion.setEstado(estado);
            inscripcion.setFechaInscripcion(fechaInscripcion);
        }
    }
}
