package models;

import java.util.Date;

public class ChoferPermanente extends Chofer {
    
    private int cant_hijos;
    private Date fecha_ingreso;
    private double plus_x_antiguedad; 
    private double plus_x_hijo;

    public ChoferPermanente(String dni, String nombre, double sueldo_basico, double aportes) {
        super(dni, nombre, sueldo_basico, aportes);
    }


    @Override
    public double getSueldo() {
        // TODO Implement this method
        return 0.0;
    }
}
