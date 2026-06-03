package vista;

import controlador.RuletaController;
import controlador.ResultadoController;
import javax.swing.*;
import java.awt.*;

public class VentanaMenu extends JFrame {
    private final RuletaController ruletaController;
    private final ResultadoController resultadoController;

    private final JLabel lblSaldo = new JLabel();
    private final JTextField txtMonto = new JTextField("1000", 10);
    private final JComboBox<String> cbTipoApuesta = new JComboBox<>(new String[]{"ROJO", "NEGRO", "PAR", "IMPAR"});
    private final JButton btnGirar = new JButton("🎰 Girar Ruleta");
    private final JButton btnEstadisticas = new JButton("📊 Ver Estadísticas");

    // Recibe los controladores para interactuar con el motor real
    public VentanaMenu(RuletaController ruletaController, ResultadoController resultadoController) {
        this.ruletaController = ruletaController;
        this.resultadoController = resultadoController;

        setTitle("Casino - Tablero de Ruleta");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        // Mostrar saldo actualizado
        actualizarSaldo();

        JPanel p1 = new JPanel(); p1.add(new JLabel("Saldo Actual: ")); p1.add(lblSaldo);
        JPanel p2 = new JPanel(); p2.add(new JLabel("Monto a Apostar: ")); p2.add(txtMonto);
        JPanel p3 = new JPanel(); p3.add(new JLabel("Tipo Apuesta: ")); p3.add(cbTipoApuesta);
        JPanel p4 = new JPanel(); p4.add(btnGirar);
        JPanel p5 = new JPanel(); p5.add(btnEstadisticas);

        add(p1); add(p2); add(p3); add(p4); add(p5);

        // EVENTO 1: Girar y simular juego
        btnGirar.addActionListener(e -> {
            try {
                int monto = Integer.parseInt(txtMonto.getText());
                String tipo = (String) cbTipoApuesta.getSelectedItem();

                if (monto > ruletaController.getSaldo()) {
                    JOptionPane.showMessageDialog(this, "No tienes saldo suficiente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Ejecuta la jugada en el controlador
                ruletaController.jugar(tipo, monto);

                // Muestra el resultado (en tu modelo real puedes guardar el número, acá simulamos la alerta rápida)
                JOptionPane.showMessageDialog(this, "¡Ruleta girada con éxito!\nRevisa la consola o estadísticas para ver el historial.");

                actualizarSaldo();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingresa un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // EVENTO 2: Abrir Estadísticas (Iteración 7)
        btnEstadisticas.addActionListener(e -> {
            VentanaEstadisticas vEst = new VentanaEstadisticas(resultadoController);
            vEst.setVisible(true);
        });
    }

    private void actualizarSaldo() {
        lblSaldo.setText("$" + ruletaController.getSaldo());
    }
}