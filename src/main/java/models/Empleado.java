package models;

import java.util.GregorianCalendar;

/**
 *
 *Clase que representa a los choferes de la empresa
 */
public abstract class Empleado {
    protected String dni;
    protected String nombre;
    protected int cant_viajes;
    protected boolean ocupado;
    protected int puntaje_Empresa;

    /**
     * Constructor que asigna al empleado un numero de dni y nombre
     *
     * <b>Pre: </> El parametro dni no puede ser null ni estar vacio
     * @param dni  El numero del dni del empleado
     *
     * <b>Pre: </> El parametro nombre no puede ser null ni estar vacio
     * @param nombre El nombre del chofer
     */
    public Empleado(String dni,String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.puntaje_Empresa = 0;
        this.ocupado = false;
    }
    public abstract double getSueldo(GregorianCalendar fecha_inicio_mes);

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

    public void finalizarViaje() {
        IViaje viajeActivo = Sistema.getInstancia().getViajeActivoChofer(this);
        Sistema.getInstancia().finalizarViaje(viajeActivo);
    }

    @Override
    public String toString() {
        return  "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cant_viajes=" + cant_viajes +
                ", ocupado=" + ocupado +
                '}';
    }

    /**
     *<br>Metodo que incrementa en uno la cantidad de viajes del empleado
     */
    public void setCant_viajes() {
        this.cant_viajes++;
    }

    /**
     * Metodo que incrementa el puntaje del empleado segun corresponda
     * <b>Pre: </> El parametro debe ser mayor a cero
     * @param i Es el valor a incrementar el puntaje del empleado por viaje
     */
    public void setPuntaje(int i) {
        this.puntaje_Empresa += i;
    }
}
