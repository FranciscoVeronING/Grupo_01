package models;

public abstract class Chofer extends Empleado {

protected double sueldo_basico;
protected double aportes;


    public Chofer(String dni, String nombre, double sueldo_basico, double aportes) {
        // TODO Implement this method
        super(dni, nombre);
        this.aportes = aportes;
        this.sueldo_basico = sueldo_basico;
    }


    protected void setSueldo_basico(double sueldo_basico) {
        this.sueldo_basico = sueldo_basico;
    }

    protected double getSueldo_basico() {
        return sueldo_basico;
    }

    protected void setAportes(double aportes) {
        this.aportes = aportes;
    }

    protected double getAportes() {
        return aportes;
    }

}
