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

    public boolean validarBaul(Pedido pedido)throws PedidoIncoherenteException {
        if (this.baul) return true;
        if (!this.baul && pedido.isEquipaje())
            throw new PedidoIncoherenteException("El pedido fue rechazado por baul");
        else
            return true;
    }

    public boolean validarPF(Pedido pedido) throws PedidoIncoherenteException{
        if (this.petfriendly) return true;
        if (!this.petfriendly && pedido.isMascota())
            throw new PedidoIncoherenteException("El pedido fue rechazado por petfriendly");
        else
            return true;
    }

    public boolean validarPax(Pedido pedido) throws PedidoIncoherenteException{
        if(this.cant_max_pasajeros < pedido.getCant_pasajeros())
            throw new PedidoIncoherenteException("El pedido fue rechazado por cantidad de pasajeros");
        else
            return true;
    }

    public boolean validarVehiculo(Pedido pedido) {
        try {
            boolean petFriendly = validarPF(pedido);
            boolean baul = validarBaul(pedido);
            boolean pax = validarPax(pedido);
            return petFriendly && baul && pax;
        }
        catch (PedidoIncoherenteException e) {
            System.out.println(e.getMessage());
            return false;
        }
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
