package modelo.Apuestas;

public class ApuestaRojo extends ApuestaBase {
    // Lista de números rojos estándar de la ruleta
    private final int[] rojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

    public ApuestaRojo() {
        super("ROJO");
    }

    @Override
    public boolean evaluar(int numero) {
        for (int r : rojos) {
            if (r == numero) return true;
        }
        return false;
    }
}