package models;

public class DecoratorMoto extends DecoratorVehiculo{
    @Override
    public int getPrioridad(Pedido pedido) {
        return 0;
    }
}
