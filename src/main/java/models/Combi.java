package models;

/**
 * Clase que representa el vehiculo Combi que es extendida de Vehiculo
 * <b>Pre:</b> Las porpiedades que adquiere de la clase Vehiculo son validas ya pre establecido con anterioridad en las superclase
 */
public class Combi extends Vehiculo {
    public Combi(String patente) {
        super(patente, 10, false, true);
    }

    /**
     * Metodo utilizado para calcular la prioridad que tendra la combi para ser utilizada ante los requisitos del usuario
     * <b>Pre:</b> El parametro pedido no puede ser null ni estar vacio
     * @param pedido : Parametro utilizado para almacenar las solicitud de viaje del usuario
     * @return : Devuelve un valor que representa la prioridad de la combi ante los otros vehiculos
     * <b>Post:</b> La combi adquirira una cierta prioridad segun la solicitud del pedido
     */
    @Override
    public Integer getPrioridad(Pedido pedido) {
        int puntaje;
        if (!(validarVehiculo(pedido))) return null;
        puntaje = pedido.getCant_pasajeros()*10;
        if (pedido.isEquipaje()) puntaje += 100;
        return puntaje;
    }

    @Override
    public String toString() {
        return "Combi " + super.toString();
    }
}
