package vista;

import controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {

    private final SessionController sessionController;
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;
    private JButton btnIrRegistro;

    public VentanaLogin(SessionController sessionController) {
        this.sessionController = sessionController;
        inicializar();
    }

    private void inicializar() {
        setTitle("Acceso - Casino UFRO");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Mejora 1: Padding y diseño más limpio
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        panel.add(new JLabel("Clave:"));
        txtClave = new JPasswordField();
        panel.add(txtClave);

        btnIngresar = new JButton("Ingresar");
        btnIrRegistro = new JButton("Registrarse");

        panel.add(btnIngresar);
        panel.add(btnIrRegistro);

        add(panel);

        btnIngresar.addActionListener(e -> intentarLogin());
        btnIrRegistro.addActionListener(e -> abrirRegistro());

        // Mejora 2: Permitir presionar "Enter" para entrar
        getRootPane().setDefaultButton(btnIngresar);
    }

    private void intentarLogin() {
        String usuario = txtUsuario.getText();
        String clave = new String(txtClave.getPassword());

        // Mejora 3: Validación de campos vacíos antes de llamar al controlador
        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean ok = sessionController.iniciarSesion(usuario, clave);

        if (ok) {
            // El SessionController ahora "mantiene" al usuario actual para toda la sesión
            JOptionPane.showMessageDialog(this, "Bienvenido " + sessionController.getUsuarioActual().getNombre());

            // Pasamos la ÚNICA instancia de sessionController
            new VentanaMenu(sessionController).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirRegistro() {
        // Redirigir a la ventana que ya tienes creada
        new VentanaRegistro(sessionController).setVisible(true);
        dispose();
    }
}