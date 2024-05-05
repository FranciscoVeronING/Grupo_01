package models;

import Exception.VehiculoNoDisponibleException;
import Exception.ChoferNoDisponibleException;
import Exception.UsuarioRepetidoException;
import Exception.PedidoIncoherenteException;

import java.util.*;

/**
 * Clase que representa a la empresa en el programa
 */
public class Sistema  {
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

    /**
     * Metodo que aplica el patron singleton
     * @return Devuelve una sola instancia del sistema
     */
    public static Sistema getInstancia() {
        if (_instancia == null)
            _instancia = new Sistema();
        return _instancia;
    }

    /**
     * Retorna un iterador que contiene todos los viajes realizados por un empleado chofer dado
     * <b>Pre: </> El parametro chofer no puede ser null ni vacio
     * @param chofer El empleado chofer del cual se desean obtener los viajes
     * @return Un iterador que contiene todos los viajes realizados por el chofer especificado
     */
    public Iterator<IViaje> getViajesChofer(Empleado chofer) {
        ArrayList<IViaje> viajesChofer = new ArrayList<IViaje>();
        for (IViaje viaje : viajes) if (chofer == viaje.getChofer()) viajesChofer.add(viaje);
        return viajesChofer.iterator();
    }

    /**
     * Obtiene un iterador de los viajes asociados a un cliente dado
     *<b>Pre: </>El parametro cliente no puede ser null ni vacio
     * @param cliente El cliente para el cual se desean obtener los viajes.
     * @return Un iterador de los viajes asociados al cliente.
     */
    public Iterator<IViaje> getViajesCliente(Cliente cliente) {
        ArrayList<IViaje> viajesCliente = new ArrayList<IViaje>();
        for (IViaje viaje : viajes) if (cliente == viaje.getPedido().getCliente()) viajesCliente.add(viaje);
        return viajesCliente.iterator();
    }

