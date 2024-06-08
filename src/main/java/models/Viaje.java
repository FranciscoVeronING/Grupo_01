package models;
/**
 * Clase que representa el viaje con sus costos,estados,chofer y vehiculo
 * Implementa la interfaz viaje y cloneable
 */

public final class Viaje implements IViaje,Cloneable {
    private double costo_base;
    private double costo_viaje;
    private String estado_de_viaje;
    private Pedido pedido;
    private Empleado chofer;
    private IVehiculo vehiculo;

    /**
     * Constructor utilizado para la incializacion de algunos datos sobre el viaje segun el pedido del cliente
     * @param pedido Parametro que almacena la informacion sobre el viaje solicitado
     * <b>Post:</b> El viaje habra sido solicitado
     */
    public Viaje(Pedido pedido) {
        this.estado_de_viaje = "Solicitado";
        this.pedido = pedido;
        this.costo_base = 1000;
    }

    public double getCosto_viaje() {
        return costo_viaje;
    }

    @Override
    public void setCosto_viaje(double costo_viaje) {
        this.costo_viaje = costo_viaje;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Metodo que se utiliza para cambiar el estado del viaje
     * <b>Pre:</b> El parametro no puede ser null ni vacio
     * @param estado_de_viaje El parametro debe guardar el estado del viaje
     * <b>Post:</b> El estado del viaje habra sido actualizado a su nuevo estado
     */
    public void setEstado_de_viaje(String estado_de_viaje) {
        this.estado_de_viaje = estado_de_viaje;
    }

    public String getEstado_de_viaje() {
        return estado_de_viaje;
    }
  
    public Empleado getChofer() {
        return chofer;
  }

    /**
     * Asigna un chofer al viaje
     *<b>Pre:</b> El chofer no puede ser null ni estar vacio
     * @param chofer Parametro que representa el chofer que se le asigno al viaje
     * <b>Post:</b>El viaje tendra un chofer asignado
     */
    public void setChofer(Empleado chofer) {
        this.chofer = chofer;
    }

    public IVehiculo getVehiculo() {
        return vehiculo;
    }
    /**
     * Asigna un vehiculo al viaje
     *<b>Pre:</b> El vehiculo no puede ser null ni estar vacio
     * @param vehiculo Parametro que representa elvehiculo que se le asigno al viaje
     * <b>Post:</b>El viaje tendra un vehiculo asignado
     */
    public void setVehiculo(IVehiculo vehiculo) {
        this.vehiculo = vehiculo;
        vehiculo.setOcupado(true);
    }

    public double getCosto_base() {
        return costo_base;
    }
    /**
     * Asigna un costo base al viaje
     *<b>Pre:</b> El costo base no puede ser negativo
     * @param costo_base Parametro que guarda el costo base del viaje
     * <b>Post:</b>El viaje tendra un costo base asignado
     */
    public void setCosto_base(double costo_base) {
        this.costo_base = costo_base;
    }

    public void calcularCostoViaje() {
        this.costo_viaje = costo_base;
    }

    /**
     * Metodo utilizado para cambiar el estado del viaje una vez que se termino
     * <b>Post:</b> El viaje habra finalizado con el estado del mismo actualizado
     */
    public void finalizarse() {
        setEstado_de_viaje("FINALIZADO");
        vehiculo.setOcupado(false);
    }

    /**
     * Metodo utilizado para indicar que el viaje fue pagado con exito
     * <b>Post:</b> El viaje habra finalizado con el estado del mismo actualizado
     */
    public void pagarse() {
        setEstado_de_viaje("PAGADO");
    }

    /**
     * <b>Pre: </b> La clase Viaje debe implementar cloneable
     * <b>Post:</b> El metodo devolvera una instancia clonada de objeto original
     * @return Devolvera una copia del objeto Viaje
     * @throws AssertionError se lanzara la excepcion en caso de que la clase no implemente cloneable (lo cual no deberia suceder)
     */
    @Override
    public Viaje clone() {
        try {
            Viaje clone = (Viaje) super.clone();
            if (this.chofer != null)
                clone.chofer =  this.chofer.clone();
            if (this.pedido != null)
                clone.pedido =  this.pedido.clone();
            if (this.vehiculo != null)
                clone.vehiculo =  this.vehiculo.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int compareTo(Object o) {
        Viaje v = (Viaje) o;
        return Double.compare(this.getCosto_viaje(), v.getCosto_viaje()) * -1;
    }

    @Override
    public Viaje getViaje() {
        return this;
    }

    @Override
    public String toString() {
        return Utiles.formatFecha(this.getPedido().getFecha())+" ($ " + costo_viaje + ")" + " (" + this.estado_de_viaje + ") Cliente = " + this.getPedido().getCliente().getNombre_usuario();
    }
}
