package models;

import java.util.ArrayList;
import java.util.Iterator;

public class Empresa {
    /**
     * @aggregation composite
     */
    private static Empresa _instancia = null;

    private ArrayList<Empleado> choferes;
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

    private Viaje asignarPedidoVehiculo(Pedido pedido) throws VehiculoNoDisponibleException {
        Viaje viaje = new Viaje(pedido);
        Iterator<Vehiculo> it = this.vehiculos.iterator();

        int maxP = 0;
        while (it.hasNext()) {
            Vehiculo v = it.next();
            if (v.getPrioridad(pedido) > maxP) {
                maxP = v.getPrioridad(pedido);
                viaje.setVehiculo(v);
            }
        }
        if (viaje.getVehiculo() == null) throw new VehiculoNoDisponibleException();
        return viaje;
    }

    private Viaje asignarViajeChofer(Viaje viaje) throws ChoferNoDisponibleException {
        Iterator<Empleado> it = this.choferes.iterator();
        while (it.hasNext() && viaje.getChofer() == null) {
            Empleado c = it.next();
            if (!c.isOcupado()) {
                viaje.setChofer(c);
                c.setOcupado(true);
                viaje.setEstado_de_viaje("iniciado");
            }
        }
        if (viaje.getChofer() == null) throw new ChoferNoDisponibleException();
        return viaje;
    }

}
