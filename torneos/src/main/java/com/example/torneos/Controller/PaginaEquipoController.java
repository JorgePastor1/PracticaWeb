package com.example.torneos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaEquipoController {

    @GetMapping("/equipos/lista")
    public String mostrarPaginaEquipos() {
        return "ListaEquipos"; // Thymeleaf buscará ListaEquipos.html en Templates
    }
}
