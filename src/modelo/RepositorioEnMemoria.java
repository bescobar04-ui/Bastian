package modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEnMemoria implements IRepositorioResultados {
    private final List<String> historial = new ArrayList<>();

    @Override
    public void guardarResultado(String resultado) {
        historial.add(resultado);
        System.out.println("[Memoria] Guardado temporal: " + resultado);
    }

    @Override
    public List<String> obtenerHistorial() {
        return historial;
    }
}