package models;

public class DecoratorEquipaje implements IViaje {
    IViaje encapsulado;

    public DecoratorEquipaje(IViaje encapsulado) {
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
        double base = encapsulado.getCosto_viaje();
        double aumentoPax = getCosto_base() * getPedido().getCant_pasajeros() * 0.1;
        double aumentoKm = getCosto_viaje() * getDistancia() * 0.05;
        setCosto_viaje(base + aumentoKm + aumentoPax);
    }

    @Override
    public String toString() {
        return encapsulado.toString() + " con equipaje ";
    }
}
