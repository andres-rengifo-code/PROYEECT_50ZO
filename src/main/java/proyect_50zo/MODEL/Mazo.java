package proyect_50zo.MODEL;

import java.util.ArrayList;
import java.util.Collections;

/** Mazo único, barajado y compartido para la preparación de la partida. */
public class Mazo {
    private final ArrayList<Carta> cartas = new ArrayList<>();

    /**
     * Crea las 52 cartas de una baraja estándar y las baraja.
     */
    public Mazo() {
        String[] palos = {"DIAMANTES", "CORAZONES", "TREBOLES", "PICAS"};
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        for (String palo : palos) for (String valor : valores) cartas.add(new Carta(palo, valor));
        Collections.shuffle(cartas);
    }

    /**
     * Extrae la carta superior del mazo para repartirla durante la preparación.
     *
     * @return carta entregada.
     */
    public Carta entregarCarta() {
        return cartas.remove(0);
    }

    /**
     * Consulta cuántas cartas quedan disponibles en el mazo.
     *
     * @return cantidad de cartas restantes.
     */
    public int cartasRestantes() {
        return cartas.size();
    }
}
