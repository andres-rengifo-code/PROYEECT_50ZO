package proyect_50zo.MODEL;

import java.util.ArrayList;

public class Mano {

    private ArrayList<Carta> mano;

    public Mano() {
        mano = new ArrayList<>();
        crearMano();
    }

    public ArrayList<Carta> crearMano(){
        for(int i=0; i<4;i++){
            mano.add(new Carta());
        }
        return mano;
    }

    public void agregarCartaMano(Carta carta){
        if(mano.size()<4){
            mano.add(carta);
        }
        else {
            System.out.println("La mano ya tiene 4 cartas");
        }
    }
}
