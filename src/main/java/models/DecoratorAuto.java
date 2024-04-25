package models;

public class DecoratorAuto extends DecoratorVehiculo{
    @Override
    public int getPrioridad(Pedido pedido) {
        return 0;
    }
}
