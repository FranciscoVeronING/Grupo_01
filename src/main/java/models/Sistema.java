package models;

import java.util.ArrayList;
import java.util.Iterator;

public class Sistema {
    /**
     * @aggregation composite
     */
    private static Sistema _instancia = null;

    private ArrayList<Empleado> choferes;
    private ArrayList<IVehiculo> vehiculos;
    private ArrayList<Cliente> clientes;
    private ArrayList<IViaje> viajes;

    private Sistema() {
        this.choferes = new ArrayList<Empleado>();
        this.vehiculos = new ArrayList<IVehiculo>();
        this.clientes = new ArrayList<Cliente>();
        this.viajes = new ArrayList<IViaje>();
    }

    public static Sistema getInstancia() {
        if (_instancia == null)
            _instancia = new Sistema();
        return _instancia;
    }

    public ArrayList<IViaje> getViajesChofer(Empleado chofer) {
        ArrayList<IViaje> viajesChofer = new ArrayList<IViaje>();
        for (IViaje viaje : viajes) if (chofer == viaje.getChofer()) viajesChofer.add(viaje);
        return viajesChofer;
    }

    public boolean existeVehiculo(Pedido pedido) {
        boolean existe = false;
        Iterator<IVehiculo> it = this.vehiculos.iterator();
        while (it.hasNext() && !existe)
            existe = it.next().getPrioridad(pedido) != 0;
        return existe;
    }

    public IVehiculo buscarMejorVehiculo(Pedido pedido) {
        Iterator<IVehiculo> it = this.vehiculos.iterator();
        int maxP = 0;
        IVehiculo mejor = null;
        while (it.hasNext()) {
            IVehiculo v = it.next();
            Integer prioridad = v.getPrioridad(pedido);
            if (prioridad != null && prioridad > maxP) {
                maxP = prioridad;
                mejor = v;
            }
        }
        return mejor;
    }

    public IViaje asignarPedidoVehiculo(Pedido pedido) throws VehiculoNoDisponibleException {
        IViaje viaje = ViajeFactory.getViaje(pedido);
        if (!existeVehiculo(pedido)) throw new VehiculoNoDisponibleException(); // No existe vehiculo valido
        else {
            viaje.setVehiculo(buscarMejorVehiculo(pedido));
            agregarViaje(viaje);
        }
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
        if (viaje.getChofer() == null) throw new FaltaDeChoferException("Falta de choferes disponibles");
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

    // Comprueba si el nombre de usuario no esta ocupado
    public boolean validarUsuario(String nombre_usuario) {
        boolean valido = true;
        Iterator<Cliente> clientes = getClientes().iterator();
        while (clientes.hasNext() && valido)
            valido = !clientes.next().getNombre_usuario().equalsIgnoreCase(nombre_usuario);
        return valido;
    }
}
