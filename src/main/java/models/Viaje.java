package models;

public class Viaje {
private double costo_viaje;
private double distancia;
private String estado_de_viaje;
private Pedido pedido;



    public void setEstado_de_viaje(String estado_de_viaje) {
        this.estado_de_viaje = estado_de_viaje;
    }

    public String getEstado_de_viaje() {
        return estado_de_viaje;
    }
}
