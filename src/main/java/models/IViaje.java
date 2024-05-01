package models;

public interface IViaje {
    public double getCosto_viaje();
    public void setCosto_viaje(double valor);
    public double getDistancia();
    public void setDistancia(double distancia);
    public Pedido getPedido();
    public void setPedido(Pedido pedido);
    public void setEstado_de_viaje(String estado_de_viaje);
    public String getEstado_de_viaje();
    public Empleado getChofer();
    public void setChofer(Empleado chofer);
    public IVehiculo getVehiculo();
    public void setVehiculo(IVehiculo vehiculo);
    public void setCosto_base(double costo_base);
    public double getCosto_base();
    public void calcularCostoViaje();

}