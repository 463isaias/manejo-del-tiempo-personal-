package com.manejotiempo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.manejotiempo.model.Categoria;

@Service
public class CategoriaService {
    private final Map<Integer, Categoria> store = new ConcurrentHashMap<>();
    private final AtomicInteger idGen = new AtomicInteger(1);

    public List<Categoria> findAll() { return new ArrayList<>(store.values()); }
    public Optional<Categoria> findById(Integer id) { return Optional.ofNullable(store.get(id)); }
    public Categoria create(Categoria c) {
        int id = idGen.getAndIncrement();
        c.setCategoriaId(id);
        store.put(id, c);
        return c;
    }
    public Optional<Categoria> update(Integer id, Categoria c) {
        if (!store.containsKey(id)) return Optional.empty();
        c.setCategoriaId(id);
        store.put(id, c);
        return Optional.of(c);
    }
    public boolean delete(Integer id) { return store.remove(id) != null; }
}
