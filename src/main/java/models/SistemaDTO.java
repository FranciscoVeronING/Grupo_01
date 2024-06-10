package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SistemaDTO implements Serializable {

    private ArrayList<Empleado> choferes;
    private ArrayList<IVehiculo> vehiculos;
    private HashMap<String, Cliente> clientes;
    private transient BolsaDeViajes viajes;

    public SistemaDTO() {
       // this.choferes = Sistema.getInstancia().getChoferes();
       // this.vehiculos = Sistema.getInstancia().getVehiculos();
       // this.clientes = Sistema.getInstancia().getClientes();
       // this.viajes = Sistema.getInstancia().getViajes();
    }

    public BolsaDeViajes getViajes() {
        return viajes;
    }

    public void setViajes(BolsaDeViajes viajes) {
        this.viajes = viajes;
    }

    public void setChoferes(ArrayList<Empleado> choferes) {
        this.choferes = choferes;
    }

    public void setVehiculos(ArrayList<IVehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

// public void setViajes(ArrayList<IViaje> viajes) {
  //      this.viajes = viajes;
   // }

    public ArrayList<Empleado> getChoferes() {
        return choferes;
    }

    public ArrayList<IVehiculo> getVehiculos() {
        return vehiculos;
    }

  //  public ArrayList<IViaje> getViajes() {
   //     return viajes;
   // }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }
}
