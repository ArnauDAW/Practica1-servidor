package com.example.gestor.controller;

import com.example.gestor.model.Proyecto;
import com.example.gestor.model.Tarea;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    private List<Proyecto> proyectos = new ArrayList<>();
    private int siguienteId = 1;

    public ProyectoController() {
        proyectos.add(new Proyecto(1, "Alpha", "Primer proyecto", true, 3));
        proyectos.add(new Proyecto(2, "Beta", "Segundo proyecto", false, 1));
        proyectos.add(new Proyecto(3, "Gamma", "Tercer proyecto", true, 5));
    }

    @GetMapping
    public List<Proyecto> listarProyectos(
            @RequestParam(name = "activo", required = false) Boolean activo) {
        if (activo == null) {
            return proyectos;
        }
        List<Proyecto> resultado = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.isActivo() == activo) {
                resultado.add(proyecto);
            }
        }
        return resultado;
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
        proyecto.setId(siguienteId);
        siguienteId = siguienteId + 1;
        proyectos.add(proyecto);
        return proyecto;
    }

    @PutMapping("/{id}")
    public Proyecto actualizar(
            @PathVariable(name = "id") int id,
            @RequestBody Proyecto datos) {

        for (int i = 0; i < proyectos.size(); i++) {
            if (proyectos.get(i).getId() == id) {
                datos.setId(id);
                proyectos.set(i, datos);
                return datos;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable(name = "id") int id) {
        proyectos.removeIf(proyecto -> proyecto.getId() == id);
    }
}
