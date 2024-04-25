package models;

public class DecoratorCombi extends DecoratorVehiculo{
    @Override
    public int getPrioridad(Pedido pedido) {
        return 0;
    }
}
