package models;


import java.io.Serializable;
import java.util.Random;

/**
 * Clase que se extiende de Empleado usada para agregar informacion de los choferes
 */
public abstract class Chofer extends EmpleadoRunnable implements Serializable {

protected double sueldo_basico;
protected double aportes;

    /**
     * Constuctor utilizado para setear el dni,nombre,aportes ALEATORIOS e incicializar el sueldo basico
     * <b>Post: </b> El chofer sera inicializado con sus datos
     */
    // TODO = Borrar cuando se haya creado el campo de viajes max por chofer
    public Chofer(BolsaDeViajes b) {
        super(b);
        Random d = new Random();
        this.sueldo_basico = 1000;
        this.aportes = d.nextDouble() *70 + 1;
    }

    /* TODO = Reemplazo por el de arriba
    * public Chofer(BolsaDeViajes b, int max) {
        super(b, max);
        Random d = new Random();
        this.sueldo_basico = 1000;
        this.aportes = d.nextDouble() *70 + 1;
    }
    * */

    public Chofer() {
    }

    public void setSueldo_basico(double sueldo_basico) {
        this.sueldo_basico = sueldo_basico;
    }

    public double getSueldo_basico() {
        return sueldo_basico;
    }

    public void setAportes(double aportes) {
        this.aportes = aportes;
    }

    public double getAportes() {
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
