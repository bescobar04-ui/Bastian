package modelo.Apuestas;

public class ApuestaPar extends ApuestaBase {
    public ApuestaPar() {
        super("PAR");
    }

    @Override
    public boolean evaluar(int numero) {
        return numero % 2 == 0;
    }
}