package vista;

import controlador.RuletaController;
import controlador.SessionController;
import modelo.Resultado;
import modelo.TipoApuesta;
import modelo.Usuario; // ESTA LÍNEA ES LA QUE FALTA

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

    private final SessionController sessionController;
    private final RuletaController ruletaController;

    private JComboBox<TipoApuesta> cboTipo;
    private JTextField txtMonto;
    private JLabel lblSaldo;
    private JButton btnJugar;
    private JButton btnVolver;

    public VentanaJuego(SessionController sessionController, RuletaController ruletaController) {
        this.sessionController = sessionController;
        this.ruletaController = ruletaController;
        inicializar();
    }

    private void inicializar() {
        setTitle("Ruleta - Jugador: " + sessionController.getUsuarioActual().getNombre());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Tipo de apuesta:"));
        cboTipo = new JComboBox<>(TipoApuesta.values());
        add(cboTipo);

        add(new JLabel("Monto a apostar:"));
        txtMonto = new JTextField();
        add(txtMonto);

        add(new JLabel("Tu Saldo:"));
        lblSaldo = new JLabel("$" + ruletaController.getSaldo());
        add(lblSaldo);

        btnJugar = new JButton("¡Girar!");
        btnVolver = new JButton("Volver");

        add(btnJugar); add(btnVolver);

        btnJugar.addActionListener(e -> jugar());
        btnVolver.addActionListener(e -> volverMenu());
    }

    private void jugar() {
        try {
            TipoApuesta tipo = (TipoApuesta) cboTipo.getSelectedItem();
            int monto = Integer.parseInt(txtMonto.getText());

            // Aquí es donde te daba el error
            Usuario usuarioQueJuega = sessionController.getUsuarioActual();
            Resultado res = ruletaController.jugar(tipo, monto, usuarioQueJuega);

            JOptionPane.showMessageDialog(this, "Salió el: " + res.getNumero());
            lblSaldo.setText("$" + ruletaController.getSaldo());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void volverMenu() {
        new VentanaMenu(sessionController).setVisible(true);
        dispose();
    }
}