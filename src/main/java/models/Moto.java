package models;

/**
 * Clase extendida de vehiculo que representa la moto
 */
public class Moto extends Vehiculo {
    public Moto(String patente) {
        super(patente, 1, false, false);
    }

    /**
     * Metodo utilizado para calcular la prioridad que tendra la moto para ser utilizada ante los requisitos del usuario
     * <b>Pre:</b> pedido no puede ser null ni estar vacio
     * @param pedido Parametro utilizado para almacenar las solicitud de viaje del usuario
     * @return Devuelve un valor que representa la prioridad de la moto ante los otros vehiculos
     */
    @Override
    public Integer getPrioridad(Pedido pedido) {
        int puntaje = 0;
        if (!(validarVehiculo(pedido))) return 0;
        if (pedido.getCant_pasajeros() == 1 && !pedido.isEquipaje() && !pedido.isMascota()) puntaje = 1000;
        return puntaje;
    }

    @Override
    public String toString() {
        return "Moto " + super.toString();
    }
}
