import java.util.Random;
import java.util.Scanner;

public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    public static void main(String[] args) { menu(); }

    public static void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);
        } while (opcion != 3);
    }

    public static void mostrarMenu() {
        System.out.println("\n1. Iniciar Ronda\n2. Ver Estadísticas\n3. Salir");
        System.out.print("Seleccione: ");
    }

    public static int leerOpcion(Scanner in) { return in.nextInt(); }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        if (opcion == 1) iniciarRonda(in);
        else if (opcion == 2) mostrarEstadisticas();
        else if (opcion == 3) System.out.println("Saliendo...");
    }

    public static void iniciarRonda(Scanner in) {
        char tipo = leerTipoApuesta(in);
        System.out.print("Monto: ");
        int monto = in.nextInt();
        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);
        registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipo, monto, acierto);
    }

    public static char leerTipoApuesta(Scanner in) {
        System.out.print("Tipo (R/N/P/I): ");
        return Character.toUpperCase(in.next().charAt(0));
    }

    public static int girarRuleta() { return rng.nextInt(37); }

    public static boolean evaluarResultado(int numero, char tipo) {
        if (numero == 0) return false;
        if (tipo == 'P') return numero % 2 == 0;
        if (tipo == 'I') return numero % 2 != 0;
        boolean esR = esRojo(numero);
        return (tipo == 'R') ? esR : !esR;
    }

    public static boolean esRojo(int n) {
        for (int r : numerosRojos) if (r == n) return true;
        return false;
    }

    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    public static void mostrarResultado(int n, char t, int m, boolean a) {
        System.out.println("Número: " + n + (a ? " ¡GANASTE!" : " Perdiste"));
    }

    public static void mostrarEstadisticas() {
        int tAp = 0, tAc = 0, neto = 0;
        for (int i = 0; i < historialSize; i++) {
            tAp += historialApuestas[i];
            tAc += historialAciertos[i] ? 1 : 0;
            neto += historialAciertos[i] ? historialApuestas[i] : -historialApuestas[i];
        }
        System.out.println("Rondas: " + historialSize + " | Total: $" + tAp);
        System.out.println("% Acierto: " + (historialSize == 0 ? 0 : (tAc * 100.0 / historialSize)) + "%");
        System.out.println("Ganancia/Pérdida Neta: $" + neto);
    }
}