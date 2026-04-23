package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private String username;
    private String clave;
    private String nombre;

    // Asociación 1 a muchos: Un usuario tiene muchos resultados [cite: 14, 84]
    private final List<Resultado> historial = new ArrayList<>();

    public Usuario(String username, String clave, String nombre) {
        this.username = username;
        this.clave = clave;
        this.nombre = nombre;
    }

    // Método para registrar cada jugada en el historial del usuario [cite: 15, 85]
    public void agregarResultado(Resultado r) {
        this.historial.add(r);
    }

    // Retorna el historial de forma protegida (unmodifiable) [cite: 91]
    public List<Resultado> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    // Getters necesarios para la Vista y el Controlador
    public String getUsername() { return username; }
    public String getClave() { return clave; }
    public String getNombre() { return nombre; }
}