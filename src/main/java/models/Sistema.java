package models;

import Exception.VehiculoNoDisponibleException;
import Exception.ChoferNoDisponibleException;
import Exception.UsuarioRepetidoException;
import Exception.PedidoIncoherenteException;
import Exception.UsuarioIncorrectoException;
import vista.Controlador;

import java.util.*;

/**
 * Clase que representa a la empresa en el programa
 */
public class Sistema {
    private final String MOTO = "MOTO";
    private final String AUTO = "AUTO";
    private final String COMBI = "COMBI";

    private static Sistema _instancia = null;
    private SistemaXML sistemaOutput;
    private SistemaXML sistemaInput;

    private ArrayList<Empleado> choferes;
    private ArrayList<IVehiculo> vehiculos;
    private HashMap<String, Cliente> clientes;
    private BolsaDeViajes viajes;
    private HashMap<String, Cliente> clientesApp;

    private int choferesActivos;
    private int clientesActivos;
    private boolean clienteAppActivo;

    private Sistema() {
        this.choferes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.clientes = new HashMap<>();
        this.viajes = new BolsaDeViajes();
        this.clientesApp = new HashMap<>();
        this.choferesActivos = 0;
        this.clientesActivos = 0;
        this.clienteAppActivo = true;
    }



    /**
     * Metodo que aplica el patron singleton
     * @return Devuelve una sola instancia del sistema
     * <b>Post:</b> Se habra creado una instancia de la empresa
     */
    public static Sistema getInstancia() {
        if (_instancia == null)
            _instancia = new Sistema();
        return _instancia;
    }

    // Crear cliente, vehiculos y empleados

    /**
     * Metodo que crea y agrega un cliente a la lista de clientes de la empresa.
     * <b>Pre </b> los parametros deben ser validos (strings distintas de null o ""), y objetos distintos de null
     * @throws UsuarioRepetidoException Se lanza cuando el nombre de usuario ya esta en uso
     * <b>Post:</b> El cliente habra sido creado con sus datos correspondientes de manera valida
     *
     * @param nombre_usuario  Nombre de usuario del cliente.
     * @param contrasenia     Contraseña del cliente.
     * @param nombre          Nombre del cliente.
     * @param apellido        Apellido del cliente.
     * @param telefono        Teléfono del cliente.
     * @param mail            Correo electrónico del cliente.
     * @param NombreCalle     Nombre de la calle de la dirección del cliente.
     * @param AlturaCalle     Altura de la calle de la dirección del cliente.
     * @param PisoCalle       Piso de la dirección del cliente.
     * @param LetraCalle      Letra de la dirección del cliente.
     * @param fecha_nacimiento Fecha de nacimiento del cliente.
     * @return El cliente creado y agregado.
     * @throws UsuarioRepetidoException Si el nombre de usuario ya está en uso.
     */
    public Cliente crearCliente(String nombre_usuario, String contrasenia,String nombre, String apellido, String telefono, String mail, String NombreCalle, String AlturaCalle, String PisoCalle, String LetraCalle, GregorianCalendar fecha_nacimiento) throws UsuarioRepetidoException {
        verificarClienteRepetido(nombre_usuario);
        Cliente c = new Cliente(nombre_usuario, contrasenia, nombre, apellido, telefono, mail, new Direccion(NombreCalle, AlturaCalle, PisoCalle, LetraCalle), fecha_nacimiento);
        this.clientesApp.put(nombre_usuario, c);
        return c;
    }

    /**
     * Metodo que crea y agrega un cliente ALEATORIO a la lista de clientes de la empresa.
     * <b>Pre </b> cantidad debe ser mayor a 0
     * <b>Post:</b> El cliente habra sido creado con sus datos correspondientes de manera valida
     */
    // TODO = BORRAR, REEMPLAZO ABAJO
    public ArrayList<ClienteRunnable> crearCientesRandom(int cantidad) {
        this.clientesActivos = cantidad;
        ArrayList<ClienteRunnable> cs = new ArrayList<>();
        for (int i = 0; i < cantidad ; i++) {
            ClienteRunnable cliente = new ClienteRunnable(viajes);
            this.clientes.put(cliente.getNombre_usuario(), cliente);
            cs.add(cliente);
        }
        return cs;
    }

    /* TODO = REEMPLAZAR POR ESTA FUNCION CUANDO SE IMPLEMENTEN LOS CAMPOS DE CANTIDAD DE VIAJES POR CLIENTE
    public ArrayList<ClienteRunnable> crearCientesRandom(int cantClientes, int cantViajes) {
        ArrayList<ClienteRunnable> cs = new ArrayList<>();
        for (int i = 0; i < cantClientes ; i++) {
            ClienteRunnable cliente = new ClienteRunnable(viajes, cantViajes);
            this.clientes.put(cliente.getNombre_usuario(), cliente);
            cs.add(cliente);
        }
        return cs;
    }
    * */

