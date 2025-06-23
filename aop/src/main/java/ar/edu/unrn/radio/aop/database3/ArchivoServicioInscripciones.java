package ar.edu.unrn.radio.aop.database3;

import ar.edu.unrn.radio.aop.model3.Concurso;
import ar.edu.unrn.radio.aop.model3.Participante;
import ar.edu.unrn.radio.aop.model3.ServicioDeInscripciones;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ArchivoServicioInscripciones implements ServicioDeInscripciones {
    private final String rutaArchivo;

    public ArchivoServicioInscripciones(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void inscribir(Participante p, Concurso c) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            pw.printf("%s, %s, %s, %s, %d%n",
                    p.getApellido(), p.getNombre(), p.getTelefono(), p.getEmail(), c.getId());
        } catch (Exception e) {
            throw new RuntimeException("Error escribiendo inscripción", e);
        }
    }
}
