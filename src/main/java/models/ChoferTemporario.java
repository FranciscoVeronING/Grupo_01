package models;

import java.util.GregorianCalendar;
import java.util.Iterator;

public class ChoferTemporario extends Chofer {
 private double plus_x_cant_viajes;

    public ChoferTemporario(String dni, String nombre, double aportes, double plusCantViajes) {
        super(dni, nombre, aportes);
        this.plus_x_cant_viajes = plusCantViajes;
    }


    @Override
    public double getSueldo(GregorianCalendar fecha_inicio_mes) {
        double salario = sueldo_basico;
        int cant_viajes = 0;
        Sistema e = Sistema.getInstancia();
        Iterator<IViaje> viajes = e.getViajesChofer(this);
        while (viajes.hasNext() && viajes.next().getPedido().getFecha().compareTo(fecha_inicio_mes) > 0)
            cant_viajes++;
        salario *= (1 + plus_x_cant_viajes * cant_viajes);
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
