package models;

public class ChoferContratado extends Empleado {
    private double ganancia_viaje;
    
    
    public ChoferContratado(String string, String string1) {
        super(string, string1);
    }

    @Override
    public double getSueldo() {
        // TODO Implement this method
        return 0.0;
    }
}
