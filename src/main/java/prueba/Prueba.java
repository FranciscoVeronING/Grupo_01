package prueba;

import models.*;
import Exception.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Prueba {
    public Prueba() {
        super();
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        Sistema e = Sistema.getInstancia();

        IVehiculo moto1 = e.crearVehiculo("MOTO", "AAA111");
        IVehiculo moto2 = e.crearVehiculo("MOTO", "BBB222");
        IVehiculo auto1 = e.crearVehiculo("AUTO", "CCC333");
        IVehiculo auto2 = e.crearVehiculo("AUTO", "DDD444");
        IVehiculo auto3 = e.crearVehiculo("AUTO", "DDD445");
        IVehiculo combi1 = e.crearVehiculo("COMBI", "EEE555");
        IVehiculo combi2 = e.crearVehiculo("COMBI", "FFF666");

        Empleado choferTemporario = e.crearChoferTemporario("12345678", "Maria", 0.1, 0.1);
        Empleado choferTemporario2 = e.crearChoferTemporario("40123421", "Mario", 0.2, 0.2);
        Empleado choferPermanente = e.crearChoferPermanente("98746532", "Juan", 0.1, new GregorianCalendar(1995,Calendar.JANUARY,16), 20,2);
        Empleado choferPermanente2 = e.crearChoferTemporario("9421322", "Juanito", 0.1, new GregorianCalendar(1982,Calendar.FEBRUARY,16), 5,0);
        Empleado choferContratado = e.crearChoferContratado("74353656", "Pepe", 0.6);
        Empleado choferContratado2 = e.crearChoferContratado("77446688", "Grillo", 0.5);

        try {
        Cliente c1 = e.crearCliente("Juan_Perez", "1234", "Juan", "Perez", "12345678", "juanperez@gmail.com", new Direccion("Calle Falsa", "123", "8","A"), new GregorianCalendar(1990, Calendar.MAY, 10));
        Cliente c2 = e.crearCliente("Maria_Gomez", "5678", "Maria", "Gomez", "87654321", "mariagomez@gmail.com", new Direccion("Calle Falsa", "456", null,null), new GregorianCalendar(1995, Calendar.FEBRUARY, 16));
        Cliente c3 = e.crearCliente("Maria_Gomeria", "asdds", "Maria", "Gomerias", "12345467", "mariagomerias@gmail.com", new Direccion("Calle Falsa", "567", null,null), new GregorianCalendar(1965, Calendar.FEBRUARY, 16));
        Cliente c4 = e.crearCliente("CapitanLeonel", "1234", "Capitan", "Leonel", "12345678", "juanperez@gmail.com", new Direccion("Calle Falsa", "123", "8","A"), new GregorianCalendar(1990, Calendar.MARCH, 10));
        Cliente c5 = e.crearCliente("JuanBondoni", "5678", "Juan", "Bondoni", "87654321", "mariagomez@gmail.com", new Direccion("Calle Falsa", "456", null,null), new GregorianCalendar(1995, Calendar.OCTOBER, 16));
        Cliente c6 = e.crearCliente("MartinPatriarca", "asdds", "Martin", "Patriarca", "12345467", "mariagomerias@gmail.com", new Direccion("Calle Falsa", "567", null,null), new GregorianCalendar(1965, Calendar.DECEMBER, 16));
        Cliente c7 = e.crearCliente("Ivonne", "1234", "Ivonne", "Pascal", "12345678", "juanperez@gmail.com", new Direccion("Calle Falsa", "123", "8","A"), new GregorianCalendar(1990, Calendar.NOVEMBER, 10));
        Cliente c8 = e.crearCliente("FranciscoVeron", "5678", "Francisco", "Veron", "87654321", "mariagomez@gmail.com", new Direccion("Calle Falsa", "456", null,null), new GregorianCalendar(1995, Calendar.APRIL, 16));
        Cliente c9 = e.crearCliente("JamesBond", "asdds", "James", "Bond", "12345467", "mariagomerias@gmail.com", new Direccion("Calle Falsa", "567", null,null), new GregorianCalendar(1965, Calendar.JANUARY, 16));
        Cliente c10 = e.crearCliente("JamesBond", "asdds", "Martina", "Patriarco", "12345467", "mariagomerias@gmail.com", new Direccion("Calle Falsa", "567", null,null), new GregorianCalendar(1965, Calendar.JANUARY, 16));

        Pedido p1 = e.hacerPedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,2),"ESTANDAR", true, 4, true, c1, 10);
        Pedido p2 = e.hacerPedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,4), "PELIGROSA", false, 1, false, c2, 10);
        Pedido p3 = e.hacerPedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,5), "ESTANDAR", false, 1, false, c3, 10);
        Pedido p4 = e.hacerPedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,12),"ESTANDAR", true, 4, true, c4, 10);
        Pedido p5 = e.hacerPedido(new GregorianCalendar(2024,Calendar.OCTOBER,4), "PELIGROSA", false, 1, false, c5, 10);
        Pedido p6 = e.hacerPedido(new GregorianCalendar(2024,Calendar.OCTOBER,5), "ESTANDAR", false, 1, false, c6, 10);
        Pedido p7 = e.hacerPedido(new GregorianCalendar(2024,Calendar.NOVEMBER,4),"ESTANDAR", false, 4, true, c7, 20);
        Pedido p8 = e.hacerPedido(new GregorianCalendar(2024,Calendar.DECEMBER,4), "PELIGROSA", false, 1, false, c8, 10);

        IViaje v1 = e.asignarVehiculoViaje(p1);
            e.asignarViajeChofer(v1);
            v1.getPedido().getCliente().pagar_viaje();
            v1.getChofer().finalizarViaje();
            IViaje v2 = e.asignarVehiculoViaje(p2);
            e.asignarViajeChofer(v2);
            v2.getPedido().getCliente().pagar_viaje();
            v2.getChofer().finalizarViaje();
            IViaje v3 = e.asignarVehiculoViaje(p3);
            e.asignarViajeChofer(v3);
            v3.getPedido().getCliente().pagar_viaje();
            v3.getChofer().finalizarViaje();
            IViaje v4 = e.asignarVehiculoViaje(p4);
            e.asignarViajeChofer(v4);
            v4.getPedido().getCliente().pagar_viaje();
            v4.getChofer().finalizarViaje();
            IViaje v5 = e.asignarVehiculoViaje(p5);
            e.asignarViajeChofer(v5);
            v5.getPedido().getCliente().pagar_viaje();
            v5.getChofer().finalizarViaje();
            IViaje v6 = e.asignarVehiculoViaje(p6);
            e.asignarViajeChofer(v6);
            v6.getPedido().getCliente().pagar_viaje();
            v6.getChofer().finalizarViaje();
            IViaje v7 = e.asignarVehiculoViaje(p7);
            e.asignarViajeChofer(v7);
            v7.getPedido().getCliente().pagar_viaje();
            v7.getChofer().finalizarViaje();
            IViaje v8 = e.asignarVehiculoViaje(p8);
            e.asignarViajeChofer(v8);
            v8.getPedido().getCliente().pagar_viaje();
            v8.getChofer().finalizarViaje();
        }
        catch(VehiculoNoDisponibleException | ChoferNoDisponibleException | PedidoIncoherenteException ex){
            System.out.println(ex.getMessage());
        } catch (UsuarioRepetidoException ex) {
            throw new RuntimeException(ex);
        }

        // Calcular los costos de los viaje
        Iterator<IViaje> pp = e.getIteratorViajes();
        while (pp.hasNext()) {
            pp.next().calcularCostoViaje();
        }

        e.puntaje_mes_finalizado(new GregorianCalendar(2000,Calendar.SEPTEMBER,1));
        ///Listados

        System.out.println(e.getSueldosTotales(new GregorianCalendar(2024,Calendar.SEPTEMBER,1)));
        System.out.println(e.viajesChoferesFecha(choferTemporario,new GregorianCalendar(2024,Calendar.SEPTEMBER,1),new GregorianCalendar(2024, Calendar.DECEMBER,1)));
        System.out.println(e.viajesClienteFecha(c3,new GregorianCalendar(2024,Calendar.SEPTEMBER,1),new GregorianCalendar(2024, Calendar.DECEMBER,1)));
        System.out.println(e.listadoSueldoMes(new GregorianCalendar(2024,Calendar.SEPTEMBER,1)));

        System.out.println("\nLISTADO VEHICULOS\n");
        System.out.println(e.listado_vehiculos());

        System.out.println("\nLISTADO CLIENTES\n");
        System.out.println(e.listado_clientes());


        System.out.println("\nLISTADO CHOFERES\n");
        System.out.println(e.listado_choferes());

        System.out.println("\nLISTADO VIAJES\n");
        System.out.println(e.historico_viajes());

    }
}
