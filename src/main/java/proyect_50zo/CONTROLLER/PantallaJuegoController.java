package proyect_50zo.CONTROLLER;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import proyect_50zo.EXCEPTION.JugadaInvalidaException;
import proyect_50zo.EXCEPTION.MazoVacioException;
import proyect_50zo.EXCEPTION.TurnoInvalidoException;
import proyect_50zo.HILO.HiloDecisionMaquina;
import proyect_50zo.HILO.HiloTomaMaquina;
import proyect_50zo.MODEL.Carta;
import proyect_50zo.MODEL.Jugador;
import proyect_50zo.MODEL.Mazo;
import proyect_50zo.MODEL.Mesa;
import proyect_50zo.MODEL.OpcionJugada;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controlador (capa <b>C</b> de MVC) de la pantalla de juego de
 * "El Cincuentazo". Orquesta el {@link Mazo}, la {@link Mesa} y los
 * {@link Jugador} del {@code MODEL}, y actualiza los nodos de la vista
 * FXML según avanza la partida.
 * <p>
 * Alcance actual de esta pantalla:
 * <ul>
 *     <li><b>HU-3 Jugar una carta:</b> el jugador humano y los jugadores
 *     máquina pueden seleccionar una carta de su mano que no haga
 *     superar 50 la suma de la mesa; la carta jugada queda encima de la
 *     carta anterior.</li>
 *     <li><b>HU-4 Tomar una carta del mazo:</b> después de jugar, el
 *     jugador toma una carta del mazo para volver a tener 4 en mano y
 *     el turno pasa al siguiente jugador.</li>
 * </ul>
 * La eliminación de jugadores y el fin de la partida no forman parte de
 * esta pantalla por ahora: si un jugador no tiene ninguna jugada válida,
 * simplemente se le salta el turno.
 */
public class PantallaJuegoController {

    @FXML private Label lblSuma;
    @FXML private Label lblCartaMesa;
    @FXML private Label lblTurno;
    @FXML private Label lblMensaje;
    @FXML private Label lblMazoRestante;
    @FXML private HBox handHumanBox;
    @FXML private HBox oponentesBox;
    @FXML private Button btnTomarCarta;

    private static final String ESTILO_CARTA_JUGABLE =
            "-fx-background-color: #142914; -fx-border-color: #f5c518; -fx-border-width: 2; " +
            "-fx-border-radius: 8; -fx-background-radius: 8; -fx-text-fill: #f5c518; " +
            "-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: 'Courier New'; -fx-cursor: hand;";

    private static final String ESTILO_CARTA_BLOQUEADA =
            "-fx-background-color: #0f1f0f; -fx-border-color: #333333; -fx-border-width: 2; " +
            "-fx-border-radius: 8; -fx-background-radius: 8; -fx-text-fill: #555555; " +
            "-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: 'Courier New';";

    private Mazo mazo;
    private Mesa mesa;
    private List<Jugador> jugadores;
    private final Map<Jugador, Label> etiquetasEstadoMaquina = new LinkedHashMap<>();

    private int turnoActual;
    private boolean cartaJugadaEsteTurno;

    /**
     * Arma y arranca una partida nueva. Debe llamarse justo después de
     * cargar el FXML, antes de mostrar la escena.
     *
     * @param cantidadMaquinas cantidad de jugadores máquina (1, 2 o 3),
     *                         elegida en la pantalla de inicio.
     */
    public void configurarPartida(int cantidadMaquinas) {
        mazo = new Mazo();
        mesa = new Mesa();
        jugadores = new ArrayList<>();
        jugadores.add(new Jugador("TÚ", false));
        for (int i = 1; i <= cantidadMaquinas; i++) {
            jugadores.add(new Jugador("MÁQUINA " + i, true));
        }

        repartirManoInicial();
        colocarCartaInicialEnMesa();

        turnoActual = 0;
        cartaJugadaEsteTurno = false;

        construirVistaOponentes();
        setMensaje("");
        comenzarTurno();
    }

