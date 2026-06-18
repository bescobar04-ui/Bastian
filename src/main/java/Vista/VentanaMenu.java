package Vista;

import Controlador.SessionController;
import Controlador.RuletaController;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu extends JFrame {

    private final SessionController sessionController;
    private final RuletaController ruletaController;

    private JLabel lblBienvenida;
    private JLabel lblSaldo;
    private JButton btnJugar;
    private JButton btnPerfil;
    private JButton btnCerrarSesion;
    private JButton btnHistorial;
    private JButton btnEstadisticas;

    public VentanaMenu(SessionController sessionController, RuletaController ruletaController) {
        this.sessionController = sessionController;
        this.ruletaController = ruletaController;

        // CASO 3: Validación estricta de sesión antes de inicializar componentes visuales
        if (!sessionController.hayUsuario()) {
            JOptionPane.showMessageDialog(null, "Acceso denegado. Debe iniciar sesión primero.", "Seguridad", JOptionPane.ERROR_MESSAGE);
            // Redirección inmediata y segura al Login sin construir la ventana rota
            SwingUtilities.invokeLater(() -> new VentanaLogin(sessionController).setVisible(true));
            this.dispose();
            return;
        }
        inicializar();
    }

    private void inicializar() {
        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 10, 10));

        lblBienvenida = new JLabel("Bienvenido: " + sessionController.getNombreUsuario(), SwingConstants.CENTER);
        lblSaldo = new JLabel("Saldo: $" + ruletaController.getSaldo(), SwingConstants.CENTER);

        btnJugar = new JButton("Jugar");
        btnPerfil = new JButton("Perfil");
        btnHistorial = new JButton("Historial");
        btnEstadisticas = new JButton("Estadísticas");
        btnCerrarSesion = new JButton("Cerrar sesión");

        add(lblBienvenida);
        add(lblSaldo);
        add(btnJugar);
        add(btnPerfil);
        add(btnHistorial);
        add(btnEstadisticas);
        add(btnCerrarSesion);

        // CASO 6: Aplicación de red de seguridad/resiliencia en listeners de la UI
        btnJugar.addActionListener(e -> {
            try {
                abrirJuego();
            } catch (Exception ex) {
                System.err.println("Fallo evitado al abrir el juego: " + ex.getMessage());
            }
        });

        btnPerfil.addActionListener(e -> {
            try {
                mostrarPerfil();
            } catch (Exception ex) {
                System.err.println("Fallo evitado al mostrar el perfil: " + ex.getMessage());
            }
        });

        btnHistorial.addActionListener(e -> {
            try {
                abrirHistorial();
            } catch (Exception ex) {
                System.err.println("Fallo evitado al abrir el historial: " + ex.getMessage());
            }
        });

        btnEstadisticas.addActionListener(e -> {
            try {
                abrirEstadisticas();
            } catch (Exception ex) {
                System.err.println("Fallo evitado al abrir estadísticas: " + ex.getMessage());
            }
        });

        btnCerrarSesion.addActionListener(e -> {
            try {
                cerrarSesion();
            } catch (Exception ex) {
                System.err.println("Fallo evitado al cerrar sesión: " + ex.getMessage());
            }
        });
    }

    private void abrirJuego() {
        new VentanaJuego(sessionController, ruletaController).setVisible(true);
        dispose();
    }

    private void mostrarPerfil() {
        // CASO 3: Intercepción controlada de IllegalStateException si se invoca el método protegido sin sesión válida
        try {
            JOptionPane.showMessageDialog(this,
                    "Nombre: " + sessionController.getUsuarioActual().getNombre() +
                            "\nUsuario: " + sessionController.getUsuarioActual().getUsername() +
                            "\nSaldo: $" + ruletaController.getSaldo());
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Estado", JOptionPane.ERROR_MESSAGE);
            cerrarSesion(); // Forzar salida limpia ante inconsistencia de datos
        }
    }

    private void abrirHistorial() {
        new VentanaHistorial(sessionController, ruletaController).setVisible(true);
        dispose();
    }

    private void abrirEstadisticas() {
        new VentanaEstadisticas(ruletaController).setVisible(true);
    }

    private void cerrarSesion() {
        sessionController.cerrarSesion();
        new VentanaLogin(sessionController).setVisible(true);
        dispose();
    }
}