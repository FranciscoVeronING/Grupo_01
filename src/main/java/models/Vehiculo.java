package models;


public abstract class Vehiculo implements IVehiculo{   
    protected String patente;
    protected int cant_max_pasajeros;
    protected boolean petfriendly;
    protected boolean baul;
    
    public Vehiculo(String patente, int cantPax, boolean PF, boolean baul) {
        this.patente = patente;
        this.cant_max_pasajeros = cantPax;
        this.petfriendly = PF;
        this.baul = baul;
    }

    public boolean validarVehiculo(Pedido pedido) {
        /* validar con patron template
        Verifica cantidad Pasajeros
        Verifica Uso de Baul
        Verifica Transporte de Mascota
        */
        return true;
    }

    public String getPatente() {
        return patente;
    }

    public int getCant_max_pasajeros() {
        return cant_max_pasajeros;
    }

    public boolean isPetfriendly() {
        return petfriendly;
    }

    public boolean isBaul() {
        return baul;

    }
}
