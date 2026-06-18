package Vista;

import Controlador.EstadisticasController;
import Controlador.RuletaController;

import javax.swing.*;
import java.awt.*;

public class VentanaEstadisticas extends JFrame {

    private final EstadisticasController estadisticasController;

    public VentanaEstadisticas(RuletaController ruletaController) {
        // CASO 6: Validación preventiva de dependencias requeridas
        if (ruletaController == null) {
            throw new IllegalArgumentException("El controlador de ruleta no puede ser nulo.");
        }

        this.estadisticasController = new EstadisticasController(ruletaController);

        setTitle("Estadísticas de la Ruleta");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));

        cargarEstadisticas();
    }

    private void cargarEstadisticas() {
        // CASO 6: Aislamiento del flujo lógico ante llamadas externas que pudiesen fallar
        try {
            String tipoMasJugado = estadisticasController.obtenerTipoMasJugado();

            add(new JLabel("Total de jugadas: " + estadisticasController.obtenerTotalJugadas()));
            add(new JLabel("Victorias: " + estadisticasController.obtenerVictorias()));
            add(new JLabel("Porcentaje de victorias: " + String.format("%.2f", estadisticasController.obtenerPorcentajeVictorias()) + "%"));
            add(new JLabel("Racha máxima: " + estadisticasController.obtenerRachaMaxima()));
            add(new JLabel("Tipo más jugado: " + (tipoMasJugado != null ? tipoMasJugado : "Sin jugadas")));

        } catch (Exception e) {
            // Red de seguridad local para evitar que problemas de cálculo corrompan la UI
            add(new JLabel("Error al procesar los datos de estadísticas de juego."));
            System.err.println("Fallo mitigado en carga de estadísticas: " + e.getMessage());
        }

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar);
    }

    public void mostrarVentana() {
        setVisible(true);
    }
}