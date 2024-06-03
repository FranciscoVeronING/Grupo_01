package models;

/**
 * Clase abracta utilizada para poder definir en la superclase los metodos que implementaran las subclases de manera dinamica
 * <b>Post: </b> Cada superclase podra implementar de manera dinamica cada uno de estos metodos definidos en la superclase
 */

public abstract class DecoratorZonas implements IViaje {
    IViaje encapsulado;

    public DecoratorZonas(IViaje encapsulado) {
        this.encapsulado = encapsulado;
    }

    @Override
    public double getCosto_viaje() {
        return encapsulado.getCosto_viaje();
    }

    @Override
    public void setCosto_viaje(double val) {
        encapsulado.setCosto_viaje(val);
    }

    public double getDistancia() {
        return getPedido().getDistancia();
    }

    @Override
    public Pedido getPedido() {
        return encapsulado.getPedido();
    }

    @Override
    public void setPedido(Pedido pedido) {
        encapsulado.setPedido(pedido);
    }

    @Override
    public void setEstado_de_viaje(String estado_de_viaje) {
        encapsulado.setEstado_de_viaje(estado_de_viaje);
    }

    @Override
    public String getEstado_de_viaje() {
        return encapsulado.getEstado_de_viaje();
    }

    @Override
    public Empleado getChofer() {
        return encapsulado.getChofer();
    }

    @Override
    public void setChofer(Empleado chofer) {
        encapsulado.setChofer(chofer);
    }

    @Override
    public IVehiculo getVehiculo() {
        return encapsulado.getVehiculo();
    }

    @Override
    public void setVehiculo(IVehiculo vehiculo) {
        encapsulado.setVehiculo(vehiculo);
    }

    @Override
    public void setCosto_base(double costo_base) {
        encapsulado.setCosto_base(costo_base);
    }

    @Override
    public double getCosto_base() {
        return encapsulado.getCosto_base();
    }

    @Override
    public void calcularCostoViaje() {
        encapsulado.calcularCostoViaje();
    }

    @Override
    public void pagarse() {
        encapsulado.pagarse();
    }

    @Override
    public void finalizarse() {
        encapsulado.finalizarse();
    }

    @Override
    public int compareTo(Object o) {
        return encapsulado.compareTo(o);
    }

    /**
     *<b>Pre: </b> La clase Iviaje debe ser cloneable
     *<b>Post:</b> Se devuelve un nuevo objeto DecoratorZonas que es una copia superficial del objeto actual
     * @return una copia superficial del objeto actual
     * @throws CloneNotSupportedException lanzado en el caso de que la clase base no soporte la clonacion
     */
    @Override
    public IViaje clone() throws CloneNotSupportedException {
        DecoratorZonas clon = (DecoratorZonas) super.clone();
        if (encapsulado != null) clon.encapsulado.clone();
        return clon;
    }
}
