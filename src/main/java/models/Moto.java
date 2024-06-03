package models;

/**
 * Clase extendida de vehiculo que representa la moto
 * <b>Pre:</b> Las porpiedades que adquiere la moto de la clase Vehiculo son validas ya pre establecidas con anterioridad en la superclase
 */
public class Moto extends Vehiculo {
    public Moto(String patente) {
        super(patente, 1, false, false);
    }

    /**
     * Metodo utilizado para calcular la prioridad que tendra la moto para ser utilizada ante los requisitos del usuario
     * <b>Pre:</b> pedido no puede ser null ni estar vacio
     * @param pedido Parametro utilizado para almacenar las solicitud de viaje del usuario
     * <b>Post:</b> Se le asignara una prioridad al objeto moto segun la solicitudes del viaje
     * @return Devuelve un valor que representa la prioridad de la moto ante los otros vehiculos
     */
    @Override
    public Integer getPrioridad(Pedido pedido) {
        int puntaje = 0;
        if (!(validarVehiculo(pedido))) return null;
        if (pedido.getCant_pasajeros() == 1 && !pedido.isEquipaje() && !pedido.isMascota()) puntaje = 1000;
        return puntaje;
    }

    @Override
    public String toString() {
        return "Moto " + super.toString();
    }
}