    /**
     * Metodo que crea y agrega una cantidad de choferes ALEATORIOS a la lista de choferes de la empresa.
     * <b>Pre </b> cantidad debe ser mayor a 0
     * <b>Post:</b> Los choferes habran sido creado con sus datos correspondientes de manera valida
     */
    // TODO = BORRAR, REEMPLAZO ABAJO
    public ArrayList<EmpleadoRunnable> crearChoferesRandom(int cantidad) {
        this.choferesActivos = cantidad;
        ArrayList<EmpleadoRunnable> empleados = new ArrayList<>();
        for(int i = 0; i < cantidad ; i++) {
            Random r = new Random();
            int tipo = r.nextInt(3);
            switch (tipo) {
                case 0:
                    ChoferContratado choferContratado = new ChoferContratado(viajes);
                    choferes.add(choferContratado);
                    empleados.add(choferContratado);
                    break;
                case 1:
                    ChoferPermanente choferPermanente = new ChoferPermanente(viajes);
                    choferes.add(choferPermanente);
                    empleados.add(choferPermanente);
                    break;
                case 2:
                    ChoferTemporario choferTemporario = new ChoferTemporario(viajes);
                    choferes.add(choferTemporario);
                    empleados.add(choferTemporario);
                    break;
            }
        }
        return empleados;
    }

    /* TODO = REEMPLAZAR POR ESTA FUNCION CUANDO SE IMPLEMENTEN LOS CAMPOS DE CANTIDAD DE CHOFERES ESPECIFICOS
    * public ArrayList<EmpleadoRunnable> crearChoferesRandom(int cantMax, int cantidadTemp, int cantPerm, int cantCont) {
        ArrayList<EmpleadoRunnable> empleados = new ArrayList<>();
        for(int i = 0; i < cantidadTemp ; i++) {
            ChoferTemporario choferTemporario = new ChoferTemporario(viajes, cantMax);
            choferes.add(choferTemporario);
            empleados.add(choferTemporario);

        }
        for(int i = 0; i < cantPerm ; i++) {
            ChoferPermanente choferpermanente = new ChoferPermanente(viajes, cantMax);
            choferes.add(choferpermanente);
            empleados.add(choferpermanente);
        }
        for(int i = 0; i < cantCont ; i++) {
            ChoferContratado choferContratado = new ChoferContratado(viajes, cantMax);
            choferes.add(choferContratado);
            empleados.add(choferContratado);
        }
        return empleados;
    }
    * */


