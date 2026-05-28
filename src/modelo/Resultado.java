package modelo;

public class Resultado {
    private int numero;
    private ApuestaBase apuesta; // Cambio clave: de TipoApuesta a ApuestaBase
    private boolean acierto;

    public Resultado(int numero, ApuestaBase apuesta, boolean acierto) {
        this.numero = numero;
        this.apuesta = apuesta;
        this.acierto = acierto;
    }

    public int getNumero() {
        return numero;
    }

    public ApuestaBase getApuesta() {
        return apuesta;
    }

    public boolean isAcierto() {
        return acierto;
    }

    // Método de conveniencia para obtener el monto de la apuesta
    public int getMonto() {
        return apuesta.getMontoApostado();
    }
}