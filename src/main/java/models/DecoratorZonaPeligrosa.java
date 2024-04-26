package models;

public class DecoratorZonaPeligrosa extends DecoratorZonas {
    public DecoratorZonaPeligrosa(IViaje encapsulado) {
        super(encapsulado);
    }

    @Override
    public double calcularCostoViaje() {
        double base = getCosto_base();
        double aumentoPax = getCosto_base() * getPedido().getCant_pasajeros() * 0.1;
        double aumentoKm = getCosto_viaje() * getDistancia() * 0.2;
        return base + aumentoKm + aumentoPax;
    }
}
