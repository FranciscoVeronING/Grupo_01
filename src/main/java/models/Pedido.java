package models;

import java.util.Date;

public class Pedido {
    private Date fecha; //contempla hora
    private String zona;
    private boolean mascota;
    private int cant_pasajeros;
    private boolean equipaje;
    private Cliente cilente;

    public Pedido(Date fecha, String zona, boolean mascota, int cant_pasajeros, boolean equipaje, Cliente cilente) {
        this.fecha = fecha;
        this.zona = zona;
        this.mascota = mascota;
        this.cant_pasajeros = cant_pasajeros;
        this.equipaje = equipaje;
        this.cilente = cilente;
    }

    public Date getFecha() {
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

    public Cliente getCilente() {
        return cilente;
    }
}
