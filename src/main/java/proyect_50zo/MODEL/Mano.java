package proyect_50zo.MODEL;

import java.util.ArrayList;

public class Mano {

    //Atributos de la mano
    private ArrayList<Carta> mano;
    private Mazo mazo= new Mazo();

    //Contructor de una lista de mano
    public Mano() {
        mano = new ArrayList<>();
        crearMano();
    }

    //Crear una mano mediante las primeras 4 cartas del mazo como se
    //realizaria en un verdadero juego
    public ArrayList<Carta> crearMano(){
        for(int i=0; i<4;i++){
            mano.add(mazo.entregarCarta());
        }
        return mano;
    }

    //Metodo diseniado para poder agregar una carta al mazo cuando este sea menor a 4
    // esta carta es la primera del mazo
    public void agregarCartaMano(Carta carta){
        if(mano.size()<4){
            mano.add(mazo.entregarCarta());
        }
        else {
            System.out.println("La mano ya tiene 4 cartas");
        }
    }

    //Metodo getter y setter para la mano
    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }
}
