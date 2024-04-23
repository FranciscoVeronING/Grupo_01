package models;

public interface  IVehiculo {
  public Integer getPrioridad(Pedido pedido);
  public boolean validarVehiculo(Pedido pedido);
  
}
