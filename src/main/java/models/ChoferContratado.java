package models;

import java.util.ArrayList;

public class ChoferContratado extends Empleado {
    private double ganancia_viaje;
    
    
    public ChoferContratado(String string, String string1, double ganancia) {
        super(string, string1);
        this.ganancia_viaje = ganancia;
    }

    @Override
    public double getSueldo() {
        double salario = 0;
        Sistema e = Sistema.getInstancia();
        ArrayList<IViaje> viajes = e.getViajesChofer(this);
        for (IViaje viaje : viajes) {
            salario += viaje.getCosto_viaje() * ganancia_viaje;
        }
        return salario;
    }

    @Override
    public String toString() {
        return "ChoferContratado {" +
                " ganancia_viaje=" + ganancia_viaje +
                super.toString();
    }
}
