package models;

/**
 * Clase que se extiende de Empleado usada para agregar informacion de los choferes
 */
public abstract class Chofer extends Empleado {

protected double sueldo_basico;
protected double aportes;

    /**
     * Constuctor utilizado para setear el dni,nombre,aportes,sueldo basico e incicializar la calificaion
     * <b>Pre: </b> dni no puede ser null ni vacio
     * @param dni : Parametro que es utilizado para setear el dni del chofer
     * <b>Pre: </b> nombre no puede ser null ni vacio
     * @param nombre : Parametro que es utilizado para setear el nombre del chofer
     * <b>Pre: </b> dni no puede ser menor a cero
     * @param aportes : Parametro que es utilizado para setear los aportes del chofer
     */
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", sueldo_basico=").append(sueldo_basico);
        sb.append(", aportes=").append(aportes);
        return sb.toString();
    }
}
