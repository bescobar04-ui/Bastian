package modelo;

import java.util.List;

public interface IRepositorioResultados {
    void guardarResultado(String resultado);
    List<String> obtenerHistorial();
}