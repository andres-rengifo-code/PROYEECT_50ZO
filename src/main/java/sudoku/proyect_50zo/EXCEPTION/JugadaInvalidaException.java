package sudoku.proyect_50zo.EXCEPTION;

/**
 * Excepción <b>no marcada (unchecked)</b> propia del juego.
 * <p>
 * Se lanza cuando se intenta jugar una carta que viola la regla principal
 * del Cincuentazo (la suma de la mesa no debe superar 50) o cuando se
 * intenta aplicar un valor que la carta no puede aportar. Es una
 * {@link RuntimeException} porque representa un error de lógica del
 * programa (la interfaz ya debería impedir que esto ocurra); no se obliga
 * a capturarla en cada punto de la jerarquía de llamadas.
 */
public class JugadaInvalidaException extends RuntimeException {

    public JugadaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
