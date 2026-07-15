package proyect_50zo.HILO;

import javafx.application.Platform;
import proyect_50zo.MODEL.OpcionJugada;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Hilo que simula el tiempo que tarda un jugador máquina en decidir qué
 * carta jugar (criterio de aceptación HU-3: entre 2 y 4 segundos).
 * <p>
 * Se ejecuta en un {@link Thread} independiente del hilo de la interfaz
 * gráfica de JavaFX para no congelar la UI mientras "piensa"; al terminar,
 * entrega el resultado al controlador mediante {@link Platform#runLater}
 * para que la actualización de la interfaz sea segura.
 */
public class HiloDecisionMaquina extends Thread {

    private final List<OpcionJugada> opcionesDisponibles;
    private final Consumer<OpcionJugada> alTerminar;
    private final Random random = new Random();

    public HiloDecisionMaquina(List<OpcionJugada> opcionesDisponibles, Consumer<OpcionJugada> alTerminar) {
        super("HiloDecisionMaquina");
        this.opcionesDisponibles = opcionesDisponibles;
        this.alTerminar = alTerminar;
    }

    @Override
    public void run() {
        try {
            double segundos = 2 + random.nextDouble() * 2; // entre 2.0 y 4.0
            Thread.sleep((long) (segundos * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        OpcionJugada elegida = opcionesDisponibles.get(random.nextInt(opcionesDisponibles.size()));
        Platform.runLater(() -> alTerminar.accept(elegida));
    }
}
