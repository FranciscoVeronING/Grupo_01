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

    private static Sistema _instancia = null;

    private final ArrayList<Empleado> choferes;
    private final ArrayList<IVehiculo> vehiculos;
    private final ArrayList<Cliente> clientes;
    private final ArrayList<IViaje> viajes;

    private Sistema() {
        this.choferes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.viajes = new ArrayList<>();
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
     * <b>Pre: </b> El parametro chofer no puede ser null ni vacio
     * @param chofer : El empleado chofer del cual se desean obtener los viajes
     * @return : Un iterador que contiene todos los viajes realizados por el chofer especificado
     */
    public Iterator<IViaje> getViajesChofer(Empleado chofer) {
        ArrayList<IViaje> viajesChofer = new ArrayList<>();
        for (IViaje viaje : viajes) if (chofer == viaje.getChofer()) viajesChofer.add(viaje);
        return viajesChofer.iterator();
    }

    /**
     * Obtiene un iterador de los viajes asociados a un cliente dado
     *<b>Pre: </b>El parametro cliente no puede ser null ni vacio
     * @param cliente : El cliente para el cual se desean obtener los viajes.
     * @return : Un iterador de los viajes asociados al cliente.
     */
    public Iterator<IViaje> getViajesCliente(Cliente cliente) {
        ArrayList<IViaje> viajesCliente = new ArrayList<>();
        for (IViaje viaje : viajes) if (cliente == viaje.getPedido().getCliente()) viajesCliente.add(viaje);
        return viajesCliente.iterator();
    }

    /**
     * Obtiene un iterador de los viajes asociados al chofer activo
     * <b>Pre: </b> El parametro chofer no puede ser null ni estar vacio
     * @param chofer : El chofer disponible que sera capaz de tomar el viaje
     * @return : El viaje activo asociado al chofer, o null si no hay ninguno activo y pagado
     */
    public IViaje getViajeActivoChofer(Empleado chofer) {
        IViaje aux = null;
        for (IViaje viaje : viajes)
            if (chofer == viaje.getChofer() && viaje.getEstado_de_viaje().equalsIgnoreCase("pagado"))
                aux =  viaje;
        return aux;
    }

    /**
     * Metodo que retorna el viaje activo asociado a un cliente
     *<b>Pre: </b> cliente no puede ser null ni vacio
     * @param cliente : El cliente para el cual se desea obtener el viaje activo
     * @return : El viaje activo asociado al cliente, o null si no hay ninguno activo e iniciado
     */
    public IViaje getViajeActivoCliente(Cliente cliente) {
        IViaje aux = null;
        Iterator<IViaje> viajesCliente = this.getViajesCliente(cliente);
        while (viajesCliente.hasNext() && aux == null) {
            IViaje x = viajesCliente.next();
            if (x.getEstado_de_viaje().equalsIgnoreCase("iniciado")) aux = x;
        }
        return aux;
    }

    /**
     * Metodo que comprueba si existe un vehículo disponible para un pedido dado
     *<b>Pre: </b> pedido no puede ser null ni estar vacio
     * @param pedido : pedido almacena la disponibilidad del vehículo
     * @return : true si existe al menos un vehículo disponible para el pedido, false en caso contrario
     */
    public boolean existeVehiculo(Pedido pedido) {
        boolean existe = false;
        Iterator<IVehiculo> it = this.vehiculos.iterator();
        while (it.hasNext() && !existe) {
            IVehiculo v = it.next();
            if (!v.isOcupado() && v.getPrioridad(pedido) != null)
                existe = true;
        }
        return existe;
    }

    /**
     * Metodo que busca cual sera el mejor vehiculo dados los requisitos del cliente
     * <b>Pre:</b> pedido no puede ser null ni vacio
     * @param pedido : ALmacena los requisitos del cliente
     * @return : devuelve el vehiculo que se adapte a la solicitud del cliente
     */
    public IVehiculo buscarMejorVehiculo(Pedido pedido) {
        Iterator<IVehiculo> it = this.vehiculos.iterator();
        int maxP = 0;
        IVehiculo mejor = null;
        while (it.hasNext()) {
            IVehiculo v = it.next();
            if (!v.isOcupado()) {
                Integer prioridad = v.getPrioridad(pedido);
                if (prioridad != null && prioridad > maxP) {
                    maxP = prioridad;
                    mejor = v;
                }
            }
        }
        return mejor;
    }

    /**
     * Metodo que asigna un vehiculo al pedido
     * <b>Pre: </b> pedido no puede ser null ni estar vacio
     * @param pedido almacena informacion a cerca del pedido y que auto sera el mejor para los requerimientos del mismo
     * @return El viaje asociado al pedido y vehículo asignado
     * @throws VehiculoNoDisponibleException Si no hay ningún vehículo disponible para el pedido
     * @throws PedidoIncoherenteException Si el pedido no es coherente o válido
     */
    public IViaje asignarPedidoVehiculo(Pedido pedido) throws VehiculoNoDisponibleException, PedidoIncoherenteException {
        IViaje viaje = ViajeFactory.getViaje(pedido);
        validarPedido(pedido);
        if (!existeVehiculo(pedido))
            throw new VehiculoNoDisponibleException("No hay vehiculo disponible"); // No existe vehiculo valido
        else {
            IVehiculo v = buscarMejorVehiculo(pedido);
            viaje.setVehiculo(v);
            v.setOcupado(true);
            this.agregarViaje(viaje);
        }
        return viaje;
    }

    /**
     * Valida la coherencia del pedido verificando ciertos criterios
     *<b>Pre: </b> el pedido no puede ser null ni vacio
     * @param pedido El pedido que se desea validar con la informacion necesaria del mismo
     * @throws PedidoIncoherenteException Si el pedido no es coherente
     */
    private void validarPedido(Pedido pedido) throws PedidoIncoherenteException {
        if (pedido.getCant_pasajeros() > 10) throw new PedidoIncoherenteException("Cantidad de pasajeros mayor a 10");
        if (pedido.getCant_pasajeros() > 4 && pedido.isMascota()) throw new PedidoIncoherenteException("Mascotas no permitidas en combis");
    }

    /**
     * Metodo que asigna un chofer disponible a un viaje
     *<b>Pre: </b> El parametro viaje no puede ser null ni vacio
     * @param viaje El viaje al cual se desea asignar un chofer
     * return El viaje con el chofer asignado
     * @throws ChoferNoDisponibleException Si no hay ningún chofer disponible para el viaje
     */
    public void asignarViajeChofer(IViaje viaje) throws ChoferNoDisponibleException {
        Iterator<Empleado> it = this.choferes.iterator();
        while (it.hasNext() && viaje.getChofer() == null) {
            Empleado c = it.next();
            if (!c.isOcupado()) {
                viaje.setEstado_de_viaje("iniciado");
                viaje.setChofer(c);
                c.setOcupado(true);
            }
        }
        if (viaje.getChofer() == null)
            throw new ChoferNoDisponibleException("Falta de choferes disponibles");
    }

    /**
     * Metodo que calcular los sueldos totales de la fecha solicitada
     * @return : devuelve el sueldo en la fecha solicitada
     */
    public double getSueldosTotales(GregorianCalendar fecha_inicio_mes){
        double sueldo = 0;
        for (Empleado c : this.choferes) {
            sueldo += c.getSueldo(fecha_inicio_mes);
        }
        return sueldo;
    }

    /**
     * Genera un listado de los sueldos mensuales de todos los choferes para un mes
     *<b>Pre: </b> La fecha debe ser una fecha valida
     * @param fecha_inicio_mes : La fecha de inicio del mes para el cual se genera el listado de sueldos
     * @return : Cadena que contiene el listado de sueldos mensuales de los choferes, junto con el sueldo total a pagar
     */
    public String listadoSueldoMes(GregorianCalendar fecha_inicio_mes){
        final StringBuilder sb = new StringBuilder("Listado Sueldos Mensuales: \n");
        Iterator<Empleado> empleadoIterator = this.choferes.iterator();
        if (empleadoIterator.hasNext()) {
            do {
                Empleado empleado = empleadoIterator.next();
                sb.append(empleado.getNombre()).append(" ").append(empleado.getDni()).append("\t$ ").append(empleado.getSueldo(fecha_inicio_mes)).append("\n");
            } while (empleadoIterator.hasNext());
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
     * @throws UsuarioRepetidoException Se lanza cuando el nombre de usuario ya esta en uso
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
        viajesClone.clear();
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

    /**
     * Metodo que actualiza el estado del chofer dejandolo ultimo en los choferes disponibles
     * <b>Pre: </> viajeActivo no puede ser null ni estar vacio
     * @param viajeActivo Representa el viaje que esta llevando a cabo el chofer
     */
    public void finalizarViaje(IViaje viajeActivo) {
        viajeActivo.finalizarse();
        Empleado chofer = viajeActivo.getChofer();
        IVehiculo vehiculo = viajeActivo.getVehiculo();
        vehiculo.setOcupado(false);
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

    /**
     * Genera un listado de los viajes de un cliente entre dos fechas dadas
     *<b>Pre: </b> clliente no puede ser null ni estar vacio
     * @param cliente El cliente para el cual se desea obtener los viajes
     *                <b>Pre:</b> fechai debe ser una fecha valida
     * @param fechai La fecha de inicio del período para el cual se desean los viajes
     *               <b>Pre: </b> fechaf debe ser una fecha valida y posterior a fechai
     * @param fechaf La fecha final del período para el cual se desean los viajes
     * @return Una cadena que contiene el listado de los viajes del cliente dentro del período especificado
     */

    public String viajesClienteFecha(Cliente cliente, GregorianCalendar fechai, GregorianCalendar fechaf){
        final StringBuilder sb = new StringBuilder("Viajes de ");
        sb.append(cliente.getNombre_usuario()).append(": \n");
        Iterator<IViaje> viajes = this.getViajesCliente(cliente);
        while (viajes.hasNext()) {
            IViaje viaje = viajes.next();
            if ((viaje.getPedido().getFecha().compareTo(fechai) >= 0) && (viaje.getPedido().getFecha().compareTo(fechaf) <= 0))
                sb.append(viaje).append("\n");
        }
    return sb.toString();

    }

    /**
     * Genera un listado de los viajes de un chofer entre dos fechas dadas
     *<b>Pre: </b> chofer no puede ser null ni vacio
     * @param chofer : El chofer para el cual se desea obtener los viajes
     *<b>Pre: </b> fechai debe ser una fecha valida
     * @param fechai : La fecha de inicio del período para el cual se desean los viajes
     *<b>Pre: </b> fechaf debe ser una fecha valida y posterior a fechai
     * @param fechaf : La fecha final del período para el cual se desean los viajes
     * @return :  Cadena que contiene el listado de los viajes del chofer dentro del período especificado
     */
    public String viajesChoferesFecha(Empleado chofer, GregorianCalendar fechai, GregorianCalendar fechaf){
        final StringBuilder sb = new StringBuilder("Viajes de ");
        sb.append(chofer.getNombre()).append(" ,DNI: ").append(chofer.getDni()).append(": \n");
        Iterator<IViaje> viajes = this.getViajesChofer(chofer);
        while (viajes.hasNext()) {
            IViaje viaje = viajes.next();
            if ((viaje.getPedido().getFecha().compareTo(fechai) >= 0) && (viaje.getPedido().getFecha().compareTo(fechaf) <= 0))
                sb.append(viaje).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sistema{");
        sb.append(" choferes=").append(choferes);
        sb.append(", vehiculos=").append(vehiculos);
        sb.append(", clientes=").append(clientes);
        sb.append(", viajes=").append(viajes);
        sb.append('}');
        return sb.toString();
    }
}