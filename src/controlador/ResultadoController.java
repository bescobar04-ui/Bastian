package controlador;

import modelo.Ruleta;
import modelo.Resultado;
import java.util.List;

public class ResultadoController {
    private final Ruleta ruleta;

    public ResultadoController(Ruleta ruleta) {
        this.ruleta = ruleta;
    }

    public List<Resultado> getHistorial() {
        return ruleta.getHistorialRondas();
    }
}