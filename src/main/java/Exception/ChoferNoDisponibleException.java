package Exception;

/**
 * <br>Clase que representa la excepcion de que no haya un chofer disponible para tomar el pedido. se extiende de Exception
 */
public class ChoferNoDisponibleException extends PedidoImposibleException {

    /**
     *<br>
     *Metodo que representa la excepcion falta de choferes en el caso que no haya ninguno disponible
     * <b> Pre: </b> El parametro arg0 no debe ser null
     *@param arg0 : Parametro que indica a que se debe que se haya lanzado la excepcion
     */
    public ChoferNoDisponibleException(String arg0){
        super(arg0);
    }
}
