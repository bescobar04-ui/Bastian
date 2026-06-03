package modelo.Apuestas;

public abstract class ApuestaBase {
    private final String etiqueta;

    public ApuestaBase(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() { return etiqueta; }

    // Método abstracto que cada tipo de apuesta resolverá
    public abstract boolean evaluar(int numero);
}