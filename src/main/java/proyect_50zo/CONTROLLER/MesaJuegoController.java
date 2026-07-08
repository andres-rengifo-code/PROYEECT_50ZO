package proyect_50zo.CONTROLLER;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import proyect_50zo.MODEL.Jugador;
import proyect_50zo.MODEL.Maquina;
import proyect_50zo.MODEL.Mazo;
import proyect_50zo.CONTROLLER.PantallaInicioController.ConfiguracionJuego;

public class MesaJuegoController {

    @FXML private VBox zonaMaquina1;
    @FXML private VBox zonaMaquina2;
    @FXML private VBox zonaMaquina3;

    @FXML private Label labelCartasRestantes;
    @FXML private Label puntajeJugador;
    @FXML private Label labelTurno;

    private Mazo mazo;
    private Jugador jugador;
    private Maquina maquina1;
    private Maquina maquina2;
    private Maquina maquina3;

    @FXML
    public void initialize() {

        mazo = new Mazo();
        mazo.barajar();
        ocultarMaquinas(ConfiguracionJuego.numeroMaquinas);




    }

    @FXML
    public void pedirCarta() {



    }

    @FXML
    public void plantarse() {

    }

    private void actualizarInterfaz() {

    }

    public void ocultarMaquinas(int numMaquinas){

        zonaMaquina1.setVisible(numMaquinas >= 1);
        zonaMaquina1.setManaged(numMaquinas >= 1);

        zonaMaquina2.setVisible(numMaquinas >= 2);
        zonaMaquina2.setManaged(numMaquinas >= 2);

        zonaMaquina3.setVisible(numMaquinas >= 3);
        zonaMaquina3.setManaged(numMaquinas >= 3);

    }

}