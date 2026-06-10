package com.manejotiempo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.manejotiempo.model.Categoria;

class CategoriaServiceTest {
    private CategoriaService svc;

    @BeforeEach
    void setUp() { svc = new CategoriaService(); }

    @Test
    void createGetUpdateDelete() {
        Categoria c = new Categoria(); c.setNombre("Cat");
        Categoria r = svc.create(c);
        assertNotNull(r.getCategoriaId());
        r.setNombre("Cat2");
        assertTrue(svc.update(r.getCategoriaId(), r).isPresent());
        assertTrue(svc.delete(r.getCategoriaId()));
    }

    @Test
    void list() { List<Categoria> all = svc.findAll(); assertNotNull(all); }

    @Test
    void findByIdAndNotFoundCases() {
        Categoria c = new Categoria(); c.setNombre("Z");
        Categoria created = svc.create(c);
        assertTrue(svc.findById(created.getCategoriaId()).isPresent());
        assertFalse(svc.update(9999, c).isPresent());
        assertFalse(svc.delete(9999));
    }
}
