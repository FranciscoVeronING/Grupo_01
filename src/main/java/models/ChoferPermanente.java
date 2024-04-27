package models;

import java.util.Date;

public class ChoferPermanente extends Chofer {
    
    private int cant_hijos;
    private Date fecha_ingreso;
    private double plus_x_antiguedad; 
    private double plus_x_hijo;

    public ChoferPermanente(String dni, String nombre, double aportes, double plusA, double plusH) {
        super(dni, nombre, aportes);
        this.plus_x_antiguedad = plusA;
        this.plus_x_hijo = plusH;
        this.cant_hijos = 0; // CAMBIAR
    }


    @Override
    public double getSueldo() {
        double salario = this.sueldo_basico;
        salario += sueldo_basico * this.plus_x_antiguedad; // antiguedad va por año o total?
        salario += sueldo_basico * (plus_x_hijo * cant_hijos); // hijos
        salario *= (1-aportes);
        return salario;
    }

    @Override
    public String toString() {
        return "ChoferPermanente {" +
                " cant_hijos=" + cant_hijos +
                ", fecha_ingreso=" + fecha_ingreso +
                ", plus_x_antiguedad=" + plus_x_antiguedad +
                ", plus_x_hijo=" + plus_x_hijo +
                super.toString();
    }
}
