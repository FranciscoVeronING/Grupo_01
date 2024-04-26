package models;

public class ViajeFactory {

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

        if (pedido.isEquipaje()) respuesta = new DecoratorMascota(respuesta);

        return respuesta;
    }
}
