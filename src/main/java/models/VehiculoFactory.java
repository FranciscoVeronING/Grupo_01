package models;

public class VehiculoFactory {

    public IVehiculo getVehiculo(String s, String patente) {
        if (s == null) return null;
        if (s.equalsIgnoreCase("MOTO")) return new Moto(patente);
        if (s.equalsIgnoreCase("COMBI")) return new Combi(patente);
        if (s.equalsIgnoreCase("AUTO")) return new Tutu(patente);
        return null;
    }
}
