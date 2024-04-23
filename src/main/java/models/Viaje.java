package models;

public class Viaje {
private double costo_viaje;
private double distancia;
private String estado_de_viaje;
private Pedido pedido;
private Chofer chofer;

    public Chofer getChofer() {
        return chofer;
    }

    public double getCosto_viaje() {
        return costo_viaje;
    }

    public double getDistancia() {
        return distancia;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setEstado_de_viaje(String estado_de_viaje) {
        this.estado_de_viaje = estado_de_viaje;
    }

    public String getEstado_de_viaje() {
        return estado_de_viaje;
    }
}
