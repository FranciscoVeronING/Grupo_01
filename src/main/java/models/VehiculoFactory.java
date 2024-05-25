package models;

import java.io.Serializable;

/**
 * Clase utilizada para verificar si el vehiculo a utilizar sera un auto, combi o moto
 */
public class VehiculoFactory implements Serializable {
    /**
     * Metodo que compara si el vehiculo es de tipo auto,combi o moto
     * <b>Pre: </b> Vehiculo no puede ser null ni estar vacio
     * @param vehiculo : Almacena que tipo de vehiculo se utilizara segun los requisitos y la propiedad de cada uno
     * * <b>Pre: </b> Patente no puede ser null ni estar vacio
     * @param patente : Almacena la patente del vehiculo a utiizar
     * @return : Devuelve el tipo de vehiculo que se utilizara
     */
    public static IVehiculo getVehiculo(String vehiculo,String patente) {
        IVehiculo encapsulado = null;
        if (vehiculo.equalsIgnoreCase("auto"))
            encapsulado = new Tutu(patente);
        else if (vehiculo.equalsIgnoreCase("combi")) {
            encapsulado = new Combi(patente);
        }
        else if (vehiculo.equalsIgnoreCase("moto")) {
            encapsulado = new Moto(patente);
        }
        return encapsulado;
    }
}
