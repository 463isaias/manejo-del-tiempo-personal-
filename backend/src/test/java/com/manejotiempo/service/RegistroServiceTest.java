package com.manejotiempo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.manejotiempo.model.Registro;

class RegistroServiceTest {
    private RegistroService svc;

    @BeforeEach
    void setUp() { svc = new RegistroService(); }

    @Test
    void crud() {
        Registro r = new Registro();
        Registro c = svc.create(r);
        assertNotNull(c.getId());
        c.setDuracion(10);
        assertTrue(svc.update(c.getId(), c).isPresent());
        assertTrue(svc.delete(c.getId()));
    }

    @Test
    void list() { List<Registro> all = svc.findAll(); assertNotNull(all); }

    @Test
    void findByIdAndNotFound() {
        Registro r = new Registro();
        Registro c = svc.create(r);
        assertTrue(svc.findById(c.getId()).isPresent());
        assertFalse(svc.findById(9999).isPresent());
    }
}
