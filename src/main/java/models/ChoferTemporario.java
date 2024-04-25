package models;

public class ChoferTemporario extends Chofer {
 private double plus_x_cant_viajes;

    public ChoferTemporario(String dni, String nombre, double sueldo_basico, double aportes) {
        super(dni, nombre, sueldo_basico, aportes);
    }


    @Override
    public double getSueldo() {
        double salario = sueldo_basico;
        salario *= (1 + plus_x_cant_viajes * getCant_viajes());
        salario *= (1 - aportes);
        return salario;
    }
}
