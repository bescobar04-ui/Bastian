package Vista;

import Controlador.SessionController;

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
        setTitle("Registro");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Clave:"));
        txtClave = new JPasswordField();
        add(txtClave);

        btnRegistrar = new JButton("Registrar");
        btnVolver = new JButton("Volver");

        add(btnRegistrar);
        add(btnVolver);

        btnRegistrar.addActionListener(e -> registrar());
        btnVolver.addActionListener(e -> volverLogin());
    }

    private void registrar() {
        String usuario = txtUsuario.getText();
        String clave = new String(txtClave.getPassword());
        String nombre = txtNombre.getText();

        // Caso 5: Estructura condicional IF para validar campos vacíos antes de llamar al controlador
        if (usuario.isBlank() || clave.isBlank() || nombre.isBlank()) {
            JOptionPane.showMessageDialog(this, "Error: Todos los campos son obligatorios.");
            return; // Rompe el flujo sin invocar al backend
        }

        try {
            // Caso 1: Invoca el controlador para el flujo normal de registro
            sessionController.registrarUsuario(usuario, clave, nombre);

            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente");
            new VentanaLogin(sessionController).setVisible(true);
            dispose();

        } catch (IllegalStateException e) {
            // Caso 1: Captura de la excepción de negocio (Usuario ya registrado)
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void volverLogin() {
        new VentanaLogin(sessionController).setVisible(true);
        dispose();
    }
}