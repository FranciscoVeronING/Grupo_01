package models;

public abstract class Empleado {
    protected String dni;
    protected String nombre;
    private int cant_viajes;
    protected boolean ocupado;
  
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
}
