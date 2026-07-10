package sudoku.proyect_50zo.CONTROLLER;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador (capa <b>C</b> de MVC) de la pantalla de inicio.
 * <p>
 * Permite elegir contra cuántos jugadores máquina (1, 2 o 3) se jugará y,
 * al iniciar la partida, carga la pantalla de juego
 * ({@link PantallaJuegoController}) pasándole esa cantidad.
 */
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

    /** Selecciona la opción "1 jugador vs 1 máquina". */
    @FXML
    public void elegirUno(MouseEvent event) {
        resetCards();
        activarCard(card1);
        jugadoresSeleccionados = 1;
        labelStatus.setText("✓  1 JUGADOR VS MAQUINA");
        labelStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: #4caf81; -fx-font-family: 'Courier New';");
    }

    /** Selecciona la opción "1 jugador vs 2 máquinas". */
    @FXML
    public void elegirDos(MouseEvent event) {
        resetCards();
        activarCard(card2);
        jugadoresSeleccionados = 2;
        labelStatus.setText("✓  1 JUGADOR VS DOS MAQUINAS");
        labelStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: #4caf81; -fx-font-family: 'Courier New';");
    }

    /** Selecciona la opción "1 jugador vs 3 máquinas". */
    @FXML
    public void elegirTres(MouseEvent event) {
        resetCards();
        activarCard(card3);
        jugadoresSeleccionados = 3;
        labelStatus.setText("✓  1 JUGADOR VS TRES MAQUINAS");
        labelStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: #4caf81; -fx-font-family: 'Courier New';");
    }

    /**
     * Valida que se haya elegido una cantidad de jugadores máquina y, de
     * ser así, carga {@code PantallaJuego.fxml}, configura la partida a
     * través de {@link PantallaJuegoController#configurarPartida(int)} y
     * reemplaza la escena actual por la de juego.
     */
    @FXML
    public void iniciarJuego(ActionEvent event) {
        if (jugadoresSeleccionados == 0) {
            labelStatus.setText("⚠  ELIGE CUANTOS JUGADORES PRIMERO");
            labelStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: #e8423a; -fx-font-family: 'Courier New';");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sudoku/proyect_50zo/PantallaJuego.fxml"));
            Parent root = loader.load();

            PantallaJuegoController controller = loader.getController();
            controller.configurarPartida(jugadoresSeleccionados);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 700, 500));
        } catch (IOException e) {
            // Excepción marcada (checked) estándar de Java: falla al cargar el FXML.
            labelStatus.setText("⚠  NO SE PUDO CARGAR LA PANTALLA DE JUEGO");
            labelStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: #e8423a; -fx-font-family: 'Courier New';");
            e.printStackTrace();
        }
    }
}