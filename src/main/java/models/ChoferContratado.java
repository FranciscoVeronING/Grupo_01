package models;

import java.util.ArrayList;
import java.util.Iterator;

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
        Iterator<IViaje> viajes = e.getViajesChofer(this);
        for (Iterator<IViaje> it = viajes; it.hasNext(); ) {
            IViaje viaje = it.next();
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
