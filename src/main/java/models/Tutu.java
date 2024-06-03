package models;

/**
 * Clase que representa al auto que se extiende de vehiculo
 */
public class Tutu extends Vehiculo {
    /**
     * Constructor de tutu para amlmacenar informacion a cerca de sus cualidades
     * <b>Pre: </b> patente no puede ser null ni vacio
     * @param patente : almacena informacion del vehiculo a cerca de la patente del mismo
     */
    public Tutu(String patente) {
        super(patente, 4, true, true);
    }

    /**
     * Metodo utilizado para calcular la prioridad que tendra el auto para ser utilizada ante los requisitos del usuario
     * <b>Pre:</b> pedido no puede ser null ni estar vacio
     * <b>Post:</b> Se le asignara una prioridad al vehiculo tutu segun que tan adecuado sea este vehiculo ante la solicitudes del usuario
     * @param pedido : Parametro utilizado para almacenar las solicitud de viaje del usuario
     * @return : Devuelve un valor que representa la prioridad del auto ante los otros vehiculos
     */
    @Override
    public Integer getPrioridad(Pedido pedido) {
        int puntaje;
        if (!(validarVehiculo(pedido)))
            return null;
        if (pedido.isEquipaje())
            puntaje = 40 * pedido.getCant_pasajeros();
        else
            puntaje = 30;
        return puntaje;
    }

    @Override
    public String toString() {
        return "Tutu " + super.toString();
    }
}
