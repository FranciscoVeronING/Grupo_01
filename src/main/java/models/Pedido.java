package models;

import java.util.Date;

public class Pedido {
    private Date fecha; //contempla hora
    private Zona zona;
    private boolean mascota;
    private int cant_pasajeros;
    private boolean equipaje;
    private Cliente cilente;

    public Date getFecha() {
        return fecha;
    }

    public Zona getZona() {
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
