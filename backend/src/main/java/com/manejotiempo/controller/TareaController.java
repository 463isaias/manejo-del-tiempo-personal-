package com.manejotiempo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manejotiempo.model.Tarea;
import com.manejotiempo.service.TareaService;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    private final TareaService svc;
    public TareaController(TareaService svc) { this.svc = svc; }

    @GetMapping
    public List<Tarea> list() { return svc.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> get(@PathVariable Integer id) { return svc.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public ResponseEntity<Tarea> create(@RequestBody Tarea t) { return ResponseEntity.ok(svc.create(t)); }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> update(@PathVariable Integer id, @RequestBody Tarea t) { return svc.update(id, t).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) { return svc.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build(); }
}
