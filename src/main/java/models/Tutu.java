package models;

/**
 * Clase que representa al auto que se extiende de vehiculo
 */
public class Tutu extends Vehiculo {
    public Tutu(String patente) {
        super(patente, 4, true, true);
    }

    /**
     * Metodo utilizado para calcular la prioridad que tendra el auto para ser utilizada ante los requisitos del usuario
     * <b>Pre:</> pedido no puede ser null ni estar vacio
     * @param pedido Parametro utilizado para almacenar las solicitud de viaje del usuario
     * @return Devuelve un valor que representa la prioridad del auto ante los otros vehiculos
     */
    @Override
    public Integer getPrioridad(Pedido pedido) {
        int puntaje = 0;
        if (!(validarVehiculo(pedido)))
            return 0;
        if (pedido.isEquipaje())
            puntaje = 40 * pedido.getCant_pasajeros();
        else
            puntaje = 30;
        return Integer.valueOf(puntaje);
    }

    @Override
    public String toString() {
        return "Tutu " + super.toString();
    }
}
