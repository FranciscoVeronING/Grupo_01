package models;

/**
 * Clase que se extiende de Empleado usada para agregar informacion de los choferes
 */
public abstract class Chofer extends Empleado {

protected double sueldo_basico;
protected double aportes;
protected static int cant_calif = 0;
protected static int acum_calif = 0;
protected int calificacion_clientes;

    /**
     * Constuctor utilizado para setear el dni,nombre,aportes,sueldo basico e incicializar la calificaion
     * <b>Pre: </> dni no puede ser null ni vacio
     * @param dni Parametro que es utilizado para setear el dni del chofer
     * <b>Pre: </> nombre no puede ser null ni vacio
     * @param nombre Parametro que es utilizado para setear el nombre del chofer
     * <b>Pre: </> dni no puede ser menor a cero
     * @param aportes Parametro que es utilizado para setear los aportes del chofer
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

    public int getCalificacion_clientes() {
        return calificacion_clientes;
    }

    /**
     * Metodo que indica cuando un viaje finalizo
     * @param viaje Parametro que contiende informacion a cerca del viaje que esta realizando el chofer
     * <b>Post: </> Se indicara el viaje como finalizado
     */
    public void finalizaViaje(Viaje viaje){
        viaje.setEstado_de_viaje("finalizado");
    }

    /**
     *Metodo utilizado para setear el promedio de la calificacion del chofer segun los clientes
     * <b>Pre: </> La calificacion del cliente debe ser mayor a 0
     * @param calificacion_clientes Es la calificacion que le da el cliente al chofer luego del viaje
     */
    public void setCalificacion_clientes(int calificacion_clientes) {
        cant_calif++;
        acum_calif += calificacion_clientes;
        this.calificacion_clientes = acum_calif/cant_calif;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", sueldo_basico=").append(sueldo_basico);
        sb.append(", aportes=").append(aportes);
        sb.append(", calificacion_clientes=").append(calificacion_clientes);
        return sb.toString();
    }
}
