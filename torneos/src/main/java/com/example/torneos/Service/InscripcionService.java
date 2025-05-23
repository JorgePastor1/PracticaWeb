package com.example.torneos.Service;

import com.example.torneos.Repository.EquipoRepository;
import com.example.torneos.Repository.InscripcionRepository;
import com.example.torneos.Repository.TorneoRepository;
import com.example.torneos.model.Equipo;
import com.example.torneos.model.Inscripcion;
import com.example.torneos.model.Torneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private TorneoRepository torneoRepository;

    public List<Inscripcion> obtenerTodas() {
        return inscripcionRepository.findAll();
    }

    // ✅ Usado en formulario completo (con objeto Inscripcion)
    public Inscripcion guardar(Inscripcion inscripcion) {
        Equipo equipo = equipoRepository.findById(inscripcion.getEquipo().getId())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Torneo torneo = torneoRepository.findById(inscripcion.getTorneo().getId())
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado"));

        inscripcion.setEquipo(equipo);
        inscripcion.setTorneo(torneo);

        return inscripcionRepository.save(inscripcion);
    }

    // ✅ Usado en formulario simplificado (solo ids)
    public void inscribirEquipoEnTorneo(Long equipoId, Long torneoId) {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Torneo torneo = torneoRepository.findById(torneoId)
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado"));

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEquipo(equipo);
        inscripcion.setTorneo(torneo);
        inscripcion.setFechaInscripcion(LocalDate.now()); // puedes ajustar la fecha si lo deseas

        inscripcionRepository.save(inscripcion);
    }

    public Inscripcion buscarPorId(Long id) {
        return inscripcionRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }

    public Inscripcion actualizar(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion actualizarParcial(Long id, Map<String, Object> updates) {
        Inscripcion inscripcion = buscarPorId(id);
        if (inscripcion == null)
            return null;

        if (updates.containsKey("fechaInscripcion")) {
            inscripcion.setFechaInscripcion(LocalDate.parse((String) updates.get("fechaInscripcion")));
        }
        return inscripcionRepository.save(inscripcion);
    }
}
