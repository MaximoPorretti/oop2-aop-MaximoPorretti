package ar.edu.unrn.radio.aop.model3;

import java.time.LocalDate;

public class Concurso {
    private int id;
    private String nombre;
    private LocalDate inicio;
    private LocalDate fin;

    public Concurso(int id, String nombre, LocalDate inicio, LocalDate fin) {
        this.id = id;
        this.nombre = nombre;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public boolean estaVigente(LocalDate hoy) {
        return !hoy.isBefore(inicio) && !hoy.isAfter(fin);
    }
    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}
