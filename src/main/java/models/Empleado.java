package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 *
 *Clase que representa a los choferes de la empresa
 * La clase es cloneable
 * La clase es serializable
 */
public abstract class Empleado implements Cloneable, Serializable {

    protected String dni;
    protected String nombre;
    protected int cant_viajes;
    protected boolean ocupado;
    protected int puntaje_Empresa;
    protected double calificacion_clientes;
    protected static int cant_calif = 0;
    protected static int acum_calif = 0;

    /**
     * Constructor que asigna al empleado un numero de dni y nombre ALEATORIOS
     */
    public Empleado() {
        this.dni = Utiles.generaDNI();
        this.nombre = Utiles.generaNombre();
        this.puntaje_Empresa = 0;
        this.ocupado = false;
        this.cant_viajes = 0;
    }

    public abstract double getSueldo(GregorianCalendar fecha_inicio_mes, Iterator<IViaje> viajes);

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCant_viajes() {
        return cant_viajes;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     *<br>Metodo que incrementa en uno la cantidad de viajes del empleado
     * <b>Post:</b> La cantidad de viajes del chofer sera incrementada en uno
     */
    public void AumentarCant_viajes() {
        this.cant_viajes++;
    }

    /**
     * Metodo que incrementa el puntaje del empleado segun corresponda
     * <b>Pre: </b> El parametro debe ser mayor a cero
     * @param i : Es el valor a incrementar el puntaje del empleado por viaje
     * <b>Post:</b> El puntaje del empleado se vera incrementado
     */
    public void setPuntaje(int i) {
        this.puntaje_Empresa += i;
    }

    /**
     * Metodo que crea una copia del empleado
     * <b>Pre:</b> La clase empleado debe implementar la interfaz cloneable
     * @return una nueva instancia que es una copia superficial del objeto empleado
     * @throws AssertionError si el objeto no es cloneable, lo cual no debería ocurrir ya que Empleado implementa Cloneable
     */
    @Override
    public Empleado clone() {
        try {
            return (Empleado) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Empleado{");
        sb.append("dni='").append(dni).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", cant_viajes=").append(cant_viajes);
        sb.append(", ocupado=").append(ocupado);
        sb.append(", puntaje_Empresa=").append(puntaje_Empresa);
        sb.append(", calificacion_clientes=").append(calificacion_clientes);
        sb.append('}');
        return sb.toString();
    }
}
