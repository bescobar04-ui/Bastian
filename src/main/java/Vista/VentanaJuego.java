package Vista;

import Controlador.SessionController;
import Controlador.RuletaController;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

    private final SessionController sessionController;
    private final RuletaController ruletaController;

    private JLabel lblSaldo;
    private JTextField txtMonto;
    private JComboBox<String> comboCasillas;
    private JButton btnApostar;
    private JButton btnVolver;
    private JTextArea txtResultado;

    public VentanaJuego(SessionController sessionController, RuletaController ruletaController) {
        this.sessionController = sessionController;
        this.ruletaController = ruletaController;

        // CASO 3: Validación estricta de sesión antes de cargar la interfaz de juego
        if (!sessionController.hayUsuario()) {
            JOptionPane.showMessageDialog(null, "Acceso denegado. Inicie sesión primero.", "Seguridad", JOptionPane.ERROR_MESSAGE);
            SwingUtilities.invokeLater(() -> new VentanaLogin(sessionController).setVisible(true));
            this.dispose();
            return;
        }
        inicializar();
    }

    private void inicializar() {
        setTitle("Ruleta - Tablero de Juego");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Saldo Disponible:"));
        lblSaldo = new JLabel("$" + ruletaController.getSaldo());
        add(lblSaldo);

        add(new JLabel("Monto a Apostar:"));
        txtMonto = new JTextField();
        add(txtMonto);

        add(new JLabel("Seleccione Casilla/Color:"));
        // Opciones de ejemplo para tu ruleta (números o tipos de apuestas)
        String[] opciones = {"ROJO", "NEGRO", "PAR", "IMPAR", "0", "14", "22", "36"};
        comboCasillas = new JComboBox<>(opciones);
        add(comboCasillas);

        btnApostar = new JButton("Girar Ruleta");
        btnVolver = new JButton("Volver al Menú");
        add(btnApostar);
        add(btnVolver);

        add(new JLabel("Resultado:"));
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        add(new JScrollPane(txtResultado));

        // Asignación de eventos con protección
        btnApostar.addActionListener(e -> ejecutarApuesta());
        btnVolver.addActionListener(e -> volverMenu());
    }

    private void ejecutarApuesta() {
        // CASO 6: Red de seguridad global en el Listener de Swing para fallos imprevistos
        try {
            // Validación local previa: Evitar selección nula en componentes
            if (comboCasillas.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una casilla válida.");
                return;
            }

            String casillaElegida = (String) comboCasillas.getSelectedItem();
            String montoStr = txtMonto.getText().trim();

            // Validación local previa: Evitar campos de texto vacíos en el formulario
            if (montoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un monto para apostar.");
                return;
            }

            int montoInput;
            try {
                montoInput = Integer.parseInt(montoStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int saldoDisponible = ruletaController.getSaldo();

            // CASO 2: Controles y validaciones estrictas con estructuras 'if' (Flujo normal sin lanzar excepciones)
            if (montoInput <= 0) {
                JOptionPane.showMessageDialog(this, "El monto de la apuesta debe ser un valor positivo.", "Validación", JOptionPane.WARNING_MESSAGE);
                return; // Rompe el flujo de forma segura
            }

            if (montoInput > saldoDisponible) {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente para cubrir esta apuesta.", "Validación", JOptionPane.WARNING_MESSAGE);
                return; // Rompe el flujo de forma segura
            }

            // Si pasa todas las validaciones previas de la vista, se procesa la jugada en el backend
            // Nota: Adapta este método según el nombre exacto que use tu RuletaController para jugar (ej: ruletaController.jugar o similar)
            ruletaController.jugar(montoInput, casillaElegida);

            // Actualizar interfaz con el nuevo saldo e historial/resultado
            lblSaldo.setText("$" + ruletaController.getSaldo());
            txtResultado.setText("¡Apuesta procesada!\nRevisa el Historial o Estadísticas para ver los detalles.");
            txtMonto.setText("");

        } catch (Exception ex) {
            // CASO 6: Captura de la red de seguridad para que la app no sufra un crash imprevisto
            System.err.println("Fallo inesperado mitigado en el hilo de Swing (VentanaJuego): " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Ocurrió un error interno en la interfaz gráfica.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverMenu() {
        new VentanaMenu(sessionController, ruletaController).setVisible(true);
        dispose();
    }
}