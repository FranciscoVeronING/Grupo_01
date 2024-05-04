package Exception;

/**
 * <br>Clase que representa el caso de que no haya un vehiculo en el sistema para tomar el pedido que se extiende de la clase PedidoImposibleException
 */
public class FaltaDeVehiculoException extends PedidoImposibleException{

    /**
     *<br>
     *Metodo que representa la excepcion falta de vehiculos en el caso que no haya en el sistema
    <b> Pre: <b> El parametro arg0 no debe ser null
    @param arg0 Parametro que indica a que se debe que se haya lanzado la excepcion
     */
    public FaltaDeVehiculoException(String arg0){
        super(arg0);
    }
}
