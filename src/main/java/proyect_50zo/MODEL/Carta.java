package proyect_50zo.MODEL;

/** Carta de una baraja estándar. */
public class Carta {
    
    private final String palo;
    private final String valor;

    /**
     * Construye una carta identificada por su palo y valor.
     *
     * @param palo palo de la carta.
     * @param valor valor de la carta, por ejemplo {@code A}, {@code 7} o {@code K}.
     */
    public Carta(String palo, String valor) {
        this.palo = palo;
        this.valor = valor;
    }

    /**
     * Indica si la carta pertenece a un palo rojo.
     *
     * @return {@code true} para corazones o diamantes.
     */
    public boolean esRoja() {
        return "CORAZONES".equals(palo) || "DIAMANTES".equals(palo);
    }

    /**
     * Devuelve la representación visible de la carta, con su símbolo de palo.
     *
     * @return texto de la carta, por ejemplo { A♠}.
     */
    @Override
    public String toString() {
        String simbolo = switch (palo) {
            case "CORAZONES" -> "♥";
            case "DIAMANTES" -> "♦";
            case "TREBOLES" -> "♣";
            default -> "♠";
        };
        return valor + simbolo;
    }
}
