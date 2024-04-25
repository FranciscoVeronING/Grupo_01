package models;

public abstract class Vehiculo implements IVehiculo{   
    protected String patente;
    protected int cant_max_pasajeros;
    protected boolean petfriendly;
    protected boolean baul;


    public Vehiculo(String patente) {
        this.patente = patente;
    }
}
