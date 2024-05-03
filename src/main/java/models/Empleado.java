package models;

public abstract class Empleado {
    protected String dni;
    protected String nombre;
    private int cant_viajes;
    protected boolean ocupado;
    private int puntaje;
    public Empleado(String dni,String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.cant_viajes = 0;
        this.ocupado = false;
        this.puntaje = 0;
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

    public void setCant_viajes() {
        this.cant_viajes++;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje += puntaje;
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
