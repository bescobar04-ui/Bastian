package Vista;

import Controlador.SessionController;
import Controlador.RuletaController;
import Modelo.Resultado;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorial extends JFrame {

    private final SessionController sessionController;
    private final RuletaController ruletaController;

    private JTextArea txtHistorial;
    private JButton btnVolver;

    public VentanaHistorial(SessionController sessionController, RuletaController ruletaController) {
        this.sessionController = sessionController;
        this.ruletaController = ruletaController;

        // CASO 3: Verificación condicional estricta de sesión activa en la capa de presentación externa
        if (!sessionController.hayUsuario()) {
            JOptionPane.showMessageDialog(null, "Acceso denegado: No existe una sesión activa para ver el historial.", "Seguridad", JOptionPane.ERROR_MESSAGE);
            SwingUtilities.invokeLater(() -> new VentanaLogin(sessionController).setVisible(true));
            this.dispose();
            return;
        }
        inicializar();
    }

    private void inicializar() {
        setTitle("Historial");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        txtHistorial = new JTextArea();
        txtHistorial.setEditable(false);
        cargarHistorial();

        btnVolver = new JButton("Volver");

        add(new JScrollPane(txtHistorial), BorderLayout.CENTER);
        add(btnVolver, BorderLayout.SOUTH);

        // CASO 6: Red de seguridad en el Listener del botón de retorno
        btnVolver.addActionListener(e -> {
            try {
                volverMenu();
            } catch (Exception ex) {
                System.err.println("Fallo evitado al regresar al menú principal: " + ex.getMessage());
            }
        });
    }

    private void cargarHistorial() {
        // CASO 6: El método delega al controlador y maneja la respuesta defensivamente de forma local
        try {
            List<Resultado> historial = ruletaController.obtenerHistorial();

            if (historial == null || historial.isEmpty()) {
                txtHistorial.setText("No hay jugadas registradas todavía.");
                return;
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < historial.size(); i++) {
                Resultado r = historial.get(i);

                // Control preventivo ante objetos corruptos leídos desde la persistencia
                if (r == null) continue;

                sb.append("Jugada ").append(i + 1).append("\n");
                sb.append("Número: ").append(r.getNumero()).append("\n");
                sb.append("Color: ").append(r.getColor()).append("\n");
                sb.append("Tipo de apuesta: ").append(r.getTipoApuesta()).append("\n");
                sb.append("Monto: $").append(r.getMonto()).append("\n");
                sb.append("Resultado: ").append(r.isAcierto() ? "Ganó" : "Perdió").append("\n");
                sb.append("-------------------------\n");
            }

            txtHistorial.setText(sb.toString());

        } catch (Exception e) {
            txtHistorial.setText("Ocurrió un error inesperado al cargar el archivo de historial.");
            System.err.println("Fallo mitigado en carga de historial: " + e.getMessage());
        }
    }

    private void volverMenu() {
        new VentanaMenu(sessionController, ruletaController).setVisible(true);
        dispose();
    }
}