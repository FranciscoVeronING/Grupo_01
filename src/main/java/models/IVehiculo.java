package models;


import java.io.Serializable;

public interface  IVehiculo extends Cloneable, Serializable  {
  Integer getPrioridad(Pedido pedido);
  boolean validarVehiculo(Pedido pedido);
  Vehiculo clone()throws CloneNotSupportedException;
  public void setOcupado(boolean x);
  public boolean isOcupado();
}
