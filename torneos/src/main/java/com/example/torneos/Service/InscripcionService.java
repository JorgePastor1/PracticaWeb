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

    // Obtener todas las inscripciones registradas
    public List<Inscripcion> obtenerTodas() {
        return inscripciones;
    }

    // Crear nueva inscripción con estado 'pendiente' y fecha actual
    public void inscribir(Equipo equipo, Torneo torneo) {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(siguienteId++);
        inscripcion.setEquipo(equipo);
        inscripcion.setTorneo(torneo);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setEstado("pendiente");

        inscripciones.add(inscripcion);
    }

    // Buscar inscripción por ID
    public Inscripcion buscarPorId(Long id) {
        return inscripciones.stream()
                .filter(inscripcion -> inscripcion.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Actualizar todos los campos de una inscripción existente
    public void actualizar(Inscripcion inscripcionActualizada) {
        Inscripcion inscripcion = buscarPorId(inscripcionActualizada.getId());
        if (inscripcion != null) {
            inscripcion.setEquipo(inscripcionActualizada.getEquipo());
            inscripcion.setTorneo(inscripcionActualizada.getTorneo());
            inscripcion.setEstado(inscripcionActualizada.getEstado());
            inscripcion.setFechaInscripcion(inscripcionActualizada.getFechaInscripcion());
        }
    }

    // Actualización parcial (PATCH)
    public Inscripcion actualizarParcial(Long id, Map<String, Object> updates) {
        Inscripcion inscripcion = buscarPorId(id);
        if (inscripcion == null) {
            return null;
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "fechaInscripcion":
                    inscripcion.setFechaInscripcion(LocalDate.parse(value.toString()));
                    break;
                case "estado":
                    inscripcion.setEstado(value.toString());
                    break;
                // Puedes extender con más campos si es necesario
            }
        });

        return inscripcion;
    }
}
