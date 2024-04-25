package models;

import java.util.ArrayList;
import java.util.Iterator;

public class Empresa {
    /**
     * @aggregation composite
     */
    private static Empresa _instancia = null;

    private ArrayList<Chofer> choferes;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Viaje> viajes;

    private Empresa() {
        this.choferes = new ArrayList<Chofer>();
        this.vehiculos = new ArrayList<Vehiculo>();
        this.clientes = new ArrayList<Cliente>();
        this.viajes = new ArrayList<Viaje>();
    }

    public static Empresa getInstancia() {
        if (_instancia == null)
            _instancia = new Empresa();
        return _instancia;
    }

    public ArrayList<Viaje> getViajesChofer(Empleado chofer) {
        ArrayList<Viaje> viajesChofer = new ArrayList<Viaje>();
        for (Viaje viaje : viajes) if (chofer == viaje.getChofer()) viajesChofer.add(viaje);
        return viajesChofer;
    }

}
