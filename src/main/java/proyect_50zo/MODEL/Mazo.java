package proyect_50zo.MODEL;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

    private ArrayList<Carta> mazo;

    public Mazo() {
        mazo = new ArrayList<>();
        crearMazo();
        barajar();
    }



    public void crearMazo(){

        String[] palos={"DIAMATES","CORAZONES","TREBOLES","PICAS",};
        String[] valores={"A","1","2","3","4","5","6","7","8","9","10","J","Q","K"};

        for(String palo: palos){
            for(String valor: valores){
                mazo.add(new Carta(palo, valor));
            }
        }
    }


    public void barajar() {
        for (int i = 0; i < 2; i++) {
            Collections.shuffle(mazo);
        }
    }

    public Carta entregarCarta(){
        if(mazo.isEmpty()){
            return null;
        }
        return mazo.remove(0);
    }
}
