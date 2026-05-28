package modelo;

public class ApuestaImpar extends ApuestaBase {
    public ApuestaImpar(int monto, String etiqueta) {
        super(monto, etiqueta);
    }

    @Override
    public boolean evaluarAcierto(int numeroGanador) {
        return numeroGanador != 0 && numeroGanador % 2 != 0;
    }
}