    /**
     * Metodo que crea y agrega una cantidad de vehiculos ALEATORIOS a la lista de vehiculos de la empresa.
     * <b>Pre </b> cantidad debe ser mayor a 0
     * <b>Post:</b> Los vehiculos habran sido creado con sus datos correspondientes de manera valida
     */
    // TODO = BORRAR, REEMPLAZO ABAJO
    public void crearVehiculosRandom(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Random r = new Random();
            int tipo = r.nextInt(3);
            switch(tipo) {
                case(0) : vehiculos.add(VehiculoFactory.getVehiculo(MOTO, Utiles.generaPatente()));
                break;
                case(1) : vehiculos.add(VehiculoFactory.getVehiculo(COMBI, Utiles.generaPatente()));
                break;
                case(2) : vehiculos.add(VehiculoFactory.getVehiculo(AUTO, Utiles.generaPatente()));
                break;
            }
        }
    }

    /* TODO = REEMPLAZAR POR ESTA FUNCION CUANDO SE IMPLEMENTEN LOS CAMPOS DE CANTIDAD DE VEHICULOS ESPECIFICOS
    * public void crearVehiculosRandom(int cantMotos, int cantAutos, int cantCombis) {
        for (int i = 0; i < cantMotos; i++) {
            vehiculos.add(VehiculoFactory.getVehiculo(MOTO, Utiles.generaPatente()));
        }
        for (int i = 0; i < cantAutos; i++) {
            vehiculos.add(VehiculoFactory.getVehiculo(AUTO, Utiles.generaPatente()));
        }
        for (int i = 0; i < cantCombis; i++) {
            vehiculos.add(VehiculoFactory.getVehiculo(COMBI, Utiles.generaPatente()));
        }
    }
    *
    *
    *
    * */

    // Validacion Cliente

    /**
     * Clase que verifica que el nombre de usuario sea unico
     * <b>Post</b> El usuario tendra su nombre de usuario verificado
     * @throws UsuarioRepetidoException Excepcion lanzada en caso de que el nombre de usuario ya sea utilizado por otro usuario
     */
    public void verificarClienteRepetido(String nombre_usuario) throws UsuarioRepetidoException {
        if (this.clientesApp.containsKey(nombre_usuario) || this.clientes.containsKey(nombre_usuario)) {
            throw new UsuarioRepetidoException(nombre_usuario);
        }
    }

    /**
     * Metodo que verifica si un cliente existe
     * Los parametros no pueden ser null ni estar vacios
     * @throws UsuarioIncorrectoException Excepcion lanzada en caso de que el usuario no exista
     * <b>Post:</b> Se verificara si un usuario existe o no, devolviendo verdadero o falso
     */

    public void verificarExistenciaCliente(String u, String c) throws UsuarioIncorrectoException {
        if (this.clientesApp.containsKey(u)) {
            if (!this.clientesApp.get(u).getContrasenia().equalsIgnoreCase(c)) throw new UsuarioIncorrectoException(u, c);
        } else throw new UsuarioIncorrectoException(u, c);
    }

    // Getters y Setters basicos

    public boolean isClienteAppActivo() {
        return clienteAppActivo;
    }

    public int getClientesActivos() {return this.clientesActivos;}

    public int getChoferesActivos() {return this.choferesActivos;}

    public void setChoferesActivos(int c){this.choferesActivos=c;}

    public void setClientesActivos(int c){this.clientesActivos=c;}

    public HashMap<String, Cliente> getClientesApp() {
        return clientesApp;
    }

    public void setClientesApp(HashMap<String, Cliente> clientesApp) {
        this.clientesApp = clientesApp;
    }
    public BolsaDeViajes getBolsaDeViajes() {
        return viajes;
    }

    public void setViajes(BolsaDeViajes viajes) {
        this.viajes = viajes;
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setVehiculos(ArrayList<IVehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void setChoferes(ArrayList<Empleado> choferes) {
        this.choferes = choferes;
    }

    public ArrayList<Empleado> getChoferes() {
        return choferes;
    }

    public void eliminarse(Empleado e) {
        this.choferesActivos--;
        if (choferesActivos == 0) this.viajes.detenerSimulacion();
    }

    public void eliminarse(Cliente c) {
        this.clientesActivos--;
        if (clientesActivos == 0 && !clienteAppActivo) this.viajes.detenerSimulacion();
    }

    /**
     * Retorna un iterador que contiene todos los viajes realizados por un empleado chofer dado
     * <b>Pre: </b> El parametro chofer no puede ser null ni vacio
     * @param chofer : El empleado chofer del cual se desean obtener los viajes
     * @return : Un iterador que contiene todos los viajes realizados por el chofer especificado
     * <b>Post:</b> Los viajes realizados por el chofer se almacenaran en un iterador
     */
    public Iterator<IViaje> getViajesChofer(Empleado chofer) {
        ArrayList<IViaje> viajesChofer = new ArrayList<>();
        for (IViaje viaje : viajes.getViajes()) if (chofer == viaje.getChofer()) viajesChofer.add(viaje);
        return viajesChofer.iterator();
    }

    /**
     * Obtiene un iterador de los viajes asociados a un cliente dado
     *<b>Pre: </b>El parametro cliente no puede ser null ni vacio
     * @param cliente : El cliente para el cual se desean obtener los viajes.
     * @return : Un iterador de los viajes asociados al cliente.
     * <b>Post:</b>Los viajes realizados por el cliente se almacenaran en un iterador
     */
    public Iterator<IViaje> getViajesCliente(Cliente cliente) {
        ArrayList<IViaje> viajesCliente = new ArrayList<>();
        for (IViaje viaje : viajes.getViajes()) if (cliente == viaje.getPedido().getCliente()) viajesCliente.add(viaje);
        return viajesCliente.iterator();
    }

    public Iterator<IVehiculo> getIteratorVehiculos() {
        return vehiculos.iterator();
    }

    public ArrayList<IVehiculo> getVehiculos() {
        return vehiculos;
    }


    public HashMap<String, Cliente> getClientes() {
        return this.clientes;
    }

    public Cliente getCliente(String nombre_usuario) {
        return this.clientesApp.get(nombre_usuario);
    }

    public ArrayList<Cliente> getArrayClientes() {
        Collection<Cliente> values = clientes.values();
        // Creating an ArrayList of values
        ArrayList<Cliente> listaDeClientes = new ArrayList<>(values);
        return listaDeClientes;
    }

    public Iterator<IViaje> getIteratorViajes() {
        return viajes.getViajes().iterator();
    }

    public ArrayList<IViaje> getViajes() {
        return viajes.getViajes();
    }

    public Iterator<Empleado> getIteratorChoferes() {
        return choferes.iterator();
    }

    public Empleado getChofer(int i){
        return (Empleado) this.choferes.get(i);
    }

    public boolean getClienteAppActivo() {return this.clienteAppActivo;}

    public void setClienteAppActivo(boolean x) {
        this.clienteAppActivo=x;
        if (clientesActivos == 0) viajes.detenerSimulacion();
    }

    // Otros Getters y Setters
    /**
     * Metodo que calcular los sueldos totales de la fecha solicitada
     * @return : devuelve el sueldo en la fecha solicitada
     * <b>Post:</b> Se le cargaran los sueldos a los empleados
     */
    public double getSueldosTotales(GregorianCalendar fecha_inicio_mes){
        double sueldo = 0;
        for (Empleado c : this.choferes) {
            sueldo += c.getSueldo(fecha_inicio_mes, getViajesChofer(c));
        }
        return sueldo;
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
     * <b>Post:</b> Se guardaran todos los viajes realizados por el cliente en una fecha concreta
     */
    public String viajesClienteFecha(Cliente cliente, GregorianCalendar fechai, GregorianCalendar fechaf){
        final StringBuilder sb = new StringBuilder();
        sb.append("\n");
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
     * <b>Post:</b>  Se guardaran todos los viajes realizados por un chofer en una fecha concreta
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


    // Manejo de Pedidos y Viajes

    /**
     * Metodo que realiza el pedido solicitado por el cliente
     *<b>Pre:</b>Todos los parametros deben estar inicializados correctamente, no deben estar vacios ni ser nulos
     * @return devolvera el pedido creado con las especificaciones del cliente
     * <b>Post:</b> Se habra creado el pedido
     */
    public Pedido hacerPedido(GregorianCalendar fecha, String zona, boolean mascota, int cant_pasajeros, boolean equipaje, Cliente c, double d) {
        Pedido p = new Pedido(fecha, zona, mascota, cant_pasajeros, equipaje, c, d);
        viajes.lanzarPedido(p);
        return p;
    }

    /**
     * <b>Pre:</b> El parametro pedido no puede ser null ni estra vacio
     * @param pedido Contiene los requerimientos del pedido hecho por el cliente
     *<b>Post:</b> El viaje estara en estado de SOLICITADO
     */
    public IViaje solicitarViaje(Pedido pedido) {
        IViaje v = ViajeFactory.getViaje(pedido);
        v.setEstado_de_viaje("SOLICITADO");
        viajes.agregarViaje(v);
        return v;
    }


    /**
     * Valida la coherencia del pedido verificando ciertos criterios
     *<b>Pre: </b> el pedido no puede ser null
     * @param pedido El pedido que se desea validar con la informacion necesaria del mismo
     * @throws PedidoIncoherenteException Si el pedido no cumple con los criterios basicos
     * <b>Post:</b> El viaje sera aceptado o lanzara una excepcion en caso de no tener un vehiculo que cumpla con los requerimientos
     */
    public void solicitarAceptacion(Pedido pedido) throws PedidoIncoherenteException {
        if (pedido.getCant_pasajeros() > 10) throw new PedidoIncoherenteException("Cantidad de pasajeros mayor a 10");
        if (pedido.getCant_pasajeros() > 4 && pedido.isMascota()) throw new PedidoIncoherenteException("Mascotas no permitidas en combis");
    }

    // Listados

    /**
     * Genera un listado de los sueldos mensuales de todos los choferes para un mes
     *<b>Pre: </b> La fecha debe ser una fecha valida
     * @param fecha_inicio_mes : La fecha de inicio del mes para el cual se genera el listado de sueldos
     * @return : Cadena que contiene el listado de sueldos mensuales de los choferes, junto con el sueldo total a pagar
     * <b>Post:</b>  Se habra generado un listado con los sueldos a los empleados en la fecha solicitada
     */
    public String listadoSueldoMes(GregorianCalendar fecha_inicio_mes){
        final StringBuilder sb = new StringBuilder("Listado Sueldos Mensuales: \n");
        Iterator<Empleado> empleadoIterator = this.choferes.iterator();
        if (empleadoIterator.hasNext()) {
            do {
                Empleado empleado = empleadoIterator.next();
                sb.append(empleado.getNombre()).append(" ").append(empleado.getDni()).append("\t$ ").append(empleado.getSueldo(fecha_inicio_mes, getViajesChofer(empleado))).append("\n");
            } while (empleadoIterator.hasNext());
        }
        sb.append("Sueldo Total a pagar:\t$ ").append(this.getSueldosTotales(fecha_inicio_mes));
        return sb.toString();
    }

    /**
     * Funcion que genera un String que representa el listado de todos los clientes de la empresa.
     * @return : devuelve una variable de tipo String que contiene el listado de los clientes de la empresa.
     * <b>Post:</b> Se hbara generado un listado con los clientes
     */
    public String listado_clientes(){
        StringBuilder reporte = new StringBuilder();
        Iterator<Cliente> clientes = this.clientes.values().iterator();
        while (clientes.hasNext()){
            reporte.append("\n").append(clientes.next().toString());
        }
        return reporte.toString();
    }

    /**
     * Funcion que genera un String que representa el listado de todos los viajes de la empresa.
     * @return : devuelve una variable de tipo String que contiene el listado de los viajes de la empresa.
     * <b>Post:</b> Se habra generado un listado con los viajes
     */
    public String historico_viajes() throws CloneNotSupportedException {
        assert getInstancia().viajes.getViajes().isEmpty();
        ArrayList<IViaje> viajesClone = (ArrayList<IViaje>) getInstancia().viajes.getViajes().clone();
        viajesClone.clear();
        for (int i = 0; i < getInstancia().viajes.getViajes().size(); i++) {
            viajesClone.add(getInstancia().viajes.getViajes().get(i).clone());
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
     * <b>Post:</b> Se habra generado un listado con los choferes de la empresa
     */
    public String listado_choferes(){
        StringBuilder reporte = new StringBuilder();
        Iterator<Empleado> empleados = this.getIteratorChoferes();
        while (empleados.hasNext()){
            reporte.append("\n").append(empleados.next());
        }
        return reporte.toString();
    }

    /**
     * Funcion que genera un String que representa el listado de vehiculos de la empresa.
     * @return : devuelve una variable de tipo String que contiene el listado de los vehiculos de la empresa.
     * <b>Post: </b> Se habra generado un listado con los vehiculos utilizados por la empresa
     */
    public String listado_vehiculos(){
        StringBuilder reporte = new StringBuilder();
        Iterator<IVehiculo> vehiculos = this.getIteratorVehiculos();
        while (vehiculos.hasNext()){
            reporte.append("\n").append(vehiculos.next().toString());
        }
        return reporte.toString();
    }

    /**
     * Funcion que calcula los puntajes de todos los choferes en un periodo de un mes de trabajo. Se conoce que la fecha limite es la actual, y se pasa por parametro la fecha de inicio del mes a analizar.
     *<b>Pre:</b> El parametro no puede ser null ni estar vacio, debe ser una fecha valida
     * @param pricipio_mes : parametro de tipo GregorianCalendar que representa la fecha de inicio de mes que acaba de terminar
     *<b>Post:</b> Se habra indicado el maximo puntaje del mes que se solicito
     */
    public void puntaje_mes_finalizado(GregorianCalendar pricipio_mes){
        double max = 0;
        Empleado maxKM = null;
        Iterator<Empleado> empleadoIterator = getIteratorChoferes();
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

    // Sistema

    public void cargaSistema(){
        SistemaDTO sistemaDTO= new SistemaDTO();
        sistemaDTO = SistemaXML.cargaSistema();
        Sistema.getInstancia().setChoferes(sistemaDTO.getChoferes());
        Sistema.getInstancia().setVehiculos(sistemaDTO.getVehiculos());
        Sistema.getInstancia().setClientes(sistemaDTO.getClientes());
        Sistema.getInstancia().setClientesApp(sistemaDTO.getClientesApp());
        Sistema.getInstancia().setClientesActivos(sistemaDTO.getClientes().size());
        Sistema.getInstancia().setChoferesActivos(sistemaDTO.getChoferes().size());
    }

    public void guardaSistema(){
        SistemaXML.grabaSistema();
    }

    public void detenerSimulacion() {
        this.viajes.detenerSimulacion();
    }

    public void StartAll() {
        // Ejecutar todos los choferes como hilos
        for (Empleado chofer : choferes) {
            ((EmpleadoRunnable) chofer).setBolsa(Sistema.getInstancia().getBolsaDeViajes());
            Thread choferThread = new Thread((EmpleadoRunnable) chofer);
            choferThread.start();
        }

        // Ejecutar todos los clientes como hilos
        for (Cliente cliente : clientes.values()) {
            ((ClienteRunnable) cliente).setBolsa(Sistema.getInstancia().getBolsaDeViajes());
            Thread clienteThread = new Thread((ClienteRunnable) cliente);
            clienteThread.start();
        }
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