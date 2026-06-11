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

import com.manejotiempo.model.Categoria;
import com.manejotiempo.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService svc;
    public CategoriaController(CategoriaService svc) { this.svc = svc; }

    @GetMapping
    public List<Categoria> list() { return svc.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> get(@PathVariable Integer id) { return svc.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody Categoria c) { return ResponseEntity.ok(svc.create(c)); }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria c) { return svc.update(id, c).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) { return svc.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build(); }
}
