package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SistemaDTO implements Serializable {

    private ArrayList<Empleado> choferes;
    private ArrayList<IVehiculo> vehiculos;
    private HashMap<String, Cliente> clientes;
    private HashMap<String, Cliente> clientesApp;
    private transient BolsaDeViajes viajes;

    public SistemaDTO() {
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

    public ArrayList<Empleado> getChoferes() {
        return choferes;
    }

    public ArrayList<IVehiculo> getVehiculos() {
        return vehiculos;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public HashMap<String, Cliente> getClientesApp() {
        return clientesApp;
    }

    public void setClientesApp(HashMap<String, Cliente> clientesApp) {
        this.clientesApp = clientesApp;
    }
}
