package controlador;

import modelo.Usuario;

public class SessionController {
    private Usuario usuarioActual;

    public void registrarUsuario(String username, String password, String nombre) {
        this.usuarioActual = new Usuario(username, password, nombre);
    }

    public boolean iniciarSesion(String username, String password) {
        if (usuarioActual == null) return false;
        return usuarioActual.getUsername().equals(username) && usuarioActual.getClave().equals(password);
    }

    public Usuario getUsuarioActual() { return usuarioActual; }
    public boolean hayUsuario() { return usuarioActual != null; }
}