package models;

import java.util.ArrayList;
import java.util.Iterator;

public class Empresa {
    /**
     * @aggregation composite
     */
    private static Empresa _instancia = null;

    private ArrayList<Empleado> choferes;
    private ArrayList<IVehiculo> vehiculos;
    private ArrayList<Cliente> clientes;
    private ArrayList<IViaje> viajes;

    private Empresa() {
        this.choferes = new ArrayList<Empleado>();
        this.vehiculos = new ArrayList<IVehiculo>();
        this.clientes = new ArrayList<Cliente>();
        this.viajes = new ArrayList<IViaje>();
    }

    public static Empresa getInstancia() {
        if (_instancia == null)
            _instancia = new Empresa();
        return _instancia;
    }

    public ArrayList<IViaje> getViajesChofer(Empleado chofer) {
        ArrayList<IViaje> viajesChofer = new ArrayList<IViaje>();
        for (IViaje viaje : viajes) if (chofer == viaje.getChofer()) viajesChofer.add(viaje);
        return viajesChofer;
    }

    public IViaje asignarPedidoVehiculo(Pedido pedido) throws VehiculoNoDisponibleException {
        IViaje viaje = ViajeFactory.getViaje(pedido);
        Iterator<IVehiculo> it = this.vehiculos.iterator();

        int maxP = 0;
        while (it.hasNext()) {
            IVehiculo v = it.next();
            Integer prioridad = v.getPrioridad(pedido);
            if (prioridad != null && prioridad > maxP) {
                maxP = v.getPrioridad(pedido);
                viaje.setVehiculo(v);
            }
        }
        if (viaje.getVehiculo() == null) throw new VehiculoNoDisponibleException();
        else agregarViaje(viaje);
        return viaje;
    }

    public IViaje asignarViajeChofer(IViaje viaje) throws ChoferNoDisponibleException {
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

    //GETSUELDOMENSUAL Y GETSUELDOSTOTALES DEBE IR EN ADMINISTRADOR
    public double getSueldoMensual(int i){
        return this.choferes.get(i).getSueldo();
    }
    public double getSueldosTotales(){
        double sueldo = 0;
        for (Empleado c : this.choferes) {
            sueldo += c.getSueldo();
        }
        return sueldo;
    }

    public void agregarChofer(Empleado c) {
        this.choferes.add(c);
    }

    public void agregarVehiculo(IVehiculo v) {
        this.vehiculos.add(v);
    }

    public void agregarViaje(IViaje v) {
        this.viajes.add(v);
    }

    public void agregarCliente(Cliente c) {
        this.clientes.add(c);
    }

    public ArrayList<Empleado> getChoferes() {
        return choferes;
    }

    public Chofer getChofer(int i){
        return (Chofer) this.choferes.get(i);
    }

    public ArrayList<IVehiculo> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<IViaje> getViajes() {
        return viajes;
    }
}
