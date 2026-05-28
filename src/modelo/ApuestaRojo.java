package modelo;

public class ApuestaRojo extends ApuestaBase {
    public ApuestaRojo(int monto, String etiqueta) {
        super(monto, etiqueta);
    }

    @Override
    public boolean evaluarAcierto(int numeroGanador) {
        int[] rojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        for (int r : rojos) {
            if (r == numeroGanador) return true;
        }
        return false;
    }
}