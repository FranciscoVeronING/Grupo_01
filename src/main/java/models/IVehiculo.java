package models;


import java.io.Serializable;

/**
 * La interfaz IVehiculo define el contrato para los vehículos que pueden ser clonados y serializados.
 */
public interface  IVehiculo extends Cloneable, Serializable  {
  Integer getPrioridad(Pedido pedido);
  boolean validarVehiculo(Pedido pedido);
  Vehiculo clone()throws CloneNotSupportedException;
  public void setOcupado(boolean x);
  public boolean isOcupado();
}
