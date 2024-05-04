package Exception;

/**
<br> Clase que representa la excepcion en la que un usuario solicite un viaje que no se pueda llevar a cabo porque la solicitud del mismo es imposible de llevar a cabo con los servicios brindados por la empresa
 */
public class PedidoIncoherenteException extends PedidoImposibleException{

    /**
    <br>Metodo utilizado para lanzar una excepcion en el caso que el cliente pida un servicio que no se pueda llevar a cabo con los vehiculos de la empresa y sus especificaciones
    <b> Pre: <b> El parametro arg0 no debe ser null
    @param arg0 Parametro que indica a que se debe que se haya lanzado la excepcion
     */
    public PedidoIncoherenteException(String arg0) {
        super(arg0);
    }
}
