package models;

public class VehiculoFactory {
    public static IVehiculo getVehiculo(String vehiculo,String patente) {
        IVehiculo encapsulado = null;
        if (vehiculo.equalsIgnoreCase("auto"))
            encapsulado = new Tutu(patente);
        else if (vehiculo.equalsIgnoreCase("combi")) {
            encapsulado = new Combi(patente);
        }
        else if (vehiculo.equalsIgnoreCase("moto")) {
            encapsulado = new Moto(patente);
        }
        return encapsulado;
    }
}
