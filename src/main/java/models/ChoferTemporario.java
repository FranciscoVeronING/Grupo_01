package models;

public class ChoferTemporario extends Chofer {
 private double plus_x_cant_viajes;

    public ChoferTemporario(String dni, String nombre, double aportes, double plusCantViajes) {
        super(dni, nombre, aportes);
        this.plus_x_cant_viajes = plusCantViajes;
    }


    @Override
    public double getSueldo() {
        double salario = sueldo_basico;
        salario *= (1 + plus_x_cant_viajes * getCant_viajes());
        salario *= (1 - aportes);
        return salario;
    }

    @Override
    public String toString() {
        return "ChoferTemporario {" + super.toString() +
                ", plus_x_cant_viajes=" + plus_x_cant_viajes +
                " }" ;
    }
}
