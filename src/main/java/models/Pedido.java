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
    private double distancia;

    public Pedido(GregorianCalendar fecha, String zona, boolean mascota, int cant_pasajeros, boolean equipaje, Cliente cliente, double d) {
        this.fecha = fecha;
        this.zona = zona;
        this.mascota = mascota;
        this.cant_pasajeros = cant_pasajeros;
        this.equipaje = equipaje;
        this.cliente = cliente;
        this.distancia = d;
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

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setMascota(boolean mascota) {
        this.mascota = mascota;
    }

    public void setCant_pasajeros(int cant_pasajeros) {
        this.cant_pasajeros = cant_pasajeros;
    }

    public void setEquipaje(boolean equipaje) {
        this.equipaje = equipaje;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pedido{");
        sb.append("fecha=").append(formatFecha(fecha));
        sb.append(", zona='").append(zona).append('\'');
        sb.append(", mascota=").append(mascota);
        sb.append(", cant_pasajeros=").append(cant_pasajeros);
        sb.append(", equipaje=").append(equipaje);
        sb.append(", cliente=").append(cliente);
        sb.append('}');
        return sb.toString();
    }

    public String formatFecha(GregorianCalendar fecha){
        int day = fecha.get(fecha.DAY_OF_MONTH);
        int month = fecha.get(fecha.MONTH) + 1; // Los meses en Java empiezan en 0, por lo que se suma 1 para obtener el mes correcto.
        int year = fecha.get(fecha.YEAR);
        final StringBuilder sb = new StringBuilder("(");
        sb.append(year).append("/").append(month).append("/").append(day).append(")");
        return sb.toString();
    }
}
