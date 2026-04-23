package vista;

import controlador.SessionController;
import modelo.Resultado;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaHistorial extends JFrame {

    private final SessionController sessionController;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public VentanaHistorial(SessionController sessionController) {
        this.sessionController = sessionController;
        inicializar();
        cargarDatos();
    }

    private void inicializar() {
        // El título muestra a quién pertenece el historial
        setTitle("Historial de Jugadas - " + sessionController.getUsuarioActual().getNombre());
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana, no el programa
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Definimos las columnas requeridas por la guía
        String[] columnas = {"Número Salió", "Tipo Apuesta", "Monto Apostado", "Resultado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);

        // 2. Agregamos un scroll por si hay muchas jugadas
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        // 3. Botón para volver al menú
        JButton btnCerrar = new JButton("Cerrar Historial");
        btnCerrar.addActionListener(e -> dispose());

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);
        add(panelBoton, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        // Recuperamos el historial directamente del modelo Usuario
        List<Resultado> historial = sessionController.getUsuarioActual().getHistorial();

        for (Resultado r : historial) {
            Object[] fila = {
                    r.getNumero(),
                    r.getTipoApuesta(),
                    "$" + r.getMonto(),
                    r.isAcierto() ? "GANASTE" : "PERDISTE"
            };
            modeloTabla.addRow(fila);
        }
    }
}