package models;

import java.io.Serializable;
import java.util.ArrayList;

public class SistemaDTO implements Serializable {

    private ArrayList<Empleado> choferes;
    private ArrayList<IVehiculo> vehiculos;
    private ArrayList<Cliente> clientes;
    private ArrayList<IViaje> viajes;

    public SistemaDTO() {
        this.choferes = Sistema.getInstancia().getChoferes();
        this.vehiculos = Sistema.getInstancia().getVehiculos();
        this.clientes = Sistema.getInstancia().getClientes();
        this.viajes = Sistema.getInstancia().getViajes();
    }

    public void setChoferes(ArrayList<Empleado> choferes) {
        this.choferes = choferes;
    }

    public void setVehiculos(ArrayList<IVehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setViajes(ArrayList<IViaje> viajes) {
        this.viajes = viajes;
    }

    public ArrayList<Empleado> getChoferes() {
        return choferes;
    }

    public ArrayList<IVehiculo> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<IViaje> getViajes() {
        return viajes;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

}
