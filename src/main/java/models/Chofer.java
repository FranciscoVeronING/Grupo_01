package models;

public abstract class Chofer extends Empleado {

protected double sueldo_basico;
protected double aportes;
protected static int cant_calif = 0;
protected static int acum_calif = 0;
protected int calificacion_clientes;


    public Chofer(String dni, String nombre, double aportes) {
        super(dni, nombre);
        this.sueldo_basico = 1000;
        this.aportes = aportes;
        this.calificacion_clientes = 0;
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

    public int getCalificacion_clientes() {
        return calificacion_clientes;
    }

    public void setCalificacion_clientes(int calificacion_clientes) {
        cant_calif++;
        acum_calif += calificacion_clientes;
        this.calificacion_clientes = acum_calif/cant_calif;
    }

    @Override
    public String toString() {
        return " sueldo_basico=" + sueldo_basico +
                ", aportes=" + aportes +
                ", calificacion_clientes=" + calificacion_clientes +
                super.toString();
    }
}
