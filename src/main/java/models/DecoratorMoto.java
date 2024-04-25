package models;

public class DecoratorMoto extends DecoratorVehiculo{
    @Override
    public Integer getPrioridad(Pedido pedido) {
        return 0;
    }
}
