package models;

public class Combi extends Vehiculo {

    public Combi(String patente) {
        super(patente);
        this.setBaul();
        this.setPetfriendly();
        this.setCant_max_pasajeros();
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getCant_max_pasajeros() {
        return cant_max_pasajeros;
    }

    public void setCant_max_pasajeros() {
        this.cant_max_pasajeros = 10;
    }

    public boolean isPetfriendly() {
        return petfriendly;
    }

    public void setPetfriendly() {
        this.petfriendly = false;
    }

    public boolean isBaul() {
        return baul;
    }

    public void setBaul() {
        this.baul = true;
    }
    @Override
    public int getPrioridad(Pedido pedido) {
        // TODO Implement this method
        return 0;
    }
}
