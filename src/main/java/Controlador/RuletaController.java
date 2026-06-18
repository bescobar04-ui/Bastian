package Controlador;

import Modelo.Ruleta;
import Modelo.Resultado;
import java.util.List;

public class RuletaController {

    private final Ruleta ruleta;
    private final SessionController sessionController;

    public RuletaController(Ruleta ruleta, SessionController sessionController) {
        this.ruleta = ruleta;
        this.sessionController = sessionController;
    }

    public int getSaldo() {
        // Caso 3: Contrato estricto - Lanza IllegalStateException si se invoca sin sesión activa
        if (!sessionController.hayUsuario()) {
            throw new IllegalStateException("Operación rechazada: No hay una sesión de usuario activa.");
        }
        return ruleta.getSaldo();
    }

    public void jugar(int monto, String casilla) {
        // Caso 3: Contrato estricto - Lanza IllegalStateException si se invoca sin sesión activa
        if (!sessionController.hayUsuario()) {
            throw new IllegalStateException("Operación rechazada: No se puede realizar una jugada sin sesión activa.");
        }
        ruleta.jugar(monto, casilla);
    }

    public List<Resultado> obtenerHistorial() {
        // Caso 3: Contrato estricto - Lanza IllegalStateException si se invoca sin sesión activa
        if (!sessionController.hayUsuario()) {
            throw new IllegalStateException("Operación rechazada: Acceso denegado al historial sin autenticación.");
        }
        return ruleta.obtenerHistorial(); // Asegúrate de que coincida con el método de tu modelo Ruleta
    }
}