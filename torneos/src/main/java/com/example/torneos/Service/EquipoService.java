package com.example.torneos.Service;

import com.example.torneos.Repository.EquipoRepository;
import com.example.torneos.model.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> obtenerTodos() {
        return equipoRepository.findAll();
    }

    public Equipo guardar(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Equipo buscarPorId(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    public Equipo actualizar(Equipo equipoActualizado) {
        return equipoRepository.save(equipoActualizado);
    }

    public void eliminar(Long id) {
        equipoRepository.deleteById(id);
    }

    public Equipo actualizarParcial(Long id, Map<String, Object> updates) {
        Optional<Equipo> optional = equipoRepository.findById(id);
        if (optional.isEmpty())
            return null;

        Equipo equipo = optional.get();

        updates.forEach((key, value) -> {
            switch (key) {
                case "nombre" -> equipo.setNombre((String) value);
                case "ciudad" -> equipo.setCiudad((String) value);
                case "numJugadores" -> equipo.setNumJugadores((Integer) value);
            }
        });

        return equipoRepository.save(equipo);
    }
}
