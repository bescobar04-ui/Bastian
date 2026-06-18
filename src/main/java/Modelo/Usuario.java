package Modelo;

public class Usuario {

    private String username;
    private String password;
    private String nombre;

    public Usuario(String username, String password, String nombre) {
        // Caso 5: Al estar completamente filtrado por las capas superiores,
        // aquí no quedan escenarios excepcionales, por lo que la asignación es directa.
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    public boolean validarCredenciales(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }
}