package ar.edu.unrn.radio.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
public class LoggingAspect {

    private static final Path LOG_FILE =
            Paths.get("logs", "invocations.log");      // ← podés cambiar la ubicación
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /** Point-cut: todo método anotado con @Log */
    @Pointcut("@annotation(ar.edu.unrn.radio.aop.Log)")
    private void metodoLogueado() {}

    /** Advice: se ejecuta justo antes del método anotado */
    @Before("metodoLogueado()")
    public void registrarInvocacion(JoinPoint jp) {

        // Nombre del método
        String metodo = jp.getSignature().getName();

        // Parámetros convertidos a texto y unidos por "|"
        Object[] args = jp.getArgs();
        String valores;
        if (args == null || args.length == 0) {
            valores = "sin parametros";
        } else {
            valores = Arrays.stream(args)
                    .map(String::valueOf)
                    .reduce((a, b) -> a + "|" + b)
                    .orElse("sin parametros");
        }

        // Fecha y hora
        String fecha = LocalDateTime.now().format(FMT);

        // Línea CSV
        String linea = String.format("\"%s\", \"%s\", \"%s\"%n",
                metodo, valores, fecha);

        // Persistir
        try {
            System.out.println("Intentando escribir log en: " + LOG_FILE.toAbsolutePath());
            Files.createDirectories(LOG_FILE.getParent());
            Files.writeString(LOG_FILE, linea,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.err.println("[LogAspect] No se pudo escribir: " + e);
            e.printStackTrace();
        }
    }
}


