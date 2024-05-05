package models;

import java.util.GregorianCalendar;

/**
 * Clase que crea el viaje y lo representa en los diferentes momentos, desde que el viaje es solicitado hasta finalizado
 */

public class Viaje implements IViaje,Cloneable, Comparable {
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
     * Metodo que se utiliza para consultar el estado del viaje
     * <b>Pre:</> El parametro no puede ser null ni vacio
     * @param estado_de_viaje El parametro debe guardar el estado del viaje
     */
    public void setEstado_de_viaje(String estado_de_viaje) {
        if (estado_de_viaje.equalsIgnoreCase("Solicitado") || estado_de_viaje.equalsIgnoreCase("iniciado") || estado_de_viaje.equalsIgnoreCase("pagado") || estado_de_viaje.equalsIgnoreCase("finalizado"))
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

    public void calcularCostoViaje() {
        this.costo_viaje = costo_base;
    }

    public double getCosto_base() {
        return costo_base;
    }

    public void setCosto_base(double costo_base) {
        this.costo_base = costo_base;
    }


    @Override
    public int compareTo(Object o) {
        Viaje v = (Viaje) o;
        return Double.compare(this.getCosto_viaje(), v.getCosto_viaje()) * -1;
    }

    @Override
    public String toString() {
        return this.getPedido().formatFecha(this.getPedido().getFecha())+" ($ " + costo_viaje + ")";
    }

    /**
     * Metodo utilizado para indicar que el viaje finalizo y se libera un chofer
     */
    public void finalizarse() {
        Empleado chofer = getChofer();
        setEstado_de_viaje("finalizado");
        chofer.setOcupado(false);
        chofer.setCant_viajes();
    }

    /**
     * Metodo utilizado para indicar que el viaje fue pagado con exito
     */
    public void pagarse() {
        setEstado_de_viaje("pagado");
    }

    @Override
    public Viaje clone() {
        try {
            Viaje clone = (Viaje) super.clone();
            clone.chofer = (Empleado)this.chofer.clone();
            clone.pedido = (Pedido) this.pedido.clone();
            clone.vehiculo = (IVehiculo) this.vehiculo.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
