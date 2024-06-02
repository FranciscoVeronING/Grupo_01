package models;
/**
 * Clase que crea el viaje y lo representa en los diferentes momentos, desde que el viaje es solicitado hasta finalizado
 */

public final class Viaje implements IViaje,Cloneable {
    private double costo_base;
    private double costo_viaje;
    private String estado_de_viaje;
    private Pedido pedido;
    private Empleado chofer;
    private IVehiculo vehiculo;


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

    public void setChofer(Empleado chofer) {
        this.chofer = chofer;
    }

    public IVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(IVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public double getCosto_base() {
        return costo_base;
    }

    public void setCosto_base(double costo_base) {
        this.costo_base = costo_base;
    }

    public void calcularCostoViaje() {
        this.costo_viaje = costo_base;
    }

    @Override
    public int compareTo(Object o) {
        Viaje v = (Viaje) o;
        return Double.compare(this.getCosto_viaje(), v.getCosto_viaje()) * -1;
    }

    /**
     * Metodo utilizado para indicar que el viaje finalizo y se libera un chofer
     */
    public void finalizarse() {
        setEstado_de_viaje("FINALIZADO");
    }

    /**
     * Metodo utilizado para indicar que el viaje fue pagado con exito
     */
    public void pagarse() {
        setEstado_de_viaje("PAGADO");
    }

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
    public String toString() {
        return this.getPedido().formatFecha(this.getPedido().getFecha())+" ($ " + costo_viaje + ")" + " (" + this.estado_de_viaje + ") Cliente = " + this.getPedido().getCliente().getNombre_usuario();
    }
}
