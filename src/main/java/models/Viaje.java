package models;

public class Viaje {
private double costo_viaje;
private double distancia;
private String estado_de_viaje;
private Pedido pedido;
private Chofer chofer;


    public Viaje( Pedido pedido) {
        this.estado_de_viaje = "Solicitado";
        this.pedido = pedido;
    }

    public double getCosto_viaje() {
        return costo_viaje;
    }

    public void setCosto_viaje(double costo_viaje) {
        this.costo_viaje = costo_viaje;
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
        this.estado_de_viaje = estado_de_viaje;
    }

    public String getEstado_de_viaje() {
        return estado_de_viaje;
    }
  
  public Chofer getChofer() {
        return chofer;
  }
}