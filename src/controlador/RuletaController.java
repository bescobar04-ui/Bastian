package controlador; // Fíjate que el paquete sea controlador

import modelo.*;

public class RuletaController {
    private final Ruleta ruleta;

    public RuletaController(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    public Resultado jugar(TipoApuesta tipoApuesta, int monto, Usuario usuarioActual) {
        int numero = ruleta.generarNumero();
        boolean acierto = ruleta.evaluarResultado(numero, tipoApuesta);
        Resultado nuevoResultado = new Resultado(numero, tipoApuesta, monto, acierto);
        usuarioActual.agregarResultado(nuevoResultado); // Asociación 1 a muchos
        return nuevoResultado;
    }

    public int getSaldo() { return ruleta.getSaldo(); }
}