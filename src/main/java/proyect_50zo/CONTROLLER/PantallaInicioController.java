package proyect_50zo.CONTROLLER;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import proyect_50zo.UTILS.InsertScene;
import proyect_50zo.UTILS.Paths;

/** HU-1: permite seleccionar 1, 2 o 3 jugadores máquina. */

public class PantallaInicioController {
    @FXML private VBox card1;
    @FXML private VBox card2;
    @FXML private VBox card3;
    @FXML private Label labelStatus;

    public static int numeroMaquinas;
    private int jugadoresSeleccionados;

    private void resetCards() {
        String estilo = "-fx-background-color: #0f1f0f; -fx-border-color: #c9a227; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-cursor: hand;";
        card1.setStyle(estilo); card2.setStyle(estilo); card3.setStyle(estilo);
    }

    private void seleccionar(VBox card, int cantidad, String mensaje) {
        resetCards();
        card.setStyle("-fx-background-color: #1a3a1a; -fx-border-color: #f5c518; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-cursor: hand;");
        jugadoresSeleccionados = cantidad;
        labelStatus.setText(mensaje);
        labelStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: #4caf81; -fx-font-family: 'Courier New';");
    }

    /** Selecciona una partida contra una máquina. @param event clic de la interfaz. */
    @FXML public void elegirUno(MouseEvent event) { seleccionar(card1, 1, "1 JUGADOR VS 1 MAQUINA"); }

    /** Selecciona una partida contra dos máquinas. @param event clic de la interfaz. */
    @FXML public void elegirDos(MouseEvent event) { seleccionar(card2, 2, "1 JUGADOR VS 2 MAQUINAS"); }

    /** Selecciona una partida contra tres máquinas. @param event clic de la interfaz. */
    @FXML public void elegirTres(MouseEvent event) { seleccionar(card3, 3, "1 JUGADOR VS 3 MAQUINAS"); }

    /**
     * Valida la selección y abre la pantalla que prepara la partida.
     */
    @FXML public void iniciarJuego() {
        if (jugadoresSeleccionados == 0) {
            labelStatus.setText("ELIGE CUANTOS JUGADORES MAQUINA QUIERES");
            labelStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: #e8423a; -fx-font-family: 'Courier New';");
            return;
        }
        numeroMaquinas = jugadoresSeleccionados;
        InsertScene.setScene(Paths.MESAJUEGO);
    }
}
