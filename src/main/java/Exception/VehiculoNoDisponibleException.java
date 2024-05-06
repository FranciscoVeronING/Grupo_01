package Exception;

/**
 * <br>Clase que representa la excepcion de que no haya un vehiculo disponible para tomar el pedido. Se extiende de la clase PedidoImposibleException
 */
public class VehiculoNoDisponibleException extends PedidoImposibleException {

    /**
     *<br>
     * Metodo que representa la excepcion falta de vehiculos en el caso que no haya ninguno disponible
     * <b> Pre: </b> El parametro arg0 no debe ser null
     * @param arg0 : Parametro que indica a que se debe que se haya lanzado la excepcion
     */

    public VehiculoNoDisponibleException(String arg0){
        super(arg0);
    }
}
