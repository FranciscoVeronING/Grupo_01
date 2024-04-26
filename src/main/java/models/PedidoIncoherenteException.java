package models;

public class PedidoIncoherenteException extends Exception{
  
    public PedidoIncoherenteException() {
        super("No existe un vehiculo que pueda cumplir con los requisitos del viaje");
    }
}
