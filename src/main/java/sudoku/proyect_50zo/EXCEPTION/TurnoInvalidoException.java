package sudoku.proyect_50zo.EXCEPTION;

/**
 * Excepción <b>no marcada (unchecked)</b> propia del juego.
 * <p>
 * Se lanza cuando una acción (jugar carta, tomar carta) se intenta
 * ejecutar fuera de turno o cuando el jugador ya jugó una carta y aún
 * no ha tomado la carta correspondiente del mazo (o viceversa).
 */
public class TurnoInvalidoException extends RuntimeException {

    public TurnoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
