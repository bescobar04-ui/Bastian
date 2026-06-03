package modelo;

public class Usuario {
    private final String username;
    private final String clave;
    private final String nombre;

    public Usuario(String username, String clave, String nombre) {
        this.username = username;
        this.clave = clave;
        this.nombre = nombre;
    }

    public String getUsername() { return username; }
    public String getClave() { return clave; }
    public String getNombre() { return nombre; }
}