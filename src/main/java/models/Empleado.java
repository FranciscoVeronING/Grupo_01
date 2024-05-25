package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 *
 *Clase que representa a los choferes de la empresa
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
     * Constructor que asigna al empleado un numero de dni y nombre
     * <b>Pre: </b> El parametro dni no puede ser null ni estar vacio
     * @param dni : El numero del dni del empleado
     * <b>Pre: </b> El parametro nombre no puede ser null ni estar vacio
     * @param nombre : El nombre del chofer
     */
    public Empleado(String dni,String nombre) {
        this.dni = dni;
        this.nombre = nombre;
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

    public double getCalificacion_clientes() {
        return calificacion_clientes;
    }

    /**
     *Metodo utilizado para setear el promedio de la calificacion del chofer segun los clientes
     * <b>Pre: </b> La calificacion del cliente debe ser mayor a 0.
     * @param calificacion_clientes : Es la calificacion que le da el cliente al chofer luego del viaje
     */
    public void setCalificacion_clientes(int calificacion_clientes) {
        cant_calif++;
        acum_calif += calificacion_clientes;
        this.calificacion_clientes = acum_calif/cant_calif;
    }

    /**
     *<br>Metodo que incrementa en uno la cantidad de viajes del empleado
     */
    public void AumentarCant_viajes() {
        this.cant_viajes++;
    }

    /**
     * Metodo que incrementa el puntaje del empleado segun corresponda
     * <b>Pre: </b> El parametro debe ser mayor a cero
     * @param i : Es el valor a incrementar el puntaje del empleado por viaje
     */
    public void setPuntaje(int i) {
        this.puntaje_Empresa += i;
    }

    public void finalizarViaje() {
        this.setOcupado(false);
        this.AumentarCant_viajes();
        IViaje viajeActivo = Sistema.getInstancia().getViajeActivoChofer(this);
        Sistema.getInstancia().finalizarViaje(viajeActivo);
    }

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
