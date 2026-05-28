package controlador;

import modelo.*;

public class RuletaController {
    private final Ruleta ruleta;

    public RuletaController(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    // Ahora recibe cualquier hija de ApuestaBase (Par, Rojo, etc.)
    public Resultado jugar(ApuestaBase apuestaRealizada, Usuario usuarioActual) {
        int numero = ruleta.generarNumero();

        // POLIMORFISMO: La apuesta sabe cómo evaluarse sola
        boolean acierto = apuestaRealizada.evaluarAcierto(numero);

        Resultado nuevoResultado = new Resultado(numero, apuestaRealizada, acierto);

        usuarioActual.agregarResultado(nuevoResultado);

        // Lógica de saldo: si gana, se le paga el doble del monto apostado
        if (acierto) {
            usuarioActual.sumarSaldo(apuestaRealizada.getMontoApostado() * 2);
        }

        return nuevoResultado;
    }

    public int getSaldo(Usuario usuario) {
        return usuario.getSaldo();
    }
}