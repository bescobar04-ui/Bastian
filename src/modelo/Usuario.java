package modelo;

public class Usuario {
    private String username;
    private String password;
    private String nombre;

    // Constructor para invitados
    public Usuario() {
        this.username = "invitado";
        this.password = "";
        this.nombre = "Invitado";
    }

    // Constructor para registro de nuevos usuarios
    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    // --- AQUÍ VA EL MÉTODO QUE PREGUNTAS ---
    public boolean validarCredenciales(String u, String p) {
        // Compara el username y password guardados con los que vienen por parámetro
        return this.username.equals(u) && this.password.equals(p);
    }

    // Getters necesarios para que las ventanas muestren la info
    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }
}