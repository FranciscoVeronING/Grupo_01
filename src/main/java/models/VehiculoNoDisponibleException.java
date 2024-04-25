package models;

public class VehiculoNoDisponibleException extends Exception{
    public VehiculoNoDisponibleException() {
        super("Pedido RECHAZADO: Vehiculo no Disponible");
    }
}
