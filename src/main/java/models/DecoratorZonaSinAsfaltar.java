package models;

public class DecoratorZonaSinAsfaltar extends DecoratorZonas {
    public DecoratorZonaSinAsfaltar(IViaje encapsulado) {
        super(encapsulado);
    }

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
