package com.example.gestor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {
    @GetMapping("/pepe")
    public String pepe() {
        return "Hola, mundo. Te responde el servidor de Arnau.";
    }

    @GetMapping("/estado")
    public String estado() {
        return "Servidor en funcionamiento";
    }

    @GetMapping("/prestamos/resumen")
    public String resumen() {
        return "Esta aplicación gestionará los préstamos de material del mossad";
    }

    @GetMapping("/anyo")
    public int anyo() {
        return 2026;
    }
}
