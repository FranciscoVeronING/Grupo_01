package models;

import Exception.VehiculoNoDisponibleException;
import Exception.ChoferNoDisponibleException;
import Exception.UsuarioRepetidoException;

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
        if (!existeVehiculo(pedido))
            throw new VehiculoNoDisponibleException("No existe el vehiculo"); // No existe vehiculo valido
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
                it.next().setCant_viajes();
            }
        }
        if (viaje.getChofer() == null)                  //Va aca? o al comienzo del metodo?
            throw new ChoferNoDisponibleException("Falta de choferes disponibles");
        return viaje;
    }

    //GETSUELDOMENSUAL Y GETSUELDOSTOTALES DEBE IR EN ADMINISTRADOR, POR AHORA QUEDA ACA.
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

    public void cliente_ver_propios_viajes(Cliente cliente){
       Iterator<Viaje> viajes = cliente.getViajes().iterator();
       while (viajes.hasNext()){
            viajes.next().toString();
       }
    }

    public void historico_viajes(){
        Iterator<IViaje> viajes = this.getViajes().iterator();
        while (viajes.hasNext()){
            viajes.next().toString();
        }
    }
    public void listado_choferes(){
        Iterator<Empleado> empleados = this.getChoferes().iterator();
        while (empleados.hasNext()){
            empleados.next().toString();
        }
    }
    public void listado_clientes(){
        Iterator<Cliente> clientes = this.getClientes().iterator();
        while (clientes.hasNext()){
            clientes.next().toString();
        }
    }
    public void listado_vehiculos(){
        Iterator<IVehiculo> vehiculos = this.getVehiculos().iterator();
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
                    km_realizados += viaje.getDistancia();
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