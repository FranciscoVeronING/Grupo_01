package models;

public class DecoratorCombi extends DecoratorVehiculo{
    @Override
    public Integer getPrioridad(Pedido pedido) {
        return 0;
    }
}
