package proyect_50zo.MODEL;

import java.util.ArrayList;
import java.util.List;

/** Participante humano o máquina con una mano inicial. */
public class Jugador {
    private final String nombre;
    private final boolean maquina;
    private final List<Carta> mano = new ArrayList<>();

    /**
     * Crea un participante de la partida.
     *
     * @param nombre nombre que se mostrará en la interfaz.
     * @param maquina {@code true} si el participante es una máquina.
     */
    public Jugador(String nombre, boolean maquina) {
        this.nombre = nombre; this.maquina = maquina;
    }

    /** @return nombre visible del participante. */
    public String getNombre() {
        return nombre;
    }

    /** @return true cuando el participante es una máquina. */
    public boolean esMaquina() {
        return maquina;
    }

    /** @return cartas recibidas durante el reparto inicial. */
    public List<Carta> getMano() {
        return mano;
    }

    /**
     * Agrega una carta a la mano inicial del participante.
     *
     * @param carta carta entregada por el mazo de la meza.
     */
    public void recibirCarta(Carta carta) {
        mano.add(carta);
    }
}
