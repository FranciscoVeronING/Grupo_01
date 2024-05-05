package models;


public interface  IVehiculo extends Cloneable{
  public Integer getPrioridad(Pedido pedido);
  public boolean validarVehiculo(Pedido pedido);
  public Vehiculo clone()throws CloneNotSupportedException;
}
