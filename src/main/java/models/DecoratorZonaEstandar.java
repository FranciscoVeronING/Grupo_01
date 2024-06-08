package models;

/**
 * Clase utilizada para cuando la zona del pedido es una zona estandar
 * */
public class DecoratorZonaEstandar extends DecoratorZonas {
    public DecoratorZonaEstandar(IViaje encapsulado) {
        super(encapsulado);
    }

    /**
     *
     * Calcula el costo del viaje en el caso que el mismo sea en una zona estandar
     * <b>Post:</b> El costo del viaje sera incrementado segun el costo de la zona estandar del viaje solictiado por el usuario
     */
    @Override
    public void calcularCostoViaje() {
        super.calcularCostoViaje();
        double base = getCosto_viaje();
        double aumentoPax = getCosto_base() * getPedido().getCant_pasajeros() * 0.1;
        double aumentoKm = getCosto_base() * getDistancia() * 0.1;
        encapsulado.setCosto_viaje(base + aumentoKm + aumentoPax);
    }

    @Override
    public Viaje getViaje() {
        return null;
    }

    @Override
    public String toString() {
        return encapsulado.toString() + " en zona estandar ";
    }

}

