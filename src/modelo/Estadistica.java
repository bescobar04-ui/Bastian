package modelo;

import java.util.List;

public class Estadistica {

	private int totalJugadas;
	private int victorias;
	private double porcentajeVictorias;
	private int rachaMaxima;
	private TipoApuesta tipoMasJugado;

	public void calcularEstadisticas(List<Resultado> historial) {
		if (historial == null || historial.isEmpty()) {
			this.totalJugadas = 0;
			this.victorias = 0;
			this.porcentajeVictorias = 0;
			this.rachaMaxima = 0;
			return;
		}

		this.totalJugadas = historial.size();
		this.victorias = 0;
		int rachaActual = 0;
		this.rachaMaxima = 0;

		for (Resultado r : historial) {
			if (r.isAcierto()) {
				this.victorias++;
				rachaActual++;
				if (rachaActual > this.rachaMaxima) {
					this.rachaMaxima = rachaActual;
				}
			} else {
				rachaActual = 0;
			}
		}
		this.porcentajeVictorias = ((double) this.victorias / this.totalJugadas) * 100;
	}

	// Puedes borrar   el método operation() si no lo vas a usar
}