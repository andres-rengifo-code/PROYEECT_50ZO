package sudoku.proyect_50zo.EXCEPTION;

/**
 * Excepción <b>marcada (checked)</b> propia del juego.
 * <p>
 * Se lanza cuando se necesita tomar una carta del mazo pero no hay cartas
 * disponibles ni siquiera después de intentar reciclar las cartas de la
 * mesa (todas las cartas jugadas menos la última). Al ser una excepción
 * marcada, obliga a quien la invoca a manejarla explícitamente (try/catch
 * o declarar throws), ya que representa una condición del juego que debe
 * resolverse antes de continuar (por ejemplo, terminar la partida).
 */
public class MazoVacioException extends Exception {

    public MazoVacioException(String mensaje) {
        super(mensaje);
    }
}
