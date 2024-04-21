package models;

public class ChoferTemporario extends Chofer {
 private double plus_x_cant_viajes;

    public ChoferTemporario(String dni, String nombre, double sueldo_basico, double aportes) {
        super(dni, nombre, sueldo_basico, aportes);
    }


    @Override
    public double getSueldo() {
        // TODO Implement this method
        return 0.0;
    }
}
