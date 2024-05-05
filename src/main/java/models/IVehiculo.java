package models;


public interface  IVehiculo extends Cloneable{
  Integer getPrioridad(Pedido pedido);
  boolean validarVehiculo(Pedido pedido);
  Vehiculo clone()throws CloneNotSupportedException;
  public void setOcupado(boolean x);
  public boolean isOcupado();
}
