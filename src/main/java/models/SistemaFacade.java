package models;

import Exception.VehiculoNoDisponibleException;
import Exception.ChoferNoDisponibleException;
import Exception.UsuarioRepetidoException;
import Exception.PedidoIncoherenteException;

import java.io.Serializable;
import java.util.*;

public class SistemaFacade implements Serializable {
    private final Sistema sistema;

    public SistemaFacade() {
        this.sistema = Sistema.getInstancia();
    }

    public void agregarChofer(Empleado chofer) {
        sistema.agregarChofer(chofer);
    }

    public void agregarVehiculo(IVehiculo vehiculo) {
        sistema.agregarVehiculo(vehiculo);
    }

    public void agregarCliente(Cliente cliente) throws UsuarioRepetidoException {
        sistema.agregarCliente(cliente);
    }

    public String listadoClientes() {
        return sistema.listado_clientes();
    }

    public String listadoViajes() throws CloneNotSupportedException {
        return sistema.historico_viajes();
    }

    public String listadoChoferes() {
        return sistema.listado_choferes();
    }

    public String listadoVehiculos() {
        return sistema.listado_vehiculos();
    }

    public IViaje asignarPedidoVehiculo(Pedido pedido) throws VehiculoNoDisponibleException, PedidoIncoherenteException {
        return sistema.asignarPedidoVehiculo(pedido);
    }

    public void asignarViajeChofer(IViaje viaje) throws ChoferNoDisponibleException {
        sistema.asignarViajeChofer(viaje);
    }

    public void finalizarViaje(IViaje viajeActivo) {
        sistema.finalizarViaje(viajeActivo);
    }

    public void pagarViaje(IViaje viaje) {
        sistema.pagarViaje(viaje);
    }

    public String viajesClienteFecha(Cliente cliente, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
        return sistema.viajesClienteFecha(cliente, fechaInicio, fechaFin);
    }

    public String viajesChoferesFecha(Empleado chofer, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
        return sistema.viajesChoferesFecha(chofer, fechaInicio, fechaFin);
    }

    public String listadoSueldoMes(GregorianCalendar fechaInicioMes) {
        return sistema.listadoSueldoMes(fechaInicioMes);
    }
}
