package proyect_50zo.CONTROLLER;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import proyect_50zo.UTILS.InsertScene;
import proyect_50zo.UTILS.Paths;

/**
 * HU-6. Controla la pantalla final.
 */
public class PantallaFinalController {

    private static String ganador;

    @FXML
    private Label mensajeGanador;

    /**
     * HU-6. Guarda el ganador.
     */
    public static void setGanador(String nombre) {
        ganador = nombre;
    }

    /**
     * Muestra el ganador.
     */
    @FXML
    public void initialize() {
        mensajeGanador.setText("GANADOR: " + ganador);
    }

    /**
     * Regresa al inicio.
     */
    @FXML
    public void volverAlInicio() {
        InsertScene.setScene(Paths.INICIO);
    }
}