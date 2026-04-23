package controlador;

// IMPORTANTE: Este import soluciona el error "cannot find symbol class Usuario"
import modelo.Usuario;

public class SessionController {

    private Usuario usuarioActual;

    /**
     * Registra un nuevo usuario y lo mantiene como el usuario de la sesión actual.
     * Se usa en la VentanaRegistro.
     */
    public void registrarUsuario(String username, String password, String nombre) {
        if (username == null || username.isBlank() ||
                password == null || password.isBlank() ||
                nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }

        // Crea la instancia usando el constructor de 3 parámetros
        this.usuarioActual = new Usuario(username, password, nombre);
    }

    /**
     * Valida las credenciales para iniciar sesión.
     * Se usa en la VentanaLogin.
     */
    public boolean iniciarSesion(String username, String password) {
        if (usuarioActual == null) {
            return false;
        }

        // Compara las credenciales guardadas en el objeto usuarioActual
        return usuarioActual.getUsername().equals(username) &&
                usuarioActual.getClave().equals(password);
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}