package proyect_50zo.MODEL;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la mesa de juego: la pila de cartas jugadas boca arriba
 * (cada carta nueva queda encima de la anterior) y la suma acumulada,
 * que según la regla principal del Cincuentazo no debe superar 50.
 */
public class Mesa {

    private final List<Carta> pila = new ArrayList<>();
    private int suma;

    /**
     * Coloca la carta inicial de la mesa (la que se reparte al preparar
     * la partida) y fija la suma inicial con el valor de esa carta.
     */
    public void colocarCartaInicial(Carta carta, int valorInicial) {
        pila.add(carta);
        suma = valorInicial;
    }

    /**
     * Coloca una carta jugada encima de la pila y actualiza la suma de
     * la mesa con el aporte (positivo, negativo o cero) de esa carta.
     */
    public void jugarCarta(Carta carta, int aporte) {
        pila.add(carta);
        suma += aporte;
    }

    /** @return la carta que está actualmente boca arriba encima de la pila. */
    public Carta getCartaSuperior() {
        return pila.isEmpty() ? null : pila.get(pila.size() - 1);
    }

    public int getSuma() {
        return suma;
    }

    /**
     * Retira de la mesa todas las cartas jugadas <b>excepto la última</b>
     * (que se deja boca arriba como referencia para el siguiente jugador).
     * Se usa cuando el mazo se queda sin cartas para reponerlo. La suma
     * de la mesa no se modifica con esta operación.
     *
     * @return la lista de cartas retiradas, listas para barajarse de
     *         vuelta en el mazo.
     */
    public List<Carta> reciclar() {
        if (pila.size() <= 1) {
            return new ArrayList<>();
        }
        Carta ultima = pila.remove(pila.size() - 1);
        List<Carta> recicladas = new ArrayList<>(pila);
        pila.clear();
        pila.add(ultima);
        return recicladas;
    }
}
