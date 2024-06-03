package models;

import java.io.Serializable;

/**
 *  La interfaz IViaje define el contrato para los vehículos que pueden ser clonados y serializados.
 */
public interface IViaje extends Cloneable, Serializable {
 double getCosto_viaje();
 void setCosto_viaje(double valor);
 Pedido getPedido();
 void setPedido(Pedido pedido);
 void setEstado_de_viaje(String estado_de_viaje);
 String getEstado_de_viaje();
 Empleado getChofer();
 void setChofer(Empleado chofer);
 IVehiculo getVehiculo();
 void setVehiculo(IVehiculo vehiculo);
 void setCosto_base(double costo_base);
  double getCosto_base();
  void calcularCostoViaje();

  void pagarse();
  void  finalizarse();
  IViaje clone() throws CloneNotSupportedException;
  int compareTo(Object o);
}
