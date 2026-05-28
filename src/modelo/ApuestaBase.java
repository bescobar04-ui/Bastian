package modelo;

public abstract class ApuestaBase {
	protected int montoApostado;
	protected String etiqueta;

	public ApuestaBase(int monto, String etiqueta) {
		this.montoApostado = monto;
		this.etiqueta = etiqueta;
	}

	// Contrato: toda apuesta debe saber evaluarse
	public abstract boolean evaluarAcierto(int numeroGanador);

	public int getMontoApostado() {
		return montoApostado;
	}

	public String getEtiqueta() {
		return etiqueta;
	}
}