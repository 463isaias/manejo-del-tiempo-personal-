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

import com.manejotiempo.model.Recordatorio;
import com.manejotiempo.service.RecordatorioService;

@RestController
@RequestMapping("/api/recordatorios")
public class RecordatorioController {
    private final RecordatorioService svc;
    public RecordatorioController(RecordatorioService svc) { this.svc = svc; }

    @GetMapping
    public List<Recordatorio> list() { return svc.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Recordatorio> get(@PathVariable Integer id) { return svc.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public ResponseEntity<Recordatorio> create(@RequestBody Recordatorio r) { return ResponseEntity.ok(svc.create(r)); }

    @PutMapping("/{id}")
    public ResponseEntity<Recordatorio> update(@PathVariable Integer id, @RequestBody Recordatorio r) { return svc.update(id, r).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) { return svc.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build(); }
}
