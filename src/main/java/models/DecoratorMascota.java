package models;
/**
 * Clase utilizada para agregar metodos del viaje a cerca de la mascota de manera dinamica
 */
public class DecoratorMascota implements IViaje {
    IViaje encapsulado;

    /**
     * Metodo utilizado para generar el encapsulado del metodo decorator
     * <b>Pre: </b> El parametro no puede ser null ni estar vacio
     * @param encapsulado : Encapsula la informacion del viaje seteada anteriormente con la interfaz IViaje
     */
    public DecoratorMascota(IViaje encapsulado) {
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

    /***
     * Metodo utilizado para calcular el costo de viaje solicitado con el requerimiento de si llega a necesitar un vehiculo pet friendly
     * <b>Post:</b> El costo del viaje sera incrementado segun la necesidad del vehiculo pet friendly solictiado por el usuario
     */
    @Override
    public void calcularCostoViaje() {
        encapsulado.calcularCostoViaje();
        double base = encapsulado.getCosto_viaje();
        double aumentoPax = getCosto_base() * getPedido().getCant_pasajeros() * 0.1;
        double aumentoKm = getCosto_base() * getDistancia() * 0.2;
        encapsulado.setCosto_viaje(base + aumentoKm + aumentoPax);
    }

    @Override
    public String toString() {
        return encapsulado.toString() + " con mascota ";
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

    @Override
    public IViaje clone() throws CloneNotSupportedException {
        DecoratorMascota clon = (DecoratorMascota) super.clone();
        if (encapsulado != null) clon.encapsulado.clone();
        return clon;
    }
}
