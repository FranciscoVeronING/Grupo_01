package Exception;

/**
<br>Clase padre de las excepciones FaltaDeChofer,FaltaDeVehiculo,PedidoInchoerente que se extiende de la clase Exception
<br> Esta clase es usada como padre de toda aquella excepcion en la que no se pueda atender el pedido
 */
public class PedidoImposibleException extends Exception{

    /**
     *<br>
     *Metodo que representa el caso en el que no se pueda atender el pedido por algun motivo como falta de choferes,vehiculos o que el pedido sea inconmpatible con el servicio brindado
    <b> Pre: <b> El parametro arg0 no debe ser null
    @param arg0 Parametro que indica a que se debe que se haya lanzado la excepcion
     */
    public PedidoImposibleException(String arg0){

        super(arg0);
   }

}
