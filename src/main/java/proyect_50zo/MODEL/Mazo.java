package proyect_50zo.MODEL;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

    //Lista de cartas
    private ArrayList<Carta> mazo = new ArrayList<>();

    //Generamos el mazo desordenado
    public Mazo() {
        crearMazo();
        barajar();
    }



    //creamos un mazo ordenado con objetos tipo carta
    public void crearMazo(){

        String[] palos={"DIAMATES","CORAZONES","TREBOLES","PICAS",};
        String[] valores={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

        for(String palo: palos){
            for(String valor: valores){
                mazo.add(new Carta(palo, valor));
            }
        }
    }


    //Barajeo las cartas dos veces
    public void barajar() {
        for (int i = 0; i < 2; i++) {
            Collections.shuffle(mazo);
        }
    }

    //Entrego la primera carta del mazo
    public Carta entregarCarta(){
        if(mazo.isEmpty()){
            return null;
        }
        return mazo.remove(0);
    }

    public int extraerValorCarta(Carta carta){
        switch (carta.getValor()){
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 0;
            case "10":
                return 10;
            case "J", "Q", "K":
                return -10;
            default:
                return 0;
        }

    }

    public int extraerValorAs(Carta carta, int valorEleccion){
        if(carta.getValor().equals("A") && valorEleccion==1){
            return valorEleccion;
        }
        if (carta.getValor().equals("A") && valorEleccion==10) {
            return valorEleccion;
        }
        return valorEleccion;
    }



}
