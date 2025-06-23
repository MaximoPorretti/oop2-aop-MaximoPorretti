package ar.edu.unrn.radio.aop.main3;

import ar.edu.unrn.radio.aop.database3.ArchivoRepositorioConcursos;
import ar.edu.unrn.radio.aop.database3.ArchivoServicioInscripciones;
import ar.edu.unrn.radio.aop.model3.RepositorioConcursos;
import ar.edu.unrn.radio.aop.model3.ServicioDeInscripciones;
import ar.edu.unrn.radio.aop.ui3.RadioCompetition;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación.
 * - Crea/Inicializa los archivos de datos si no existen.
 * - Instancia las implementaciones que leen/escriben en disco.
 * - Lanza la UI Swing en el EDT.
 */
public class Main {

    public static void main(String[] args) {

        /* ----------  Inicializar archivos de datos ---------- */
        new SetUpDatabase("concursos.txt", "inscriptos.txt").inicializar();

        /* ----------  Levantar la interfaz gráfica ---------- */
        SwingUtilities.invokeLater(() -> {
            RepositorioConcursos repo =
                    new ArchivoRepositorioConcursos("concursos.txt");

            ServicioDeInscripciones inscripciones =
                    new ArchivoServicioInscripciones("inscriptos.txt");

            new RadioCompetition(repo, inscripciones);
        });
    }
}

