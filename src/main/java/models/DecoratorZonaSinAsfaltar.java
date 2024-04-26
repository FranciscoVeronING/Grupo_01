package models;

public class DecoratorZonaSinAsfaltar extends DecoratorZonas {
    public DecoratorZonaSinAsfaltar(IViaje encapsulado) {
        super(encapsulado);
    }

    @Override
    public double calcularCostoViaje() {
        double base = getCosto_base();
        double aumentoPax = getCosto_base() * getPedido().getCant_pasajeros() * 0.2;
        double aumentoKm = getCosto_viaje() * getDistancia() * 0.15;
        return base + aumentoKm + aumentoPax;
    }
}
