package vista;

import controlador.SessionController;

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

        // Campos de texto según la lógica de Usuario
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

        // Acciones de los botones
        btnRegistrar.addActionListener(e -> registrar());
        btnVolver.addActionListener(e -> volverLogin());
    }

    private void registrar() {
        String user = txtUsuario.getText();
        String pass = new String(txtClave.getPassword());
        String nombre = txtNombre.getText();

        if (user.isEmpty() || pass.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Se delega la creación y almacenamiento al controlador
            sessionController.registrarUsuario(user, pass, nombre);

            JOptionPane.showMessageDialog(this, "¡Usuario registrado correctamente!");

            // Al terminar, volvemos al Login
            volverLogin();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar: " + e.getMessage());
        }
    }

    private void volverLogin() {
        new VentanaLogin(sessionController).setVisible(true);
        dispose();
    }
}