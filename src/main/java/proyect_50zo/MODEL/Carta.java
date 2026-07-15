package proyect_50zo.MODEL;

/**
 * Representa una carta de una baraja de póker estándar (52 cartas: A, 2-10,
 * J, Q, K en los 4 palos).
 * <p>
 * Cada carta define, según las reglas de "El Cincuentazo", el o los valores
 * numéricos que puede aportar a la suma de la mesa:
 * <ul>
 *     <li>2 al 8 y 10: suman su propio número.</li>
 *     <li>9: no suma ni resta (aporta 0).</li>
 *     <li>J, Q, K: restan 10.</li>
 *     <li>A (as): suma 1 o 10, a elección del jugador.</li>
 * </ul>
 */
public class Carta {

    /** Rangos válidos de una baraja de póker. */
    private static final String[] RANGOS_VALIDOS =
            {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    /** Palos válidos de una baraja de póker. */
    private static final String[] PALOS_VALIDOS = {"♠", "♥", "♦", "♣"};

    private final String rango; // "A", "2".."10", "J", "Q", "K"
    private final String palo;  // ♠ ♥ ♦ ♣

    /**
     * Crea una carta.
     *
     * @param rango rango de la carta (A, 2-10, J, Q, K).
     * @param palo  palo de la carta (♠, ♥, ♦, ♣).
     * @throws IllegalArgumentException (excepción no marcada de Java) si el
     *         rango o el palo no corresponden a una baraja de póker válida.
     */
    public Carta(String rango, String palo) {
        if (!esRangoValido(rango)) {
            throw new IllegalArgumentException("Rango de carta inválido: " + rango);
        }
        if (!esPaloValido(palo)) {
            throw new IllegalArgumentException("Palo de carta inválido: " + palo);
        }
        this.rango = rango;
        this.palo = palo;
    }

    private static boolean esRangoValido(String rango) {
        for (String r : RANGOS_VALIDOS) {
            if (r.equals(rango)) return true;
        }
        return false;
    }

    private static boolean esPaloValido(String palo) {
        for (String p : PALOS_VALIDOS) {
            if (p.equals(palo)) return true;
        }
        return false;
    }

    public String getRango() {
        return rango;
    }

    public String getPalo() {
        return palo;
    }

    public boolean esAs() {
        return "A".equals(rango);
    }

    /** Color del palo, útil para pintar la carta en la interfaz. */
    public boolean esRoja() {
        return palo.equals("♥") || palo.equals("♦");
    }

    /**
     * Calcula los valores numéricos que esta carta puede aportar a la suma
     * de la mesa, según las reglas del juego.
     *
     * @return un arreglo con uno o dos valores posibles. Para el As son dos
     *         opciones ({1, 10}); para el resto de las cartas es un único
     *         valor.
     */
    public int[] valoresPosibles() {
        switch (rango) {
            case "9":
                return new int[]{0};
            case "J":
            case "Q":
            case "K":
                return new int[]{-10};
            case "A":
                return new int[]{1, 10};
            default:
                return new int[]{Integer.parseInt(rango)};
        }
    }

    @Override
    public String toString() {
        return rango + palo;
    }
}
