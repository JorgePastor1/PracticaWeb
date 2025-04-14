package com.example.torneos.Service;

import com.example.torneos.Repository.TorneoRepository;
import com.example.torneos.model.Torneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;

    public List<Torneo> obtenerTodos() {
        return torneoRepository.findAll();
    }

    public Torneo guardar(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    public Torneo buscarPorId(Long id) {
        return torneoRepository.findById(id).orElse(null);
    }

    public Torneo actualizar(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    public void eliminar(Long id) {
        torneoRepository.deleteById(id);
    }

    public Torneo actualizarParcial(Long id, Map<String, Object> updates) {
        Torneo torneo = buscarPorId(id);
        if (torneo == null) {
            return null;
        }

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre" -> torneo.setNombre((String) value);
                case "lugar" -> torneo.setLugar((String) value);
                case "fecha" -> torneo.setFecha(LocalDate.parse((String) value));
                case "deporte" -> torneo.setDeporte((String) value);
                case "categoria" -> torneo.setCategoria((String) value);
            }
        });

        return torneoRepository.save(torneo);
    }
}