    /** Reparte 4 cartas a cada jugador desde el mazo (preparación del juego). */
    private void repartirManoInicial() {
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 4; i++) {
                try {
                    jugador.agregarCarta(mazo.tomarCarta());
                } catch (MazoVacioException e) {
                    // No puede ocurrir: 52 cartas alcanzan de sobra para repartir
                    // 4 cartas a un máximo de 4 jugadores (16 cartas en total).
                    throw new IllegalStateException("El mazo no tiene cartas suficientes para repartir.", e);
                }
            }
        }
    }

    /** Coloca boca arriba la primera carta del mazo e inicia la suma de la mesa. */
    private void colocarCartaInicialEnMesa() {
        try {
            Carta inicial = mazo.tomarCarta();
            mesa.colocarCartaInicial(inicial, valorInicial(inicial));
        } catch (MazoVacioException e) {
            throw new IllegalStateException("El mazo no tiene carta para iniciar la mesa.", e);
        }
    }

    /**
     * Determina el valor con el que inicia la suma de la mesa según la
     * carta inicial: 0 si es 9, -10 si es J/Q/K, 1 si es As, o su propio
     * número en los demás casos.
     */
    private int valorInicial(Carta carta) {
        if (carta.esAs()) {
            return 1;
        }
        return carta.valoresPosibles()[0];
    }

    // ----------------- Construcción / actualización de la vista -----------------

    private void construirVistaOponentes() {
        oponentesBox.getChildren().clear();
        etiquetasEstadoMaquina.clear();
        for (Jugador jugador : jugadores) {
            if (!jugador.esMaquina()) continue;
            VBox caja = new VBox(4);
            caja.setAlignment(Pos.CENTER);
            Label nombre = new Label(jugador.getNombre());
            nombre.setStyle("-fx-text-fill: #c9a227; -fx-font-size: 11px; -fx-font-family: 'Courier New';");
            Label estado = new Label("🂠 x" + jugador.getMano().size());
            estado.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px;");
            caja.getChildren().addAll(nombre, estado);
            oponentesBox.getChildren().add(caja);
            etiquetasEstadoMaquina.put(jugador, estado);
        }
    }

    private void actualizarVistaOponentes() {
        for (Map.Entry<Jugador, Label> entrada : etiquetasEstadoMaquina.entrySet()) {
            Jugador jugador = entrada.getKey();
            entrada.getValue().setText("🂠 x" + jugador.getMano().size());
        }
    }

    private void actualizarVistaMesa() {
        Carta superior = mesa.getCartaSuperior();
        lblCartaMesa.setText(superior.toString());
        lblCartaMesa.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-font-family: 'Impact'; -fx-text-fill: "
                + (superior.esRoja() ? "#e8423a;" : "#f5f5f5;"));
        lblSuma.setText("SUMA MESA: " + mesa.getSuma() + " / 50");
        lblMazoRestante.setText("MAZO: " + mazo.cartasRestantes());
    }

    private void actualizarManoHumano() {
        handHumanBox.getChildren().clear();
        Jugador humano = jugadores.get(0);
        boolean esTurnoHumano = jugadores.get(turnoActual) == humano;

        for (Carta carta : humano.getMano()) {
            Button botonCarta = new Button(carta.toString());
            boolean jugable = esTurnoHumano && !cartaJugadaEsteTurno && tieneValorValido(carta);
            botonCarta.setStyle(jugable ? ESTILO_CARTA_JUGABLE : ESTILO_CARTA_BLOQUEADA);
            botonCarta.setPrefSize(60, 80);
            botonCarta.setDisable(!jugable);
            botonCarta.setOnAction(e -> jugarCartaHumano(carta));
            handHumanBox.getChildren().add(botonCarta);
        }

        handHumanBox.setDisable(!esTurnoHumano);
        btnTomarCarta.setDisable(!(esTurnoHumano && cartaJugadaEsteTurno));
    }

    private boolean tieneValorValido(Carta carta) {
        for (int valor : carta.valoresPosibles()) {
            if (mesa.getSuma() + valor <= 50) return true;
        }
        return false;
    }

    private void actualizarEstadoTurno() {
        Jugador actual = jugadores.get(turnoActual);
        lblTurno.setText(actual.esMaquina() ? "TURNO DE: " + actual.getNombre() : "TU TURNO");
        lblTurno.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-font-family: 'Courier New'; -fx-text-fill: "
                + (actual.esMaquina() ? "#e8423a;" : "#4caf81;"));
    }

    private void setMensaje(String texto) {
        lblMensaje.setText(texto);
    }

    // ----------------- HU-3: Jugar una carta -----------------

    /**
     * Intenta jugar una carta para el jugador humano. Valida el turno y la
     * regla principal (no superar 50) usando las excepciones propias
     * {@link TurnoInvalidoException} y {@link JugadaInvalidaException}.
     * Si la carta es un As y ambos valores (1 y 10) son válidos, se le
     * pregunta al jugador cuál prefiere usar.
     */
    private void jugarCartaHumano(Carta carta) {
        try {
            Jugador humano = jugadores.get(turnoActual);
            if (humano.esMaquina()) {
                throw new TurnoInvalidoException("No es tu turno.");
            }
            if (cartaJugadaEsteTurno) {
                throw new TurnoInvalidoException("Ya jugaste una carta. Debes tomar una carta del mazo para terminar tu turno.");
            }

            List<Integer> valoresValidos = new ArrayList<>();
            for (int valor : carta.valoresPosibles()) {
                if (mesa.getSuma() + valor <= 50) {
                    valoresValidos.add(valor);
                }
            }
            if (valoresValidos.isEmpty()) {
                throw new JugadaInvalidaException("Esa carta hace superar el 50 en la mesa.");
            }

            int aporte = valoresValidos.size() > 1 ? elegirValorAs(valoresValidos) : valoresValidos.get(0);

            realizarJugada(humano, carta, aporte);
            setMensaje("✓  CARTA JUGADA. AHORA TOMA UNA CARTA DEL MAZO");
        } catch (TurnoInvalidoException | JugadaInvalidaException ex) {
            setMensaje("⚠  " + ex.getMessage());
        }
    }

    /** Pregunta al jugador humano con qué valor jugar un As (1 o 10). */
    private int elegirValorAs(List<Integer> valoresValidos) {
        ChoiceDialog<Integer> dialogo = new ChoiceDialog<>(valoresValidos.get(0), valoresValidos);
        dialogo.setTitle("As");
        dialogo.setHeaderText("¿Con qué valor quieres jugar el As?");
        dialogo.setContentText("Valor:");
        Optional<Integer> resultado = dialogo.showAndWait();
        return resultado.orElse(valoresValidos.get(0));
    }

    /**
     * Lógica común para jugar una carta (usada por el humano y por las
     * máquinas): la quita de la mano, la coloca encima de la pila de la
     * mesa y actualiza la suma con su aporte.
     */
    private void realizarJugada(Jugador jugador, Carta carta, int aporte) {
        jugador.quitarCarta(carta);
        mesa.jugarCarta(carta, aporte);
        cartaJugadaEsteTurno = true;

        actualizarVistaMesa();
        actualizarManoHumano();
        actualizarVistaOponentes();
    }

    // ----------------- HU-4: Tomar una carta del mazo -----------------

    @FXML
    private void tomarCarta() {
        try {
            Jugador humano = jugadores.get(turnoActual);
            if (humano.esMaquina()) {
                throw new TurnoInvalidoException("No es tu turno.");
            }
            if (!cartaJugadaEsteTurno) {
                throw new TurnoInvalidoException("Primero debes jugar una carta.");
            }

            tomarCartaConReciclaje(humano);
            setMensaje("");
            pasarTurno();
        } catch (TurnoInvalidoException ex) {
            setMensaje("⚠  " + ex.getMessage());
        } catch (MazoVacioException ex) {
            setMensaje("⚠  " + ex.getMessage());
            pasarTurno();
        }
    }

    /**
     * Toma una carta del mazo para el jugador dado. Si el mazo está vacío,
     * primero intenta reciclar las cartas de la mesa (todas menos la
     * última jugada, que se deja boca arriba) antes de rendirse. Las
     * cartas en mano de los jugadores nunca se ven afectadas por este
     * reciclaje.
     *
     * @throws MazoVacioException excepción marcada: se propaga si, aun
     *         después de reciclar la mesa, no hay cartas disponibles.
     */
    private void tomarCartaConReciclaje(Jugador jugador) throws MazoVacioException {
        if (mazo.estaVacio()) {
            List<Carta> recicladas = mesa.reciclar();
            if (!recicladas.isEmpty()) {
                mazo.agregarYBarajar(recicladas);
                setMensaje("♻  SE RECICLARON LAS CARTAS DE LA MESA EN EL MAZO");
            }
        }
        Carta nueva = mazo.tomarCarta();
        jugador.agregarCarta(nueva);

        actualizarManoHumano();
        actualizarVistaOponentes();
        lblMazoRestante.setText("MAZO: " + mazo.cartasRestantes());
    }

    // ----------------- Turnos -----------------

    /**
     * Prepara el turno del jugador indicado por {@code turnoActual}. Si el
     * jugador no tiene ninguna jugada válida, se le salta el turno (la
     * eliminación de jugadores no forma parte de esta pantalla). Si el
     * jugador es una máquina, dispara el hilo de decisión
     * ({@link HiloDecisionMaquina}).
     */
    private void comenzarTurno() {
        Jugador actual = jugadores.get(turnoActual);
        cartaJugadaEsteTurno = false;

        actualizarVistaMesa();
        actualizarVistaOponentes();
        actualizarEstadoTurno();

        List<OpcionJugada> opciones = actual.opcionesJugables(mesa.getSuma());
        if (opciones.isEmpty()) {
            setMensaje(actual.getNombre() + " NO TIENE JUGADAS VÁLIDAS Y PASA EL TURNO");
            avanzarTurno();
            return;
        }

        actualizarManoHumano();

        if (actual.esMaquina()) {
            handHumanBox.setDisable(true);
            btnTomarCarta.setDisable(true);

            HiloDecisionMaquina hiloDecision = new HiloDecisionMaquina(opciones, this::onMaquinaDecidioJugada);
            hiloDecision.setDaemon(true);
            hiloDecision.start();
        }
    }

    /** Callback del hilo de decisión: la máquina ya eligió qué carta jugar. */
    private void onMaquinaDecidioJugada(OpcionJugada opcion) {
        Jugador maquina = jugadores.get(turnoActual);
        realizarJugada(maquina, opcion.getCarta(), opcion.getAporte());
        setMensaje(maquina.getNombre() + " JUGÓ " + opcion.getCarta());

        HiloTomaMaquina hiloToma = new HiloTomaMaquina(() -> onMaquinaTomoCarta(maquina));
        hiloToma.setDaemon(true);
        hiloToma.start();
    }

    /** Callback del hilo de toma: la máquina ya "tomó" su carta del mazo. */
    private void onMaquinaTomoCarta(Jugador maquina) {
        try {
            tomarCartaConReciclaje(maquina);
        } catch (MazoVacioException ex) {
            setMensaje("⚠  " + ex.getMessage());
        }
        pasarTurno();
    }

    /** Pasa el turno al siguiente jugador de la rotación (sin saltar a nadie). */
    private void pasarTurno() {
        avanzarTurno();
    }

    private void avanzarTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
        comenzarTurno();
    }
}
