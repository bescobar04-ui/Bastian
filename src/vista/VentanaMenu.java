package vista; // En minúsculas para que coincida con tu carpeta real

import controlador.SessionController; // En minúsculas
import controlador.RuletaController; // En minúsculas
import modelo.Ruleta;               // En minúsculas

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

    public VentanaMenu(SessionController sessionController) {
        this.sessionController = sessionController;
        // Se inicializa el controlador de la ruleta con un saldo inicial (Iteración 4)
        this.ruletaController = new RuletaController(new Ruleta(1000));
        inicializar();
    }

    private void inicializar() {
        setTitle("Menú Principal - Casino UFRO");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        lblBienvenida = new JLabel("Bienvenido: " + sessionController.getNombreUsuario(), SwingConstants.CENTER);
        lblSaldo = new JLabel("Saldo: $" + ruletaController.getSaldo(), SwingConstants.CENTER);

        btnJugar = new JButton("Ir a la Ruleta");
        btnPerfil = new JButton("Ver Mi Perfil");
        btnCerrarSesion = new JButton("Cerrar Sesión (Cerrar)");

        add(lblBienvenida);
        add(lblSaldo);
        add(btnJugar);
        add(btnPerfil);
        add(btnCerrarSesion);

        btnJugar.addActionListener(e -> abrirJuego());
        btnPerfil.addActionListener(e -> mostrarPerfil());
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
    }

    private void abrirJuego() {
        // Pasamos ambos controladores a la ventana de juego (Inyección de dependencias)
        new VentanaJuego(sessionController, ruletaController).setVisible(true);
        dispose(); // Cerramos el menú para ahorrar memoria
    }

    private void mostrarPerfil() {
        if (sessionController.getUsuarioActual() != null) {
            JOptionPane.showMessageDialog(this,
                    "Nombre: " + sessionController.getUsuarioActual().getNombre() +
                            "\nUsuario: " + sessionController.getUsuarioActual().getUsername() +
                            "\nSaldo: $" + ruletaController.getSaldo(),
                    "Información del Perfil",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cerrarSesion() {
        sessionController.cerrarSesion();
        // Al cerrar sesión, volvemos a la ventana de Login
        new VentanaLogin(sessionController).setVisible(true);
        dispose(); // Liberamos la memoria del menú
    }
}