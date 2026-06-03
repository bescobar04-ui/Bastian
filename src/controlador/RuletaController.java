package controlador;

import modelo.Ruleta;
import modelo.Apuestas.*;
import java.util.List;

public class RuletaController {
    private final Ruleta ruleta;

    public RuletaController(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    public int getSaldo() {
        return ruleta.getSaldo();
    }

    public void jugar(String tipoApuesta, int monto) {
        ApuestaBase apuesta;
        switch (tipoApuesta.toUpperCase()) {
            case "ROJO": apuesta = new ApuestaRojo(); break;
            case "NEGRO": apuesta = new ApuestaNegro(); break;
            case "PAR": apuesta = new ApuestaPar(); break;
            case "IMPAR": apuesta = new ApuestaImpar(); break;
            default: throw new IllegalArgumentException("Tipo de apuesta inválido");
        }

        int numeroGanador = ruleta.generarNumero();
        ruleta.registrarRonda(numeroGanador, apuesta, monto);
    }
}