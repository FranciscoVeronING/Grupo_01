package Exception;

/**
 * <br>Clase que representa la excepcion de que no haya un chofer para tomar el pedido. Se extiende de la clase PedidoImposibleException
 */
public class FaltaDeChoferException extends PedidoImposibleException{

    /**
     *<br>
     *Metodo que representa la excepcion falta de choferes
     *<b> Pre: </b> El parametro arg0 no debe ser null
     *@param arg0 : Parametro que indica a que se debe que se haya lanzado la excepcion
     */
    public FaltaDeChoferException(String arg0){
        super(arg0);
    }
}
