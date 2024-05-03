package models;

public class Moto extends Vehiculo {
    public Moto(String patente) {
        super(patente, 1, false, false);
    }

    @Override
    public Integer getPrioridad(Pedido pedido) {
        int puntaje = 0;
        if (!(validarVehiculo(pedido))) return 0;
        if (pedido.getCant_pasajeros() == 1 && !pedido.isEquipaje() && !pedido.isMascota()) puntaje = 1000;
        return puntaje;
    }

    @Override
    public String toString() {
        return "Moto " + super.toString();
    }
}
