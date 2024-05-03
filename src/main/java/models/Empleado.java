package models;

/**
 *
 *Clase que representa a los choferes de la empresa
 */
public abstract class Empleado {
    protected String dni;
    protected String nombre;
    private int cant_viajes;
    protected boolean ocupado;

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
    }
    public abstract double getSueldo();

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

    @Override
    public String toString() {
        return  "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cant_viajes=" + cant_viajes +
                ", ocupado=" + ocupado +
                '}';
    }
}
