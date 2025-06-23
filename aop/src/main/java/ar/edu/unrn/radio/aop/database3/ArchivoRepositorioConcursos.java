package ar.edu.unrn.radio.aop.database3;



import ar.edu.unrn.radio.aop.model3.Concurso;
import ar.edu.unrn.radio.aop.model3.RepositorioConcursos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArchivoRepositorioConcursos implements RepositorioConcursos {
    private final String rutaArchivo;

    public ArchivoRepositorioConcursos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public List<Concurso> concursosVigentes() {
        List<Concurso> vigentes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(", ");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                LocalDate inicio = LocalDate.parse(datos[2]);
                LocalDate fin = LocalDate.parse(datos[3]);
                Concurso concurso = new Concurso(id, nombre, inicio, fin);
                if (concurso.estaVigente(LocalDate.now())) {
                    vigentes.add(concurso);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo concursos", e);
        }
        return vigentes;
    }
}
