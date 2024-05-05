package models;

import Exception.PedidoIncoherenteException;

/**
 * Clase que representa el vehiculo utilizado para llevar a cabo el viaje
 * Implementa la interfaz IVehiculo y cloneable
 */
public abstract class Vehiculo implements IVehiculo, Cloneable {
    protected String patente;
    protected int cant_max_pasajeros;
    protected boolean petfriendly;
    protected boolean baul;

    /**
     * Constructor para setear los parametros de la clase vehiculo
     * <b>Pre: </> El parametro patente no puede ser null ni estar vacio
     * @param patente Almacena la patente del vehiculo a utilizar
     * <b>Pre: </>    CantPax tiene que ser mayor que cero
     * @param cantPax Representa la cantidad de pasajeros
     * @param PF Indica si el viaje involucra mascota
     * @param baul Indica si se necesita que el vehiculo tenga baul
     */
    public Vehiculo(String patente, int cantPax, boolean PF, boolean baul) {
        this.patente = patente;
        this.cant_max_pasajeros = cantPax;
        this.petfriendly = PF;
        this.baul = baul;
    }

    /**
     * Metodo utilizado para corrobar si el vehiculo cumple con el requisito de tener baul en caso de ser necesario
     * <b>Pre: </>Pedido no puede ser null ni estar vacio
     * @param pedido Tiene la informacion necesaria sobre los requisitos del pedido
     * @return Devuelve si el vehiculo es apto o no segun los requisitos del pedido
     */
    public boolean validarBaul(Pedido pedido) {
        if (this.baul) return true;
        if (!this.baul && pedido.isEquipaje()) return false;
        else return true;
    }

    /**
     * Metodo utilizado para corrobar si el vehiculo cumple con el requisito de ser pet friendly en caso de ser necesario
     * <b>Pre: </>Pedido no puede ser null ni estar vacio
     * @param pedido Tiene la informacion necesaria sobre los requisitos del pedido
     * @return Devuelve si el vehiculo es apto o no segun los requisitos del pedido
     */
    public boolean validarPF(Pedido pedido) {
        if (this.petfriendly) return true;
        if (!this.petfriendly && pedido.isMascota()) return false;
        else return true;
    }
    /**
     * Metodo utilizado para corrobar si el vehiculo cumple con el requisito de la cantidad de pasajeros
     * <b>Pre: </>Pedido no puede ser null ni estar vacio
     * @param pedido Tiene la informacion necesaria sobre los requisitos del pedido
     * @return Devuelve si el vehiculo es apto o no segun los requisitos del pedido
     */
    public boolean validarPax(Pedido pedido) {
        return this.cant_max_pasajeros >= pedido.getCant_pasajeros();
    }

    /**
     * Este metodo verifica si el vehiculo cumple o no con todos los requisitos solicitados en el pedido
     * <b>Pre: </>Pedido no puede ser null ni estar vacio
     * @param pedido Tiene la informacion necesaria sobre los requisitos del pedido
     * @return Devuelve si el vehiculo es apto o no segun los requisitos del pedido
     */
    public boolean validarVehiculo(Pedido pedido) {
        boolean petFriendly = validarPF(pedido);
        boolean baul = validarBaul(pedido);
        boolean pax = validarPax(pedido);
        return petFriendly && baul && pax;
    }

    public String getPatente() {
        return patente;
    }

    public int getCant_max_pasajeros() {
        return cant_max_pasajeros;
    }

    public boolean isPetfriendly() {
        return petfriendly;
    }

    public boolean isBaul() {
        return baul;

    }

    @Override
    public String toString() {
        return "{" +
                "patente='" + patente + '\'' +
                ", cant_max_pasajeros=" + cant_max_pasajeros +
                ", petfriendly=" + petfriendly +
                ", baul=" + baul +
                '}';
    }

    @Override
    public Vehiculo clone() {
        try {
            Vehiculo clone = (Vehiculo) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
