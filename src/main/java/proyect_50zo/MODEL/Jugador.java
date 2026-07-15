package proyect_50zo.MODEL;

import java.util.ArrayList;
import java.util.List;

/**
 * Jugador humano o máquina. Cada jugador mantiene su mano de cartas
 * (4 cartas mientras haya cartas disponibles para reponer, según la
 * preparación del juego).
 */
public class    Jugador {

    private final String nombre;
    private final boolean esMaquina;
    private final List<Carta> mano = new ArrayList<>();

    public Jugador(String nombre, boolean esMaquina) {
        this.nombre = nombre;
        this.esMaquina = esMaquina;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esMaquina() {
        return esMaquina;
    }

    public List<Carta> getMano() {
        return mano;
    }

    public void agregarCarta(Carta carta) {
        if (carta != null) {
            mano.add(carta);
        }
    }

    public void quitarCarta(Carta carta) {
        mano.remove(carta);
    }

    /**
     * Calcula todas las jugadas posibles con la mano actual: para cada
     * carta, cada uno de sus valores posibles que no haga superar 50 la
     * suma de la mesa (regla principal del juego).
     *
     * @param sumaMesa suma actual de la mesa.
     * @return lista de {@link OpcionJugada} disponibles.
     */
    public List<OpcionJugada> opcionesJugables(int sumaMesa) {
        List<OpcionJugada> opciones = new ArrayList<>();
        for (Carta carta : mano) {
            for (int valor : carta.valoresPosibles()) {
                if (sumaMesa + valor <= 50) {
                    opciones.add(new OpcionJugada(carta, valor));
                }
            }
        }
        return opciones;
    }
    private boolean eliminado = false;

    /**
     * HU-5. Indica si el jugador fue eliminado.
     * @return true si el jugador fue eliminado.
     */
    public boolean estaEliminado() {
        return eliminado;
    }

    /**
     * HU-5. Marca el jugador como eliminado.
     */
    public void eliminar() {
        eliminado = true;
    }
}
