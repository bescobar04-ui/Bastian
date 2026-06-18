package Controlador;

import Modelo.Usuario;

public class SessionController {

    private Usuario usuarioActual;

    public void registrarUsuario(String username, String password, String nombre) {
        // Caso 5: Validación defensiva con IF por capas en el controlador
        if (username == null || username.isBlank() ||
                password == null || password.isBlank() ||
                nombre == null || nombre.isBlank()) {
            return; // Flujo normal controlado preventivamente
        }

        // Caso 1: Regla del dominio - Lanzar IllegalStateException si ya está registrado
        if (usuarioActual != null && usuarioActual.getUsername().equals(username)) {
            throw new IllegalStateException("El usuario ya se encuentra registrado.");
        }

        this.usuarioActual = new Usuario(username, password, nombre);
    }

    public boolean iniciarSesion(String username, String password) {
        // Caso 5: Validación básica previa
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return false;
        }

        // Caso 1: Regla del dominio - Lanzar exclusivamente IllegalStateException
        if (usuarioActual == null || !usuarioActual.validarCredenciales(username, password)) {
            throw new IllegalStateException("Credenciales incorrectas.");
        }
        return true;
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public Usuario getUsuarioActual() {
        // Caso 3: Contrato estricto - Lanza excepción si se invoca sin sesión activa
        if (usuarioActual == null) {
            throw new IllegalStateException("Operación rechazada: No se invocó un usuario autenticado.");
        }
        return usuarioActual;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}