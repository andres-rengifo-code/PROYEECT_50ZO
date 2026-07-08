package proyect_50zo.MODEL;

public class Carta {

    //Atributos de una carta
    private String palo;
    private String valor;

    //Contructor
    //Datos necesarios: palo y valor
    public Carta(String palo, String valor) {
        this.palo = palo;
        this.valor = valor;
    }


    //Getter and setter
    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
