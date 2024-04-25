package models;

public class Tutu extends Vehiculo {
    public Tutu(String patente) {
        super(patente, 4, true, true);
    }

    @Override
    public Integer getPrioridad(Pedido pedido) {
        int puntaje = 0;
        if (!(validarVehiculo(pedido))) return null;
        if (pedido.isEquipaje()) puntaje = 40 * pedido.getCant_pasajeros();
        else puntaje = 30;
        return Integer.valueOf(puntaje);
    }
}
