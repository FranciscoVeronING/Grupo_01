package models;

import java.io.Serializable;

public class BolsaDeViajes implements Serializable {
    private ArrayList<Viaje> colaDeViajes = new ArrayList<Viaje>();

    public void agregarViaje(Viaje viaje) {
        try {
            colaDeViajes.add(viaje);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Viaje obtenerViaje() {
        try {
            return colaDeViajes.getFirst();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
}
