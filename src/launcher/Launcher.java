package launcher; // En minúsculas para que coincida con tu carpeta real

import controlador.SessionController; // En minúsculas
import vista.VentanaLogin;           // En minúsculas

public class Launcher {
    public static void main(String[] args) {
        // 1. Creamos la instancia del controlador (El cerebro)
        SessionController session = new SessionController();

        // 2. Inyectamos el controlador en la vista (Inyección de dependencias)
        VentanaLogin login = new VentanaLogin(session);

        // 3. Hacemos visible la ventana
        login.setVisible(true);
    }
}