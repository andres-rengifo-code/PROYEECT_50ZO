package sudoku.proyect_50zo.MODEL;

/**
 * Representa una posible jugada: una carta de la mano de un jugador junto
 * con uno de los valores numéricos que esa carta puede aportar a la mesa
 * sin superar 50. Para la mayoría de las cartas existe una sola
 * {@code OpcionJugada}; para el As pueden existir dos (aporte 1 o 10).
 */
public class OpcionJugada {

    private final Carta carta;
    private final int aporte;

    public OpcionJugada(Carta carta, int aporte) {
        this.carta = carta;
        this.aporte = aporte;
    }

    public Carta getCarta() {
        return carta;
    }

    public int getAporte() {
        return aporte;
    }
}
