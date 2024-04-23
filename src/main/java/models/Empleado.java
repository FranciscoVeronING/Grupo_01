package models;

public abstract class Empleado {
    protected String dni;
    protected String nombre;
    private int cant_viajes;
  
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
}
