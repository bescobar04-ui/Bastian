package modelo;

import java.util.List;

public class Estadistica {
    private final int totalJugadas;
    private int victorias = 0;
    private double porcentajeVictorias = 0.0;

    public Estadistica(List<Resultado> historial) {
        this.totalJugadas = historial.size();
        if (totalJugadas > 0) {
            for (Resultado r : historial) {
                if (r.getAcierto()) victorias++;
            }
            this.porcentajeVictorias = (this.victorias * 100.0) / this.totalJugadas;
        }
    }

    public int getTotalJugadas() { return totalJugadas; }
    public int getVictorias() { return victorias; }
    public double getPorcentajeVictorias() { return porcentajeVictorias; }
}