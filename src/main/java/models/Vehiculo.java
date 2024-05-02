package models;


import Exception.PedidoIncoherenteException;

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

    public boolean validarBaul(Pedido pedido) {
        if (this.baul) return true;
        if (!this.baul && pedido.isEquipaje()) return false;
        else return true;
    }

    public boolean validarPF(Pedido pedido) {
        if (this.petfriendly) return true;
        if (!this.petfriendly && pedido.isMascota()) return false;
        else return true;
    }

    public boolean validarPax(Pedido pedido) {
        return this.cant_max_pasajeros >= pedido.getCant_pasajeros();
    }

    public boolean validarVehiculo(Pedido pedido) {
        boolean petFriendly = validarPF(pedido);
        boolean baul = validarBaul(pedido);
        boolean pax = validarPax(pedido);
        if (petFriendly && baul && pax) {
            return petFriendly && baul && pax;
        }else return throw new PedidoIncoherenteException("El pedido es incoherente");
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

    @Override
    public String toString() {
        return "{" +
                "patente='" + patente + '\'' +
                ", cant_max_pasajeros=" + cant_max_pasajeros +
                ", petfriendly=" + petfriendly +
                ", baul=" + baul +
                '}';
    }
}
