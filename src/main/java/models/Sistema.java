package models;

import Exception.VehiculoNoDisponibleException;
import Exception.ChoferNoDisponibleException;
import Exception.UsuarioRepetidoException;
import Exception.PedidoIncoherenteException;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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

    public Iterator<IViaje> getViajesChofer(Empleado chofer) {
        ArrayList<IViaje> viajesChofer = new ArrayList<IViaje>();
        for (IViaje viaje : viajes) if (chofer == viaje.getChofer()) viajesChofer.add(viaje);
        return viajesChofer.iterator();
    }

    public Iterator<IViaje> getViajesCliente(Cliente cliente) {
        ArrayList<IViaje> viajesCliente = new ArrayList<IViaje>();
        for (IViaje viaje : viajes) if (cliente == viaje.getPedido().getCliente()) viajesCliente.add(viaje);
        return viajesCliente.iterator();
    }

    public Viaje getViajeActivoChofer(Empleado chofer) {
        for (IViaje viaje : viajes)
            if (chofer == viaje.getChofer() && viaje.getEstado_de_viaje().equalsIgnoreCase("pagado")) return (Viaje) viaje;
        return null;
    }

    public Viaje getViajeActivoCliente(Cliente cliente) {
        for (IViaje viaje : viajes)
            if (cliente == viaje.getPedido().getCliente() && viaje.getEstado_de_viaje().equalsIgnoreCase("iniciado")) return (Viaje) viaje;
        return null;
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
            if (prioridad != 0 && prioridad > maxP) {
                maxP = prioridad;
                mejor = v;
            }
        }
        return mejor;
    }

    public IViaje asignarPedidoVehiculo(Pedido pedido) throws VehiculoNoDisponibleException, PedidoIncoherenteException {
        IViaje viaje = ViajeFactory.getViaje(pedido);
        validarPedido(pedido);
        if (!existeVehiculo(pedido))
            throw new VehiculoNoDisponibleException("No existe el vehiculo"); // No existe vehiculo valido
        else {
            viaje.setVehiculo(buscarMejorVehiculo(pedido));
            agregarViaje(viaje);
        }
        return viaje;
    }

    private void validarPedido(Pedido pedido) throws PedidoIncoherenteException {
        if (pedido.getCant_pasajeros() > 10) throw new PedidoIncoherenteException("Cantidad de pasajeros mayor a 10");
        if (pedido.getCant_pasajeros() > 4 && pedido.isMascota()) throw new PedidoIncoherenteException("Mascotas no permitidas en combis");
    }

    public IViaje asignarViajeChofer(IViaje viaje) throws ChoferNoDisponibleException {
        Iterator<Empleado> it = this.choferes.iterator();
        while (it.hasNext() && viaje.getChofer() == null) {
            Empleado c = it.next();
            if (!c.isOcupado()) {
                viaje.setChofer(c);
                c.setOcupado(true);
                viaje.setEstado_de_viaje("iniciado");
                c.setCant_viajes();
            }
        }
        if (viaje.getChofer() == null)
            throw new ChoferNoDisponibleException("Falta de choferes disponibles");
        return viaje;
    }

    //GETSUELDOMENSUAL Y GETSUELDOSTOTALES DEBE IR EN ADMINISTRADOR, POR AHORA QUEDA ACA.
    public double getSueldoMensual(Empleado chofer){
        return chofer.getSueldo();
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

    public void agregarCliente(Cliente c) throws UsuarioRepetidoException{      //HACER ESTO CON UN ITERATOR.
       Iterator <Cliente> clientes = this.clientes.iterator();
       boolean flag = true;
        while (clientes.hasNext() && flag == true)
            if (clientes.next().getNombre_usuario().equalsIgnoreCase(c.getNombre_usuario()))
                flag = false;
        if (flag)
            this.clientes.add(c);
        else
            throw new UsuarioRepetidoException("Usuario existente");

    }

    public Iterator<Empleado> getChoferes() {
        return choferes.iterator();
    }

    public Empleado getChofer(int i){
        return (Empleado) this.choferes.get(i);
    }

    public Iterator<IVehiculo> getVehiculos() {
        return vehiculos.iterator();
    }

    public Iterator<Cliente> getClientes() {
        return clientes.iterator();
    }

    public Iterator<IViaje> getViajes() {
        return viajes.iterator();
    }

    // Comprueba si el nombre de usuario no esta ocupado
    public boolean validarUsuario(String nombre_usuario) {
        boolean valido = true;
        Iterator<Cliente> clientes = getClientes();
        while (clientes.hasNext() && valido)
            valido = !clientes.next().getNombre_usuario().equalsIgnoreCase(nombre_usuario);
        return valido;
    }

    public void historico_viajes(){
        Iterator<IViaje> viajes = this.getViajes();
        while (viajes.hasNext()){
            viajes.next().toString();
        }
    }
    public void listado_choferes(){
        Iterator<Empleado> empleados = this.getChoferes();
        while (empleados.hasNext()){
            empleados.next().toString();
        }
    }
    public void listado_clientes(){
        Iterator<Cliente> clientes = this.getClientes();
        while (clientes.hasNext()){
            clientes.next().toString();
        }
    }
    public void listado_vehiculos(){
        Iterator<IVehiculo> vehiculos = this.getVehiculos();
        while (vehiculos.hasNext()){
            vehiculos.next().toString();
        }
    }

    public void puntaje_mes_finalizado(GregorianCalendar pricipio_mes){
        double max = 0;
        Empleado maxviajes = null;
        Iterator<Empleado> empleadoIterator = this.choferes.iterator();
        while (empleadoIterator.hasNext()) {
            Empleado empleado = empleadoIterator.next();
            double km_realizados = 0;
            int cantidad = 0;
            Iterator<IViaje> viajeIterator = this.viajes.iterator();
            while (empleadoIterator.hasNext() && viajeIterator.hasNext() && viajeIterator.next().getPedido().getFecha().compareTo(pricipio_mes) >= 0) {
                IViaje viaje = viajeIterator.next();
                if (empleado.getDni().equalsIgnoreCase(viaje.getChofer().dni)){
                    km_realizados += viaje.getPedido().getDistancia();
                    cantidad++;
                }
            }
            empleado.setPuntaje(cantidad*5);
            if (km_realizados > max) {
                max = km_realizados;
                maxviajes = empleado;
            }
        }
        if(maxviajes != null)
            maxviajes.setPuntaje(15);
    }
}