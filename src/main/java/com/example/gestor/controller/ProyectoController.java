package com.example.gestor.controller;

import com.example.gestor.model.Proyecto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    private List<Proyecto> proyectos = new ArrayList<>();

    public ProyectoController() {
        proyectos.add(new Proyecto(1, "Alpha", "Primer proyecto", true, 3));
        proyectos.add(new Proyecto(2, "Beta", "Segundo proyecto", false, 1));
        proyectos.add(new Proyecto(3, "Gamma", "Tercer proyecto", true, 5));
    }

    @GetMapping()
    public List<Proyecto> listarProyectos() {
        return proyectos;
    }

    @GetMapping("/{id}")
    public Proyecto detalleProyecto(@PathVariable int id) {
        return proyectos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/{id}/incidencias")
    public String incidenciasProyecto(@PathVariable int id) {
        return "Incidencias del proyecto " + id;
    }

    @GetMapping("/{proyectoId}/incidencias/{incidenciaId}")
    public String incidenciaConcreta(
            @PathVariable int proyectoId,
            @PathVariable int incidenciaId) {

        return "Incidencia " + incidenciaId + " del proyecto " + proyectoId;
    }

    @GetMapping("/incidencias/busqueda")
    public String buscarIncidencias(
            @RequestParam(defaultValue = "todas") String prioridad,
            @RequestParam(defaultValue = "1") int pagina) {

        return "Buscando incidencias con prioridad " + prioridad + " en la página " + pagina;
    }

    @PostMapping
    public Proyecto crearProyecto(@RequestBody Proyecto proyecto) {
        proyectos.add(proyecto);
        return proyecto;
    }
}
