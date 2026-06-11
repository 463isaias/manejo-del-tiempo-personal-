package com.manejotiempo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.manejotiempo.model.Tarea;

@Service
public class TareaService {
    private final Map<Integer, Tarea> store = new ConcurrentHashMap<>();
    private final AtomicInteger idGen = new AtomicInteger(1);

    public List<Tarea> findAll() { return new ArrayList<>(store.values()); }
    public Optional<Tarea> findById(Integer id) { return Optional.ofNullable(store.get(id)); }
    public Tarea create(Tarea t) {
        int id = idGen.getAndIncrement();
        t.setTareaId(id);
        store.put(id, t);
        return t;
    }
    public Optional<Tarea> update(Integer id, Tarea t) {
        if (!store.containsKey(id)) return Optional.empty();
        t.setTareaId(id);
        store.put(id, t);
        return Optional.of(t);
    }
    public boolean delete(Integer id) { return store.remove(id) != null; }
}
