package models;

/**
 * Clase que tiene en cuenta en caso de que el pedido sea en una zona sin asfaltar
 */
public class DecoratorZonaSinAsfaltar extends DecoratorZonas {

    public DecoratorZonaSinAsfaltar(IViaje encapsulado) {
        super(encapsulado);
    }

    /**
     * Metodo que calcula el costo del viaje en una zona sin asfaltar
     */
    @Override
    public void calcularCostoViaje() {
        super.calcularCostoViaje();
        double base = getCosto_viaje();
        double aumentoPax = getCosto_base() * getPedido().getCant_pasajeros() * 0.2;
        double aumentoKm = getCosto_base() * getDistancia() * 0.15;
        setCosto_viaje(base + aumentoKm + aumentoPax);
    }

    @Override
    public String toString() {
        return encapsulado.toString() + " en zona sin asfaltar ";
    }
}
