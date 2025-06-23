package ar.edu.unrn.radio.aop.main3;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

public class SetUpDatabase {

    private final String rutaConcursos;
    private final String rutaInscriptos;

    public SetUpDatabase(String rutaConcursos, String rutaInscriptos) {
        this.rutaConcursos = rutaConcursos;
        this.rutaInscriptos = rutaInscriptos;
    }


    public void inicializar() {
        crearArchivoConcursos();
        crearArchivoInscriptos();
    }

    private void crearArchivoConcursos() {
        LocalDate hoy = LocalDate.now();


        String c1Inicio = hoy.minusMonths(1).toString();
        String c1Fin    = hoy.plusMonths(2).toString();


        String c2Inicio = hoy.minusDays(10).toString();
        String c2Fin    = hoy.plusDays(20).toString();


        String c3Inicio = hoy.minusMonths(3).toString();
        String c3Fin    = hoy.minusDays(1).toString();

        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaConcursos))) {
            pw.printf("1, Concurso A, %s, %s%n", c1Inicio, c1Fin);
            pw.printf("2, Concurso B, %s, %s%n", c2Inicio, c2Fin);
            pw.printf("3, Concurso C, %s, %s%n", c3Inicio, c3Fin);
        } catch (Exception e) {
            throw new RuntimeException("Error creando archivo de concursos", e);
        }
    }

    private void crearArchivoInscriptos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaInscriptos))) {
            pw.println("Young, Angus, 4444-898789, angus@acdc.com, 1");
        } catch (Exception e) {
            throw new RuntimeException("Error creando archivo de inscriptos", e);
        }
    }
}
