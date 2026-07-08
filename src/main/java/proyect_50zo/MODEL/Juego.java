package proyect_50zo.MODEL;

import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;
import java.util.ArrayList;

public class Juego {

    //Atributo de tipo lista para generar una lsta de jugadores
    private ArrayList<Integer> listaJugadores = new ArrayList<>();

    //Inicializa la lista brindando un id de (0,1, 2, 3)
    public Juego() {
        for(int i = 0 ; i<4 ; i++){
            listaJugadores.add(i);
        }
    }
    //Metodo para rotar turnos cada que se llama el primer atributo que esta en la lista pasa a ser
    // el ultimo en la ista
    public void rotacionTurnos(){
            listaJugadores.add(listaJugadores.remove(0));

    }



}
