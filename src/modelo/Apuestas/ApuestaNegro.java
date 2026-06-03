package modelo.Apuestas;

public class ApuestaNegro extends ApuestaBase {
    private final int[] rojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

    public ApuestaNegro() {
        super("NEGRO");
    }

    @Override
    public boolean evaluar(int numero) {
        // Si no es cero y no está en la lista de rojos, es negro
        for (int r : rojos) {
            if (r == numero) return false;
        }
        return true;
    }
}