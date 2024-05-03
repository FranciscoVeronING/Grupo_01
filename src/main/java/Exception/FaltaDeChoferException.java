package Exception;

/**
 * <br>Clase que representa el caso de que no haya un chofer disponible para tomar el pedido que se extiende de la clase PedidoImposibleException
 */
public class FaltaDeChoferException extends PedidoImposibleException{

    /**
     *<br>
     *Metodo que representa la excepcion falta de choferes en el caso que no haya ninguno disponible
     <b> Pre: <b> El parametro arg0 no debe ser null
    @param arg0 Parametro que indica a que se debe que se haya lanzado la excepcion
     * */
    public FaltaDeChoferException(String arg0){
        super(arg0);
    }
}
