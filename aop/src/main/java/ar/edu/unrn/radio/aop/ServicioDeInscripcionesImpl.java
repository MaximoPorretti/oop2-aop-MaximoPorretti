package ar.edu.unrn.radio.aop;


import ar.edu.unrn.radio.aop.model3.Concurso;
import ar.edu.unrn.radio.aop.model3.Participante;
import ar.edu.unrn.radio.aop.model3.RepositorioConcursos;
import ar.edu.unrn.radio.aop.model3.ServicioDeInscripciones;

import java.util.Objects;

/**
 * Implementación concreta de ServicioDeInscripciones.
 * Encapsula cualquier validación y el uso de la capa de repositorio.
 */
public class ServicioDeInscripcionesImpl implements ServicioDeInscripciones {

    private final RepositorioConcursos repositorio;

    public ServicioDeInscripcionesImpl(RepositorioConcursos repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    @Log
    public void saveInscription(Participante participante, Concurso concurso) {

        // Validaciones mínimas
        Objects.requireNonNull(participante, "participante nulo");
        Objects.requireNonNull(concurso, "concurso nulo");

        if (!concurso.estaVigente(java.time.LocalDate.now())) {
            throw new IllegalStateException("El concurso no está vigente");
        }

        // Persistencia real — reemplazá con tu lógica (JDBC, archivo, etc.)
        // Por simplicidad, sólo imprimimos en consola aquí.
        System.out.printf("Participante %s inscrito en «%s»%n",
                participante.getNombre(), concurso.getNombre());
    }
}
