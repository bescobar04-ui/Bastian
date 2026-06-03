package vista;

import controlador.ResultadoController;
import modelo.Estadistica;
import modelo.Resultado;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaEstadisticas extends JFrame {
    private final ResultadoController resultadoController;
    private final JTextArea txtEstadisticas = new JTextArea(10, 40);

    public VentanaEstadisticas(ResultadoController resultadoController) {
        this.resultadoController = resultadoController;
        setTitle("Estadísticas de Juego - Iteración 7");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtEstadisticas.setEditable(false);
        add(new JScrollPane(txtEstadisticas), BorderLayout.CENTER);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> cargarDatos());
        add(btnActualizar, BorderLayout.SOUTH);

        cargarDatos();
    }

    private void cargarDatos() {
        List<Resultado> historial = resultadoController.getHistorial();
        Estadistica est = new Estadistica(historial);

        txtEstadisticas.setText("");
        txtEstadisticas.append("=== ESTADÍSTICAS GENERALES ===\n");
        txtEstadisticas.append("Total de jugadas: " + est.getTotalJugadas() + "\n");
        txtEstadisticas.append("Victorias totales: " + est.getVictorias() + "\n");
        txtEstadisticas.append(String.format("Porcentaje de Éxito: %.2f%%\n", est.getPorcentajeVictorias()));
    }
}