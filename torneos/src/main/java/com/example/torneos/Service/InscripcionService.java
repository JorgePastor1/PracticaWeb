package com.example.torneos.Service;

import com.example.torneos.Repository.InscripcionRepository;
import com.example.torneos.model.Equipo;
import com.example.torneos.model.Inscripcion;
import com.example.torneos.model.Torneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public List<Inscripcion> obtenerTodas() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion guardar(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion buscarPorId(Long id) {
        return inscripcionRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }

    public Inscripcion inscribir(Equipo equipo, Torneo torneo) {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEquipo(equipo);
        inscripcion.setTorneo(torneo);
        inscripcion.setFechaInscripcion(LocalDate.now());
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion actualizar(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion actualizarParcial(Long id, Map<String, Object> updates) {
        Optional<Inscripcion> optional = inscripcionRepository.findById(id);
        if (optional.isEmpty()) return null;

        Inscripcion inscripcion = optional.get();

        updates.forEach((key, value) -> {
            switch (key) {
                case "fechaInscripcion" -> inscripcion.setFechaInscripcion(LocalDate.parse((String) value));
        }});

        return inscripcionRepository.save(inscripcion);
    }
}
