package sudoku.proyect_50zo.HILO;

import javafx.application.Platform;

import java.util.Random;

/**
 * Hilo que simula el tiempo que tarda un jugador máquina en tomar una
 * carta del mazo después de jugar (criterio de aceptación HU-4: entre 2
 * y 4 segundos). Igual que {@link HiloDecisionMaquina}, corre en su
 * propio {@link Thread} y avisa al controlador mediante
 * {@link Platform#runLater} para actualizar la interfaz de forma segura.
 */
public class HiloTomaMaquina extends Thread {

    private final Runnable alTerminar;
    private final Random random = new Random();

    public HiloTomaMaquina(Runnable alTerminar) {
        super("HiloTomaMaquina");
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
        Platform.runLater(alTerminar);
    }
}
