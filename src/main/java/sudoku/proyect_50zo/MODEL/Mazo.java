package sudoku.proyect_50zo.MODEL;

import sudoku.proyect_50zo.EXCEPTION.MazoVacioException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Mazo de cartas boca abajo del que los jugadores toman cartas.
 * <p>
 * Se construye como una baraja de póker estándar de 52 cartas (13 rangos
 * x 4 palos). Administra tanto la entrega de cartas (HU-4) como el
 * reciclaje de la mesa cuando el mazo se queda sin cartas.
 */
public class Mazo {

    private final Deque<Carta> cartas = new ArrayDeque<>();

    public Mazo() {
        construir();
        barajar();
    }

    private void construir() {
        String[] rangos = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] palos = {"♠", "♥", "♦", "♣"};
        for (String palo : palos) {
            for (String rango : rangos) {
                cartas.add(new Carta(rango, palo));
            }
        }
    }

    public void barajar() {
        List<Carta> lista = new ArrayList<>(cartas);
        Collections.shuffle(lista);
        cartas.clear();
        cartas.addAll(lista);
    }

    /**
     * Toma la carta de encima del mazo.
     *
     * @return la carta tomada.
     * @throws MazoVacioException excepción marcada propia: se lanza si el
     *         mazo está vacío. Quien llama a este método debe intentar
     *         primero reciclar la mesa (ver {@link Mesa#reciclar()} y
     *         {@link #agregarYBarajar(List)}) antes de que esta excepción
     *         se considere un verdadero fin de cartas disponibles.
     */
    public Carta tomarCarta() throws MazoVacioException {
        Carta carta = cartas.poll();
        if (carta == null) {
            throw new MazoVacioException("No quedan cartas disponibles en el mazo.");
        }
        return carta;
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public int cartasRestantes() {
        return cartas.size();
    }

    /**
     * Incorpora un grupo de cartas al mazo y vuelve a barajar todo el
     * conjunto. Se usa cuando el mazo se queda sin cartas y se reciclan
     * las cartas de la mesa (según las reglas del juego, la suma de la
     * mesa no se ve afectada por este reciclaje).
     */
    public void agregarYBarajar(List<Carta> nuevasCartas) {
        if (nuevasCartas == null || nuevasCartas.isEmpty()) return;
        List<Carta> lista = new ArrayList<>(cartas);
        lista.addAll(nuevasCartas);
        Collections.shuffle(lista);
        cartas.clear();
        cartas.addAll(lista);
    }
}
