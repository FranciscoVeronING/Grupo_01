package models;

import java.util.Date;
import java.util.GregorianCalendar;

public class Pedido {
    private GregorianCalendar fecha; //contempla hora
    private String zona;
    private boolean mascota;
    private int cant_pasajeros;
    private boolean equipaje;
    private Cliente cliente;

    public Pedido(GregorianCalendar fecha, String zona, boolean mascota, int cant_pasajeros, boolean equipaje, Cliente cliente) {
        this.fecha = fecha;
        this.zona = zona;
        this.mascota = mascota;
        this.cant_pasajeros = cant_pasajeros;
        this.equipaje = equipaje;
        this.cliente = cliente;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public String getZona() {
        return zona;
    }

    public boolean isMascota() {
        return mascota;
    }

    public int getCant_pasajeros() {
        return cant_pasajeros;
    }

    public boolean isEquipaje() {
        return equipaje;
    }

    public Cliente getCliente() {
        return cliente;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pedido{");
        sb.append("fecha=").append(fecha);
        sb.append(", zona='").append(zona).append('\'');
        sb.append(", mascota=").append(mascota);
        sb.append(", cant_pasajeros=").append(cant_pasajeros);
        sb.append(", equipaje=").append(equipaje);
        sb.append(", cliente=").append(cliente);
        sb.append('}');
        return sb.toString();
    }
}
