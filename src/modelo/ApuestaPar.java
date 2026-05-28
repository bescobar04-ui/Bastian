package modelo;

public class ApuestaPar extends ApuestaBase {
    public ApuestaPar(int monto, String etiqueta) {
        super(monto, etiqueta);
    }

    @Override
    public boolean evaluarAcierto(int numeroGanador) {
        return numeroGanador != 0 && numeroGanador % 2 == 0;
    }
}