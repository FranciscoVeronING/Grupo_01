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

        IVehiculo moto1 = VehiculoFactory.getVehiculo("MOTO", "AAA111");
        IVehiculo moto2 = VehiculoFactory.getVehiculo("MOTO", "BBB222");
        IVehiculo auto1 = VehiculoFactory.getVehiculo("AUTO", "CCC333");
        IVehiculo auto2 = VehiculoFactory.getVehiculo("AUTO", "DDD444");
        IVehiculo auto3 = VehiculoFactory.getVehiculo("AUTO", "DDD445");
        IVehiculo combi1 = VehiculoFactory.getVehiculo("COMBI", "EEE555");
        IVehiculo combi2 = VehiculoFactory.getVehiculo("COMBI", "FFF666");

        e.agregarVehiculo(moto1);
        e.agregarVehiculo(moto2);
        e.agregarVehiculo(auto1);
        e.agregarVehiculo(auto2);
        e.agregarVehiculo(combi1);
        e.agregarVehiculo(combi2);
        e.agregarVehiculo(auto3);

        Cliente c1 = new Cliente("Juan_Perez", "1234", "Juan", "Perez", "12345678", "juanperez@gmail.com", new Direccion("Calle Falsa", "123", "8","A"), new GregorianCalendar(1990, Calendar.MAY, 10));
        Cliente c2 = new Cliente("Maria_Gomez", "5678", "Maria", "Gomez", "87654321", "mariagomez@gmail.com", new Direccion("Calle Falsa", "456", null,null), new GregorianCalendar(1995, Calendar.FEBRUARY, 16));
        Cliente c3 = new Cliente("Maria_Gomeria", "asdds", "Maria", "Gomerias", "12345467", "mariagomerias@gmail.com", new Direccion("Calle Falsa", "567", null,null), new GregorianCalendar(1965, Calendar.FEBRUARY, 16));
        Cliente c4 = new Cliente("CapitanLeonel", "1234", "Capitan", "Leonel", "12345678", "juanperez@gmail.com", new Direccion("Calle Falsa", "123", "8","A"), new GregorianCalendar(1990, Calendar.MARCH, 10));
        Cliente c5 = new Cliente("JuanBondoni", "5678", "Juan", "Bondoni", "87654321", "mariagomez@gmail.com", new Direccion("Calle Falsa", "456", null,null), new GregorianCalendar(1995, Calendar.OCTOBER, 16));
        Cliente c6 = new Cliente("MartinPatriarca", "asdds", "Martin", "Patriarca", "12345467", "mariagomerias@gmail.com", new Direccion("Calle Falsa", "567", null,null), new GregorianCalendar(1965, Calendar.DECEMBER, 16));
        Cliente c7 = new Cliente("Ivonne", "1234", "Ivonne", "Pascal", "12345678", "juanperez@gmail.com", new Direccion("Calle Falsa", "123", "8","A"), new GregorianCalendar(1990, Calendar.NOVEMBER, 10));
        Cliente c8 = new Cliente("FranciscoVeron", "5678", "Francisco", "Veron", "87654321", "mariagomez@gmail.com", new Direccion("Calle Falsa", "456", null,null), new GregorianCalendar(1995, Calendar.APRIL, 16));
        Cliente c9 = new Cliente("JamesBond", "asdds", "James", "Bond", "12345467", "mariagomerias@gmail.com", new Direccion("Calle Falsa", "567", null,null), new GregorianCalendar(1965, Calendar.JANUARY, 16));
        Cliente c10 = new Cliente("JamesBond", "asdds", "Martina", "Patriarco", "12345467", "mariagomerias@gmail.com", new Direccion("Calle Falsa", "567", null,null), new GregorianCalendar(1965, Calendar.JANUARY, 16));
        try {
            e.agregarCliente(c1);
            e.agregarCliente(c2);
            e.agregarCliente(c3);
            e.agregarCliente(c4);
            e.agregarCliente(c5);
            e.agregarCliente(c6);
            e.agregarCliente(c7);
            e.agregarCliente(c8);
            e.agregarCliente(c9);
            //e.agregarCliente(c10); //mismo nombre de usuario que c9, lanza excepcion
        }
        catch (UsuarioRepetidoException ex){
            System.out.println(ex.getMessage());
        }

        Empleado choferTemporario = new ChoferTemporario("12345678", "Maria", 0.1, 0.1);
        Empleado choferTemporario2 = new ChoferTemporario("40123421", "Mario", 0.2, 0.2);
        Empleado choferPermanente = new ChoferPermanente("98746532", "Juan", 0.1, new GregorianCalendar(1995,Calendar.JANUARY,16), 20,2);
        Empleado choferPermanente2 = new ChoferPermanente("9421322", "Juanito", 0.1, new GregorianCalendar(1982,Calendar.FEBRUARY,16), 5,0);
        Empleado choferContratado = new ChoferContratado("74353656", "Pepe", 0.6);
        Empleado choferContratado2 = new ChoferContratado("77446688", "Grillo", 0.5);

        e.agregarChofer(choferContratado);
        e.agregarChofer(choferContratado2);
        e.agregarChofer(choferTemporario);
        e.agregarChofer(choferTemporario2);
        e.agregarChofer(choferPermanente);
        e.agregarChofer(choferPermanente2);

        Pedido p1 = new Pedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,2),"ESTANDAR", true, 4, true, c1, 10);
        Pedido p2 = new Pedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,4), "PELIGROSA", false, 1, false, c2, 10);
        Pedido p3 = new Pedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,5), "ESTANDAR", false, 1, false, c3, 10);
        Pedido p4 = new Pedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,12),"ESTANDAR", true, 4, true, c4, 10);
        Pedido p5 = new Pedido(new GregorianCalendar(2024,Calendar.OCTOBER,4), "PELIGROSA", false, 1, false, c5, 10);
        Pedido p6 = new Pedido(new GregorianCalendar(2024,Calendar.OCTOBER,5), "ESTANDAR", false, 1, false, c6, 10);
        Pedido p7 = new Pedido(new GregorianCalendar(2024,Calendar.NOVEMBER,4),"ESTANDAR", false, 4, true, c7, 20);
        Pedido p8 = new Pedido(new GregorianCalendar(2024,Calendar.DECEMBER,4), "PELIGROSA", false, 1, false, c8, 10);
        /*Pedido p9 = new Pedido(new GregorianCalendar(2024,Calendar.DECEMBER,5), "ESTANDAR", false, 1, false, c9, 30);
        Pedido p10 = new Pedido(new GregorianCalendar(2025,Calendar.JANUARY,2), "ESTANDAR", false, 1, false, c1, 30);
        Pedido p11 = new Pedido(new GregorianCalendar(2025, Calendar.JANUARY, 2), "ESTANDAR", true, 10, false, c2, 10);
        Pedido p12 = new Pedido(new GregorianCalendar(2025,Calendar.JANUARY,17), "PELIGROSA", false, 20, false, c3, 10);
*/
        try {
            IViaje v1 = e.asignarPedidoVehiculo(p1);
            e.asignarViajeChofer(v1);
            v1.getPedido().getCliente().pagar_viaje();
            v1.getChofer().finalizarViaje();
            IViaje v2 = e.asignarPedidoVehiculo(p2);
            e.asignarViajeChofer(v2);
            v2.getPedido().getCliente().pagar_viaje();
            v2.getChofer().finalizarViaje();
            IViaje v3 = e.asignarPedidoVehiculo(p3);
            e.asignarViajeChofer(v3);
            v3.getPedido().getCliente().pagar_viaje();
            v3.getChofer().finalizarViaje();
            IViaje v4 = e.asignarPedidoVehiculo(p4);
            e.asignarViajeChofer(v4);
            v4.getPedido().getCliente().pagar_viaje();
            v4.getChofer().finalizarViaje();
            IViaje v5 = e.asignarPedidoVehiculo(p5);
            e.asignarViajeChofer(v5);
            v5.getPedido().getCliente().pagar_viaje();
            v5.getChofer().finalizarViaje();
            IViaje v6 = e.asignarPedidoVehiculo(p6);
            e.asignarViajeChofer(v6);
            v6.getPedido().getCliente().pagar_viaje();
            v6.getChofer().finalizarViaje();
            IViaje v7 = e.asignarPedidoVehiculo(p7);
            e.asignarViajeChofer(v7);
            v7.getPedido().getCliente().pagar_viaje();
            v7.getChofer().finalizarViaje();
            IViaje v8 = e.asignarPedidoVehiculo(p8);
            e.asignarViajeChofer(v8);
            v8.getPedido().getCliente().pagar_viaje();
            v8.getChofer().finalizarViaje();
        }
        catch(VehiculoNoDisponibleException | ChoferNoDisponibleException | PedidoIncoherenteException ex){
            System.out.println(ex.getMessage());
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
