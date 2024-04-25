package models;

public class Combi extends Vehiculo {
    public Combi(String patente) {
        super(patente, 10, false, true);
    }

    @Override
    public Integer getPrioridad(Pedido pedido) {
        int puntaje = 0;
        if (!(validarVehiculo(pedido))) return null;
        puntaje = pedido.getCant_pasajeros()*10;
        if (pedido.isEquipaje()) puntaje += 100;
        return Integer.valueOf(puntaje);
    }
}
