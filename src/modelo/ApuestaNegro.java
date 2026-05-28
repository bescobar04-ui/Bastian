package modelo;

public class ApuestaNegro extends ApuestaBase {
    public ApuestaNegro(int monto, String etiqueta) {
        super(monto, etiqueta);
    }

    @Override
    public boolean evaluarAcierto(int numeroGanador) {
        // Los negros son los que no son 0 y no son rojos
        int[] rojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        if (numeroGanador == 0) return false;
        for (int r : rojos) {
            if (r == numeroGanador) return false;
        }
        return true;
    }
}