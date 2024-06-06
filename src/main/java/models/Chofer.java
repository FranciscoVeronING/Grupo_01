package models;


import java.util.Random;

/**
 * Clase que se extiende de Empleado usada para agregar informacion de los choferes
 */
public abstract class Chofer extends EmpleadoRunnable {

protected double sueldo_basico;
protected double aportes;

    /**
     * Constuctor utilizado para setear el dni,nombre,aportes ALEATORIOS e incicializar el sueldo basico
     * <b>Post: </b> El chofer sera inicializado con sus datos
     */
    public Chofer(BolsaDeViajes b) {
        super(b);
        Random d = new Random();
        this.sueldo_basico = 1000;
        this.aportes = d.nextDouble() *70 + 1;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", sueldo_basico=").append(sueldo_basico);
        sb.append(", aportes=").append(aportes);
        return sb.toString();
    }
}
