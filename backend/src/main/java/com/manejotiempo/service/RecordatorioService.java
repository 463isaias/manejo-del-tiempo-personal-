package com.manejotiempo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.manejotiempo.model.Recordatorio;

@Service
public class RecordatorioService {
    private final Map<Integer, Recordatorio> store = new ConcurrentHashMap<>();
    private final AtomicInteger idGen = new AtomicInteger(1);

    public List<Recordatorio> findAll() { return new ArrayList<>(store.values()); }
    public Optional<Recordatorio> findById(Integer id) { return Optional.ofNullable(store.get(id)); }
    public Recordatorio create(Recordatorio r) {
        int id = idGen.getAndIncrement();
        r.setId(id);
        store.put(id, r);
        return r;
    }
    public Optional<Recordatorio> update(Integer id, Recordatorio r) {
        if (!store.containsKey(id)) return Optional.empty();
        r.setId(id);
        store.put(id, r);
        return Optional.of(r);
    }
    public boolean delete(Integer id) { return store.remove(id) != null; }
}
