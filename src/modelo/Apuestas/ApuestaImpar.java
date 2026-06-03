package modelo.Apuestas;

public class ApuestaImpar extends ApuestaBase {
    public ApuestaImpar() {
        super("IMPAR");
    }

    @Override
    public boolean evaluar(int numero) {
        return numero % 2 != 0;
    }
}