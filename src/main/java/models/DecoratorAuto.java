package models;

public class DecoratorAuto extends DecoratorVehiculo{
    @Override
    public Integer getPrioridad(Pedido pedido) {
        return 0;
    }
}
