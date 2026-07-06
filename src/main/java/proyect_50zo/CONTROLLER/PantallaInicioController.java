package proyect_50zo.CONTROLLER;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

public class PantallaInicioController {

    @FXML private VBox card1;
    @FXML private VBox card2;
    @FXML private VBox card3;
    @FXML private Label labelStatus;

    private int jugadoresSeleccionados = 0;

    private void resetCards() {
        String estiloNormal = "-fx-background-color: #0f1f0f; -fx-border-color: #c9a227; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-cursor: hand;";
        card1.setStyle(estiloNormal);
        card2.setStyle(estiloNormal);
        card3.setStyle(estiloNormal);
    }

    private void activarCard(VBox card) {
        String estiloActivo = "-fx-background-color: #1a3a1a; -fx-border-color: #f5c518; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-cursor: hand;";
        card.setStyle(estiloActivo);
    }

    @FXML
    public void elegirUno(MouseEvent event) {
        resetCards();
        activarCard(card1);
        jugadoresSeleccionados = 1;
        labelStatus.setText("✓  1 JUGADOR VS MAQUINA");
        labelStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: #4caf81; -fx-font-family: 'Courier New';");
    }

    @FXML
    public void elegirDos(MouseEvent event) {
        resetCards();
        activarCard(card2);
        jugadoresSeleccionados = 2;
        labelStatus.setText("✓  1 JUGADOR VS DOS MAQUINAS");
        labelStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: #4caf81; -fx-font-family: 'Courier New';");
    }

    @FXML
    public void elegirTres(MouseEvent event) {
        resetCards();
        activarCard(card3);
        jugadoresSeleccionados = 3;
        labelStatus.setText("✓  1 JUGADOR VS TRES MAQUINAS");
        labelStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: #4caf81; -fx-font-family: 'Courier New';");
    }

    @FXML
    public void iniciarJuego() {
        if (jugadoresSeleccionados == 0) {
            labelStatus.setText("⚠  ELIGE CUANTOS JUGADORES PRIMERO");
            labelStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: #e8423a; -fx-font-family: 'Courier New';");
            return;
        }
        System.out.println("Iniciando juego con " + jugadoresSeleccionados + " jugador(es)");
        // Aqui va la logica para cambiar de pantalla
    }
}