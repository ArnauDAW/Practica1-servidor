package com.example.gestor.controller;

import com.example.gestor.model.Tarea;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    @GetMapping("/ejemplo")
    public Tarea ejemplo() {
        return new Tarea(1, "Revisar el login", "alta", false);
    }

    @GetMapping
    public List<Tarea> lista() {
        return List.of(
                new Tarea(1, "Revisar el login", "alta", false),
                new Tarea(2, "Actualizar dependencias", "baja", true));
    }
}