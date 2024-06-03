package models;

/**
 * Clase utilizada para permitir crear viajes sin necesidad de conocer los detalles específicos de su implementación
 */
public class ViajeFactory {

    /**
     * Metodo que averigua las condiciones del viaje solicitado y asi generar el viaje segun su zona y especificaciones de mascota y equipaje
     * @param pedido Parametro que almacena la informacion del viaje que se esta solicitando
     *  <b>Post: </b> Se habra devuelto el tipo de viaje con los requisitos que el cliente solicito
     */
    public static IViaje getViaje(Pedido pedido) {
        IViaje respuesta = new Viaje(pedido);

        // Genero viaje segun su zona
        if (pedido.getZona().equalsIgnoreCase("SIN ASFALTAR"))
            respuesta = new DecoratorZonaSinAsfaltar(respuesta);
        if (pedido.getZona().equalsIgnoreCase("ESTANDAR"))
            respuesta = new DecoratorZonaEstandar(respuesta);
        if (pedido.getZona().equalsIgnoreCase("PELIGROSA"))
            respuesta = new DecoratorZonaPeligrosa(respuesta);

        if (pedido.isMascota()) respuesta = new DecoratorMascota(respuesta);

        if (pedido.isEquipaje()) respuesta = new DecoratorEquipaje(respuesta);

        return respuesta;
    }
}
