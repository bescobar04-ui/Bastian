package vista; // En minúsculas para que coincida con tu carpeta

import controlador.SessionController; // En minúsculas

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {

    private final SessionController sessionController;

    private JTextField txtNombre;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnRegistrar;
    private JButton btnVolver;

    public VentanaRegistro(SessionController sessionController) {
        this.sessionController = sessionController;
        inicializar();
    }

    private void inicializar() {
        setTitle("Registro de Usuario - Casino UFRO");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Nombre de Usuario:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Contraseña:"));
        txtClave = new JPasswordField();
        add(txtClave);

        btnRegistrar = new JButton("Registrar");
        btnVolver = new JButton("Volver al Login");

        add(btnRegistrar);
        add(btnVolver);

        btnRegistrar.addActionListener(e -> registrar());
        btnVolver.addActionListener(e -> volverLogin());
    }

    private void registrar() {
        try {
            sessionController.registrarUsuario(
                    txtUsuario.getText(),
                    new String(txtClave.getPassword()),
                    txtNombre.getText()
            );

            JOptionPane.showMessageDialog(this, "¡Usuario registrado correctamente!");
            // Volvemos al login para que el usuario entre con sus nuevas credenciales
            new VentanaLogin(sessionController).setVisible(true);
            dispose(); // Liberamos memoria de la ventana de registro

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Faltan datos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void volverLogin() {
        new VentanaLogin(sessionController).setVisible(true);
        dispose();
    }
}