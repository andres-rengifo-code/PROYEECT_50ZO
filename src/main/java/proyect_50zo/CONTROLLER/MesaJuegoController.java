package proyect_50zo.CONTROLLER;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import proyect_50zo.MODEL.Carta;
import proyect_50zo.MODEL.Jugador;
import proyect_50zo.MODEL.Mazo;

import java.util.ArrayList;
import java.util.List;

/** HU-2: prepara y muestra las manos iniciales, mazo y carta de mesa. */
public class MesaJuegoController {
    @FXML private HBox maquinasBox;
    @FXML private HBox manoHumanoBox;
    @FXML private Label labelCartasRestantes;
    @FXML private Label cartaMesa;
    @FXML private Label resumen;

    /**
     * Prepara la partida al cargar la pantalla: crea un mazo común, crea los
     * participantes elegidos, entrega cuatro cartas a cada uno y deja una
     * carta boca arriba como carta inicial de la mesa.
     */
    @FXML public void initialize() {
        Mazo mazo = new Mazo();
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("TÚ", false));
        for (int i = 1; i <= PantallaInicioController.numeroMaquinas; i++) jugadores.add(new Jugador("MÁQUINA " + i, true));
        for (Jugador jugador : jugadores) for (int i = 0; i < 4; i++) jugador.recibirCarta(mazo.entregarCarta());
        mostrarPreparacion(jugadores, mazo.entregarCarta(), mazo.cartasRestantes());
    }

    /**
     * Muestra el estado inicial solicitado: mano humana visible, manos de
     * máquinas ocultas, carta inicial en la mesa y mazo disponible.
     *
     * @param jugadores participantes con sus cuatro cartas iniciales.
     * @param inicial carta ubicada boca arriba en la mesa.
     * @param restantes cartas que permanecen boca abajo en el mazo.
     */
    private void mostrarPreparacion(List<Jugador> jugadores, Carta inicial, int restantes) {
        for (Jugador jugador : jugadores) {
            if (jugador.esMaquina()) {
                VBox maquina = new VBox(4, etiqueta(jugador.getNombre(), "#c9a227", 12), etiqueta("🂠  🂠  🂠  🂠", "#ffffff", 18));
                maquina.setStyle("-fx-alignment: center;");
                maquinasBox.getChildren().add(maquina);
            } else for (Carta carta : jugador.getMano()) {
                Label vista = etiqueta(carta.toString(), carta.esRoja() ? "#cc0000" : "#111111", 21);
                vista.setStyle(vista.getStyle() + "-fx-background-color: white; -fx-border-color: #c9a227; -fx-border-width: 2; -fx-padding: 15;");
                manoHumanoBox.getChildren().add(vista);
            }
        }
        cartaMesa.setText(inicial.toString());
        cartaMesa.setStyle("-fx-font-size: 38px; -fx-font-weight: bold; -fx-text-fill: " + (inicial.esRoja() ? "#cc0000" : "#ffffff") + ";");
        labelCartasRestantes.setText("MAZO BOCA ABAJO: " + restantes + " CARTAS");
        resumen.setText("PARTIDA LISTA: " + jugadores.size() + " JUGADORES, 4 CARTAS CADA UNO");
    }

    private Label etiqueta(String texto, String color, int tamano) {
        Label etiqueta = new Label(texto);
        etiqueta.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: " + tamano + "px; -fx-text-fill: " + color + ";");
        return etiqueta;
    }
}
