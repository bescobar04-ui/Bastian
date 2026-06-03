package vista;

import controlador.SessionController;
import controlador.RuletaController;
import controlador.ResultadoController;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {
    private final SessionController sessionController;
    private final RuletaController ruletaController;
    private final ResultadoController resultadoController;

    private final JTextField txtUsuario = new JTextField(15);
    private final JPasswordField txtClave = new JPasswordField(15);
    private final JButton btnIngresar = new JButton("Ingresar");

    public VentanaLogin(SessionController sessionController, RuletaController ruletaController, ResultadoController resultadoController) {
        this.sessionController = sessionController;
        this.ruletaController = ruletaController;
        this.resultadoController = resultadoController;

        setTitle("Autenticación - Ruleta");
        setSize(320, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 8, 8));

        add(new JLabel("  Usuario:")); add(txtUsuario);
        add(new JLabel("  Contraseña:")); add(txtClave);
        add(new JLabel("")); add(btnIngresar);

        btnIngresar.addActionListener(e -> {
            String user = txtUsuario.getText();
            String pass = new String(txtClave.getPassword());

            if (sessionController.iniciarSesion(user, pass)) {
                JOptionPane.showMessageDialog(this, "¡Bienvenido al casino, " + sessionController.getUsuarioActual().getNombre() + "!");

                // 🎰 CONECTADO: Abre la ventana del juego pasándole los controladores limpios
                VentanaMenu menuPrincipal = new VentanaMenu(ruletaController, resultadoController);
                menuPrincipal.setVisible(true);

                this.dispose(); // Cierra el login de forma limpia
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}