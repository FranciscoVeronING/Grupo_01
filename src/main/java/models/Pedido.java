package models;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase que representa el pedido hecho por el usuario y sus requerimientos
 */
public class Pedido implements Cloneable{
    private GregorianCalendar fecha; //contempla hora
    private String zona;
    private boolean mascota;
    private int cant_pasajeros;
    private boolean equipaje;
    private Cliente cliente;
    private double distancia;

    /**
     * Constructor que setea los parametros que representan los requerimientos del usuario para el viaje
     * <b>Pre: </> El parametro fecha no puede ser null ni estar vacio
     * @param fecha Fecha en la que se realizo el pedido
     * <b>Pre: </> El parametro zona no puede ser null ni estar vacio
     * @param zona La zona en donde se llevara a cabo el pedido
     * <b>Pre: </> El parametro mascota no puede ser null ni estar vacio
     * @param mascota Indica si el viaje involucra mascotas
     * <b>Pre: </> El parametro cant_pasejors no puede ser cero
     * @param cant_pasajeros Indica cuantos pasajeros habra
     * <b>Pre: </> El parametro equipaje no puede ser null ni estar vacio
     * @param equipaje Indica si el vehiculo necesitara baul
     * <b>Pre: </> El parametro cliente no puede ser null ni estar vacio
     * @param cliente Parametro que se utiliza para almacenar la informacion del cliente que solicita el pedido
     * <b>Pre: </> El parametro d debe ser mayor de cero
     * @param d Indica la distancia del viaje
     */
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

    @Override
    public Pedido clone() {
        try {
            Pedido clone = (Pedido) super.clone();
            clone.cliente = (Cliente) this.cliente.clone();
            clone.fecha = (GregorianCalendar) this.fecha.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
