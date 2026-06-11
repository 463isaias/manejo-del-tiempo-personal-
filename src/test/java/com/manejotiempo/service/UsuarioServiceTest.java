package com.manejotiempo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.manejotiempo.model.Usuario;

class UsuarioServiceTest {
    private UsuarioService svc;

    @BeforeEach
    void setUp() { svc = new UsuarioService(); }

    @Test
    void createAndFind() {
        Usuario u = new Usuario();
        u.setNombre("A");
        Usuario created = svc.create(u);
        assertNotNull(created.getUsuarioId());
        assertEquals("A", svc.findById(created.getUsuarioId()).get().getNombre());
    }

    @Test
    void updateDelete() {
        Usuario u = new Usuario(); u.setNombre("B");
        Usuario c = svc.create(u);
        c.setNombre("C");
        assertTrue(svc.update(c.getUsuarioId(), c).isPresent());
        assertTrue(svc.delete(c.getUsuarioId()));
        assertFalse(svc.findById(c.getUsuarioId()).isPresent());
    }

    @Test
    void listEmpty() {
        List<Usuario> all = svc.findAll();
        assertNotNull(all);
    }

    @Test
    void updateNonExisting() {
        Usuario u = new Usuario(); u.setNombre("No");
        assertFalse(svc.update(9999, u).isPresent());
        assertFalse(svc.delete(9999));
    }
}
