package launcher;

import controlador.RuletaController;
import controlador.ResultadoController;
import controlador.SessionController;
import modelo.IRepositorioResultados;
import modelo.RepositorioArchivo;
import modelo.Ruleta;
import vista.VentanaLogin;

public class Launcher {
    public static void main(String[] args) {
        // 1. Inicializar la persistencia (Cumple con la Ruleta 8)
        // Usamos RepositorioArchivo para guardar permanentemente en el historial_ruleta.csv
        IRepositorioResultados repoElegido = new RepositorioArchivo();

        // 2. Inicializar el motor del modelo con un saldo inicial de $15.000 y el repositorio
        Ruleta ruleta = new Ruleta(15000, repoElegido);

        // 3. Inicializar los controladores inyectándoles sus dependencias correspondientes
        SessionController sessionController = new SessionController();
        RuletaController ruletaController = new RuletaController(ruleta);
        ResultadoController resultadoController = new ResultadoController(ruleta);

        // 4. Registrar un usuario de prueba en el sistema para poder loguearse al tiro
        // Puedes cambiar "bastian" y "123" por los datos que quieras probar
        sessionController.registrarUsuario("bastian", "123", "Bastián");

        System.out.println("--- Casino Virtual: Ruleta Inicializada ---");
        System.out.println("Saldo inicial cargado en el motor: $" + ruleta.getSaldo());
        System.out.println("Lanzando pantalla de autenticación...");

        // 5. Lanzar la interfaz gráfica pasando los tres controladores estructurados
        VentanaLogin login = new VentanaLogin(sessionController, ruletaController, resultadoController);
        login.setVisible(true);
    }
}