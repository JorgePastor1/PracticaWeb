package com.example.torneos.Service;

import com.example.torneos.model.Equipo;
import com.example.torneos.model.Inscripcion;
import com.example.torneos.model.Torneo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public Inscripcion actualizar(Inscripcion inscripcionActualizada) {
        Inscripcion original = buscarPorId(inscripcionActualizada.getId());
        if (original != null) {
            original.setEquipo(inscripcionActualizada.getEquipo());
            original.setTorneo(inscripcionActualizada.getTorneo());
            original.setFechaInscripcion(inscripcionActualizada.getFechaInscripcion());
            original.setEstado(inscripcionActualizada.getEstado());
        }
        return original;
    }

    public Inscripcion actualizarParcial(Long id, Map<String, Object> updates) {
        Inscripcion inscripcion = buscarPorId(id);
        if (inscripcion == null) {
            return null;
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "estado":
                    inscripcion.setEstado((String) value);
                    break;
                case "fechaInscripcion":
                    inscripcion.setFechaInscripcion(LocalDate.parse((String) value));
                    break;
                default:
                    break;
            }
        });

        return inscripcion;
    }

    // Método para guardar desde el controlador REST
    public Inscripcion guardar(Inscripcion inscripcion) {
        inscripcion.setId(siguienteId++);
        if (inscripcion.getFechaInscripcion() == null) {
            inscripcion.setFechaInscripcion(LocalDate.now());
        }
        if (inscripcion.getEstado() == null) {
            inscripcion.setEstado("pendiente");
        }
        inscripciones.add(inscripcion);
        return inscripcion;
    }

    // Método eliminar
    public void eliminar(Long id) {
        inscripciones.removeIf(i -> i.getId().equals(id));
    }
}
