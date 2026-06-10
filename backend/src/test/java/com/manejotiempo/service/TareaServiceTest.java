package com.manejotiempo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.manejotiempo.model.Tarea;

class TareaServiceTest {
    private TareaService svc;

    @BeforeEach
    void setUp() { svc = new TareaService(); }

    @Test
    void createAndGet() {
        Tarea t = new Tarea(); t.setNombre("T");
        Tarea c = svc.create(t);
        assertNotNull(c.getTareaId());
        assertEquals("T", svc.findById(c.getTareaId()).get().getNombre());
    }

    @Test
    void updateAndDelete() {
        Tarea t = new Tarea(); t.setNombre("X");
        Tarea c = svc.create(t);
        c.setNombre("Y");
        assertTrue(svc.update(c.getTareaId(), c).isPresent());
        assertTrue(svc.delete(c.getTareaId()));
    }

    @Test
    void list() { List<Tarea> all = svc.findAll(); assertNotNull(all); }

    @Test
    void updateNotFoundAndFindById() {
        Tarea t = new Tarea(); t.setNombre("Find");
        Tarea c = svc.create(t);
        assertTrue(svc.findById(c.getTareaId()).isPresent());
        assertFalse(svc.update(9999, t).isPresent());
    }
}
