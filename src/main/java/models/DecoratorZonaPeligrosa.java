package models;

public class DecoratorZonaPeligrosa extends DecoratorZonas {
    public DecoratorZonaPeligrosa(IViaje encapsulado) {
        super(encapsulado);
    }

    @Override
    public void calcularCostoViaje() {
        super.calcularCostoViaje();
        double base = getCosto_viaje();
        double aumentoPax = getCosto_base() * getPedido().getCant_pasajeros() * 0.1;
        double aumentoKm = getCosto_base() * getDistancia() * 0.2;
        setCosto_viaje(base + aumentoKm + aumentoPax);
    }

    @Override
    public String toString() {
        return encapsulado.toString() + " en zona peligrosa ";
    }
}
