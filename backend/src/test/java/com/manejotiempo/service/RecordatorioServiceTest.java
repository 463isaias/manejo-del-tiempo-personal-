package com.manejotiempo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.manejotiempo.model.Recordatorio;

class RecordatorioServiceTest {
    private RecordatorioService svc;

    @BeforeEach
    void setUp() { svc = new RecordatorioService(); }

    @Test
    void crudAndList() {
        Recordatorio r = new Recordatorio();
        Recordatorio c = svc.create(r);
        assertNotNull(c.getId());
        c.setMensaje("hi");
        assertTrue(svc.update(c.getId(), c).isPresent());
        assertTrue(svc.delete(c.getId()));
        List<Recordatorio> all = svc.findAll();
        assertNotNull(all);
    }

    @Test
    void findByIdAndMissing() {
        Recordatorio r = new Recordatorio();
        Recordatorio c = svc.create(r);
        assertTrue(svc.findById(c.getId()).isPresent());
        assertFalse(svc.findById(9999).isPresent());
    }
}