    /**
     * Obtiene un iterador de los viajes asociados al chofer activo
     * <b>Pre: </> El parametro chofer no puede ser null ni estar vacio
     * @param chofer El chofer disponible que sera capaz de tomar el viaje
     * @return El viaje activo asociado al chofer, o null si no hay ninguno activo y pagado
     */
    public IViaje getViajeActivoChofer(Empleado chofer) {
        IViaje aux = null;
        for (IViaje viaje : viajes)
            if (chofer == viaje.getChofer() && viaje.getEstado_de_viaje().equalsIgnoreCase("pagado"))
                aux =  viaje;
        return aux;
    }

    
    public IViaje getViajeActivoCliente(Cliente cliente) {
        IViaje aux = null;
        for (IViaje viaje : viajes)
            if (cliente == viaje.getPedido().getCliente() && viaje.getEstado_de_viaje().equalsIgnoreCase("iniciado"))
                aux =  viaje;
        return aux;
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
    public double getSueldoMensual(Empleado chofer, GregorianCalendar fecha_inicio_mes){

        return chofer.getSueldo(fecha_inicio_mes);
    }
    public double getSueldosTotales(GregorianCalendar fecha_inicio_mes){
        double sueldo = 0;
        for (Empleado c : this.choferes) {

            sueldo += c.getSueldo(fecha_inicio_mes);
        }
        return sueldo;
    }

    public String listadoSueldoMes(GregorianCalendar fecha_inicio_mes){
        final StringBuilder sb = new StringBuilder("Listado Sueldos Mensuales: \n");
        Iterator<Empleado> empleadoIterator = this.choferes.iterator();
        while (empleadoIterator.hasNext()) {
            Empleado empleado = empleadoIterator.next();
            sb.append(empleado.getNombre()).append(empleado.getDni()).append("\t$ ").append(empleado.getSueldo(fecha_inicio_mes)).append("\n");
        }
        sb.append("Sueldo Total a pagar:\t$ ").append(this.getSueldosTotales(fecha_inicio_mes));
        return sb.toString();
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

    /**
     * Funcion que agrega un cliente a la lista de clientes de la empresa.
     * <b>Precondicion: </b> el cliente debe ser distinto de null<br>
     * @param cliente : Parametro de tipo Cliente
     * @throws UsuarioRepetidoException
     */
    public void agregarCliente(Cliente cliente) throws UsuarioRepetidoException{
       Iterator <Cliente> clientes = this.clientes.iterator();
       boolean flag = true;
        while (clientes.hasNext() && flag)
            if (clientes.next().getNombre_usuario().equalsIgnoreCase(cliente.getNombre_usuario()))
                flag = false;
        if (flag)
            this.clientes.add(cliente);
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
    /**
     * Funcion que genera un String que representa el listado de todos los clientes de la empresa.
     * @return : devuelve una variable de tipo String que contiene el listado de los clientes de la empresa.
     */
    public String listado_clientes(){
        StringBuilder reporte = new StringBuilder();
        Iterator<Cliente> clientes = this.getClientes();
        while (clientes.hasNext()){
            reporte.append("\n").append(clientes.next().toString());
        }
        return reporte.toString();
    }

    /**
     * Funcion que genera un String que representa el listado de todos los viajes de la empresa.
     * @return : devuelve una variable de tipo String que contiene el listado de los viajes de la empresa.
     */
    public String historico_viajes() throws CloneNotSupportedException {
        assert getInstancia().viajes.isEmpty();
        ArrayList<IViaje> viajesClone = (ArrayList<IViaje>) getInstancia().viajes.clone();
        for (int i = 0; i < getInstancia().viajes.size(); i++) {
            viajesClone.add(getInstancia().viajes.get(i).clone());
        }
        if(!viajesClone.isEmpty())
            viajesClone.sort(Comparator.comparingDouble(IViaje::getCosto_viaje).reversed());


        StringBuilder reporte = new StringBuilder();
        Iterator<IViaje> viajeIterator = viajesClone.iterator();
        if (viajeIterator.hasNext()) {
            do {
                reporte.append("\n").append(viajeIterator.next().toString());
            } while (viajeIterator.hasNext());
        }
        return reporte.toString();
    }
    /**
     * Funcion que genera un String que representa el listado de choferes de la empresa.
     * @return : devuelve una variable de tipo String que contiene el listado de los choferes de la empresa.
     */
    public String listado_choferes(){
        StringBuilder reporte = new StringBuilder();
        Iterator<Empleado> empleados = this.getChoferes();
        while (empleados.hasNext()){
            reporte.append("\n").append(empleados.next());
        }
        return reporte.toString();
    }

    /**
     * Funcion que genera un String que representa el listado de vehiculos de la empresa.
     * @return : devuelve una variable de tipo String que contiene el listado de los vehiculos de la empresa.
     */
    public String listado_vehiculos(){
        StringBuilder reporte = new StringBuilder();
        Iterator<IVehiculo> vehiculos = this.getVehiculos();
        while (vehiculos.hasNext()){
            reporte.append("\n").append(vehiculos.next().toString());
        }
        return reporte.toString();
    }

    /**
     * Funcion que calcula los puntajes de todos los choferes en un periodo de un mes de trabajo. Se conoce que la fecha limite es la actual, y se pasa por parametro la fecha de inicio del mes a analizar.
     * @param pricipio_mes : parametro de tipo GregorianCalendar que representa la fecha de inicio de mes que acaba de terminar
     */
    public void puntaje_mes_finalizado(GregorianCalendar pricipio_mes){
        double max = 0;
        Empleado maxKM = null;
        Iterator<Empleado> empleadoIterator = getChoferes();
        while (empleadoIterator.hasNext()) {
            Empleado empleado = empleadoIterator.next();
            double km_realizados = 0;
            int cantidad = 0;
            Iterator<IViaje> viajesChofer = getViajesChofer(empleado);
            while (viajesChofer.hasNext()) {
                IViaje v = viajesChofer.next();
                if (v.getPedido().getFecha().compareTo(pricipio_mes) >= 0) {
                    km_realizados += v.getPedido().getDistancia();
                    cantidad++;
                }
            }
            empleado.setPuntaje(cantidad*5);
            if (km_realizados > max) {
                max = km_realizados;
                maxKM = empleado;
            }
        }
        if(maxKM != null)
            maxKM.setPuntaje(15);
    }

    public void finalizarViaje(IViaje viajeActivo) {
        viajeActivo.finalizarse();
        Empleado chofer = viajeActivo.getChofer();
        IVehiculo vehiculo = viajeActivo.getVehiculo();
        // Lo saco de lista y pongo ultimo CHOFER
        this.choferes.remove(chofer);
        this.choferes.addLast(chofer);
        // Lo saco de lista y pongo ultimo VEHICULO
        this.vehiculos.remove(vehiculo);
        this.vehiculos.addLast(vehiculo);
    }

    public void pagarViaje(IViaje v) {
        v.pagarse();
    }

    public String viajesClienteFecha(Cliente cliente, GregorianCalendar fechai, GregorianCalendar fechaf){
        final StringBuilder sb = new StringBuilder("Viajes de ");
        sb.append(cliente.getNombre_usuario()).append(": \n");
        Iterator<IViaje> viajes = this.getViajesCliente(cliente);
        while (viajes.hasNext()) {
            IViaje viaje = viajes.next();
            if ((viaje.getPedido().getFecha().compareTo(fechai) >= 0) && (viaje.getPedido().getFecha().compareTo(fechaf) <= 0))
                sb.append(viaje.toString()).append("\n");
        }
    return sb.toString();

    }
    public String viajesChoferesFecha(Empleado chofer, GregorianCalendar fechai, GregorianCalendar fechaf){
        final StringBuilder sb = new StringBuilder("Viajes de ");
        sb.append(chofer.getNombre()).append(" ,DNI: ").append(chofer.getDni()).append(": \n");
        Iterator<IViaje> viajes = this.getViajesChofer(chofer);
        while (viajes.hasNext()) {
            IViaje viaje = viajes.next();
            if ((viaje.getPedido().getFecha().compareTo(fechai) >= 0) && (viaje.getPedido().getFecha().compareTo(fechaf) <= 0))
                sb.append(viaje.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sistema{");
        sb.append("choferes=").append(choferes);
        sb.append(", vehiculos=").append(vehiculos);
        sb.append(", clientes=").append(clientes);
        sb.append(", viajes=").append(viajes);
        sb.append('}');
        return sb.toString();
    }
}