package com.manejotiempo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    private String nombre;
    private String email;
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Meta> metas;

    @OneToMany(mappedBy = "usuario")
    private List<Tarea> tareas;

    @OneToMany(mappedBy = "usuario")
    private List<ClaseProgramada> clasesProgramadas;

    @OneToMany(mappedBy = "usuario")
    private List<Recordatorio> recordatorios;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Meta> getMetas() {
        return metas;
    }

    public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public List<ClaseProgramada> getClasesProgramadas() {
        return clasesProgramadas;
    }

    public void setClasesProgramadas(List<ClaseProgramada> clasesProgramadas) {
        this.clasesProgramadas = clasesProgramadas;
    }

    public List<Recordatorio> getRecordatorios() {
        return recordatorios;
    }

    public void setRecordatorios(List<Recordatorio> recordatorios) {
        this.recordatorios = recordatorios;
    }
}

