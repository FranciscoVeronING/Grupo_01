package models;

import java.util.ArrayList;

public class ChoferContratado extends Empleado {
    private double ganancia_viaje;
    
    
    public ChoferContratado(String string, String string1) {
        super(string, string1);
    }

    @Override
    public double getSueldo() {
        double salario = 0;
        Empresa e = Empresa.getInstancia();
        ArrayList<Viaje> viajes = e.getViajesChofer(this);
        for (Viaje viaje : viajes) {
            salario += viaje.getCosto_viaje() * ganancia_viaje;
        }
        return salario;
    }
}
