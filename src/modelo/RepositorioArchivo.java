package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {
    private final String NOMBRE_ARCHIVO = "historial_ruleta.csv";

    @Override
    public void guardarResultado(String resultado) {
        try (FileWriter fw = new FileWriter(NOMBRE_ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(resultado);
            System.out.println("[Archivo CSV] Guardado permanente: " + resultado);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
        }
    }

    @Override
    public List<String> obtenerHistorial() {
        List<String> historial = new ArrayList<>();
        File archivo = new File(NOMBRE_ARCHIVO);
        if (!archivo.exists()) return historial;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                historial.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return historial;
    }
}