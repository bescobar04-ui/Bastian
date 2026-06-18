package Modelo.Persistencia;

import Modelo.Resultado;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {

    private final Path rutaArchivo;

    public RepositorioArchivo() {
        this("historial_ruleta.csv");
    }

    public RepositorioArchivo(String nombreArchivo) {
        this.rutaArchivo = Path.of(nombreArchivo);
    }

    @Override
    public void guardar(Resultado resultado) {
        // Caso 4: Control preventivo de escritura con estructuras IF
        if (Files.exists(rutaArchivo) && !Files.isWritable(rutaArchivo)) {
            System.out.println("Error de control: El archivo no cuenta con permisos de escritura.");
            return;
        }

        try {
            boolean archivoNuevo = !Files.exists(rutaArchivo) || Files.size(rutaArchivo) == 0;

            try (BufferedWriter writer = Files.newBufferedWriter(
                    rutaArchivo,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            )) {
                if (archivoNuevo) {
                    writer.write("numero,color,tipoApuesta,monto,acierto");
                    writer.newLine();
                }

                writer.write(resultado.getNumero() + ","
                        + resultado.getColor() + ","
                        + resultado.getTipoApuesta() + ","
                        + resultado.getMonto() + ","
                        + resultado.isAcierto());
                writer.newLine();
            }
        } catch (IOException e) {
            // Caso 4: Excepción legítima capturada ante fallos del sistema de archivos externo
            System.out.println("No se pudo guardar el resultado en archivo: " + e.getMessage());
        }
    }

    @Override
    public List<Resultado> obtenerTodos() {
        List<Resultado> resultados = new ArrayList<>();

        // Caso 4: Verificación con IF de ruta, existencia y lectura del flujo normal
        if (!Files.exists(rutaArchivo) || !Files.isRegularFile(rutaArchivo) || !Files.isReadable(rutaArchivo)) {
            return resultados;
        }

        try {
            // Operación de lectura del sistema de archivos (puede lanzar IOException)
            List<String> lineas = Files.readAllLines(rutaArchivo);

            for (int i = 1; i < lineas.size(); i++) {
                String linea = lineas.get(i).trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(",");

                // Caso 7: Check ligero de cantidad de campos requeridos
                if (partes.length != 5) {
                    continue;
                }

                // Caso 7: Bloque try-catch local para capturar NumberFormatException por separado
                try {
                    int numero = Integer.parseInt(partes[0].trim());
                    String color = partes[1].trim();
                    String tipoApuesta = partes[2].trim();
                    int monto = Integer.parseInt(partes[3].trim());
                    boolean acierto = Boolean.parseBoolean(partes[4].trim());

                    // Caso 7: Control con IF para verificar rangos plausibles
                    if (numero < 0 || numero > 36 || monto < 0) {
                        continue; // Descarte por inconsistencia de datos
                    }

                    resultados.add(new Resultado(numero, tipoApuesta, monto, acierto, color));

                } catch (NumberFormatException e) {
                    // Caso 7: Captura local para descartar solo la línea corrupta y continuar con el ciclo
                    System.out.println("Omitiendo línea corrupta en el historial: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            // Caso 4: Captura de error crítico del sistema de archivos
            System.out.println("No se pudo leer el historial desde archivo: " + e.getMessage());
        }

        return resultados;
    }

    @Override
    public void limpiar() {
        try (BufferedWriter writer = Files.newBufferedWriter(
                rutaArchivo,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        )) {
            writer.write("numero,color,tipoApuesta,monto,acierto");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("No se pudo limpiar el historial: " + e.getMessage());
        }
    }
}