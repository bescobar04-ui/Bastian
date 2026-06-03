package modelo;

import modelo.Apuestas.ApuestaBase;

public class Resultado {
    private final int numeroGanador;
    private final ApuestaBase apuesta;
    private final boolean acierto;
    private final int montoApuesta;

    public Resultado(int numeroGanador, ApuestaBase apuesta, boolean acierto, int montoApuesta) {
        this.numeroGanador = numeroGanador;
        this.apuesta = apuesta;
        this.acierto = acierto;
        this.montoApuesta = montoApuesta;
    }

    public int getNumeroGanador() { return numeroGanador; }
    public ApuestaBase getApuesta() { return apuesta; }
    public boolean getAcierto() { return acierto; }
    public int getMontoApuesta() { return montoApuesta; }
}