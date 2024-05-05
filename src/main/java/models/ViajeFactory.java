package models;

/**
 * Metodo que aplica el patron Factory a la clase viaje
 */
public class ViajeFactory {

    /**
     * Metodo que averigua las condiciones del viaje solicitado y asi generar el viaje segun su zona
     * @param pedido Parametro que almacena la informacion del viaje que se esta solicitando
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
