package vista;

import controlador.SessionController;
import controlador.RuletaController;
import modelo.Ruleta;
import modelo.Usuario; // ESTA LÍNEA ES LA QUE FALTA

import javax.swing.*;
import java.awt.*;

public class VentanaMenu extends JFrame {

    private final SessionController sessionController;
    private final RuletaController ruletaController;

    private JLabel lblBienvenida;
    private JLabel lblSaldo;
    private JButton btnJugar;
    private JButton btnHistorial;
    private JButton btnCerrarSesion;

    public VentanaMenu(SessionController sessionController) {
        this.sessionController = sessionController;
        this.ruletaController = new RuletaController(new Ruleta(1000));
        inicializar();
    }

    private void inicializar() {
        setTitle("Menú Principal - Casino UFRO");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        String nombreUsuario = sessionController.getUsuarioActual().getNombre();
        lblBienvenida = new JLabel("Bienvenido: " + nombreUsuario, SwingConstants.CENTER);
        lblSaldo = new JLabel("Saldo Disponible: $" + ruletaController.getSaldo(), SwingConstants.CENTER);

        btnJugar = new JButton("Ir a la Ruleta");
        btnHistorial = new JButton("Ver Mi Historial de Jugadas");
        btnCerrarSesion = new JButton("Cerrar Sesión");

        add(lblBienvenida); add(lblSaldo); add(btnJugar);
        add(btnHistorial); add(btnCerrarSesion);

        btnJugar.addActionListener(e -> abrirJuego());
        btnHistorial.addActionListener(e -> abrirHistorial());
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
    }

    private void abrirJuego() {
        new VentanaJuego(sessionController, ruletaController).setVisible(true);
        dispose();
    }

    private void abrirHistorial() {
        new VentanaHistorial(sessionController).setVisible(true);
    }

    private void cerrarSesion() {
        new VentanaLogin(sessionController).setVisible(true);
        dispose();
    }
}