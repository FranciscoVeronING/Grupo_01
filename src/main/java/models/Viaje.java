package models;

public class Viaje implements IViaje {
    private double costo_base;
    private double costo_viaje;
    private double distancia;
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

    public void setCosto_viaje(double costo_viaje) {
        this.costo_viaje = calcularCostoViaje();
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

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

    public double calcularCostoViaje() {
        return costo_base;
    }

    public double getCosto_base() {
        return costo_base;
    }

    public void setCosto_base(double costo_base) {
        this.costo_base = costo_base;
    }
}
