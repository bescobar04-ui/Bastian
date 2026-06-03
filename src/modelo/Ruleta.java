package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ruleta {
    private int saldo;
    private final List<Resultado> historialRondas = new ArrayList<>();
    private final Random rng = new Random();

    // Iteración 8: Dependemos de la abstracción
    private final IRepositorioResultados repositorio;

    public Ruleta(int saldoInicial, IRepositorioResultados repositorio) {
        this.saldo = saldoInicial;
        this.repositorio = repositorio;
    }

    public int generarNumero() {
        return rng.nextInt(37); // Genera de 0 a 36
    }

    public void registrarRonda(int numero, modelo.Apuestas.ApuestaBase apuesta, int monto) {
        boolean acierto = (numero != 0) && apuesta.evaluar(numero);

        if (acierto) {
            saldo += monto; // Simplificado: gana el monto apostado
        } else {
            saldo -= monto;
        }

        Resultado r = new Resultado(numero, apuesta, acierto, monto);
        historialRondas.add(r);

        // Iteración 8: Formateamos como texto y guardamos de forma externa
        String lineaHistorial = "Numero: " + numero + " | Apuesta: " + apuesta.getEtiqueta() + " | Monto: $" + monto + " | Acierto: " + (acierto ? "SI" : "NO");
        repositorio.guardarResultado(lineaHistorial);
    }

    public int getSaldo() { return saldo; }
    public List<Resultado> getHistorialRondas() { return historialRondas; }
}