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
        IVehiculo combi1 = VehiculoFactory.getVehiculo("COMBI", "EEE555");
        IVehiculo combi2 = VehiculoFactory.getVehiculo("COMBI", "FFF666");

        e.agregarVehiculo(moto1);
        e.agregarVehiculo(moto2);
        e.agregarVehiculo(auto1);
        e.agregarVehiculo(auto2);
        e.agregarVehiculo(combi1);
        e.agregarVehiculo(combi2);
        /*
        System.out.println("\nVEHICULOS\n");
        for (Iterator<IVehiculo> it = e.getVehiculos(); it.hasNext(); ) {
            IVehiculo v = it.next();
            System.out.println(v);
        }
        */
        Cliente c1 = new Cliente("Juan_Perez", "1234", "Juan", "Perez", "12345678", "juanperez@gmail.com", new Direccion("Calle Falsa", "123", "8","A"), new GregorianCalendar(1990, 5, 10));
        Cliente c2 = new Cliente("Maria_Gomez", "5678", "Maria", "Gomez", "87654321", "mariagomez@gmail.com", new Direccion("Calle Falsa", "456", null,null), new GregorianCalendar(1995, 2, 16));
        Cliente c3 = new Cliente("Maria_Gomeria", "asdds", "Maria", "Gomerias", "12345467", "mariagomerias@gmail.com", new Direccion("Calle Falsa", "567", null,null), new GregorianCalendar(1965, 2, 16));
        Cliente c4 = new Cliente("Juan_Pelotas", "1234", "Juan", "Perez", "12345678", "juanperez@gmail.com", new Direccion("Calle Falsa", "123", "8","A"), new GregorianCalendar(1990, 5, 10));
        Cliente c5 = new Cliente("Bokitaelmasgrande", "5678", "Maria", "Gomez", "87654321", "mariagomez@gmail.com", new Direccion("Calle Falsa", "456", null,null), new GregorianCalendar(1995, 2, 16));
        Cliente c6 = new Cliente("PartinMatriarca", "asdds", "Martin", "Gomerias", "12345467", "mariagomerias@gmail.com", new Direccion("Calle Falsa", "567", null,null), new GregorianCalendar(1965, 2, 16));
        Cliente c7 = new Cliente("Juesus", "1234", "Juan", "Perez", "12345678", "juanperez@gmail.com", new Direccion("Calle Falsa", "123", "8","A"), new GregorianCalendar(1990, 5, 10));
        Cliente c8 = new Cliente("FranciscoVergon", "5678", "Maria", "Gomez", "87654321", "mariagomez@gmail.com", new Direccion("Calle Falsa", "456", null,null), new GregorianCalendar(1995, 2, 16));
        Cliente c9 = new Cliente("JamesBond", "asdds", "Maria", "Gomerias", "12345467", "mariagomerias@gmail.com", new Direccion("Calle Falsa", "567", null,null), new GregorianCalendar(1965, 2, 16));
        try {
            e.agregarCliente(c1);
            e.agregarCliente(c2);
            e.agregarCliente(c3);
            // e.agregarCliente(c1);
        }
        catch (UsuarioRepetidoException ex){
            System.out.println(ex);
        }

        Empleado choferTemporario = new ChoferTemporario("12345678", "Maria", 0.1, 0.1);
        Empleado choferTemporario2 = new ChoferTemporario("40123421", "Mario", 0.2, 0.2);
        Empleado choferPermanente = new ChoferPermanente("98746532", "Juan", 0.1, new GregorianCalendar(1995,2,16), 20,2);
        Empleado choferPermanente2 = new ChoferPermanente("9421322", "Juanito", 0.1, new GregorianCalendar(1982,5,16), 5,0);
        Empleado choferContratado = new ChoferContratado("74353656", "Pepe", 0.6);
        Empleado choferContratado2 = new ChoferContratado("77446688", "Grillo", 0.5);

        e.agregarChofer(choferContratado);
        e.agregarChofer(choferContratado2);
        e.agregarChofer(choferTemporario);
        e.agregarChofer(choferTemporario2);
        e.agregarChofer(choferPermanente);
        e.agregarChofer(choferPermanente2);
        /*
        System.out.println("\nCHOFERES\n");
        for (Iterator<Empleado> it = e.getChoferes(); it.hasNext(); ) {
            Empleado v = it.next();
            System.out.println(v);
        }
        */
        Pedido p1 = new Pedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,2),"ESTANDAR", true, 4, true, c1, 10);
        Pedido p2 = new Pedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,4), "PELIGROSA", false, 1, false, c2, 10);
        Pedido p3 = new Pedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,5), "ESTANDAR", false, 1, false, c3, 10);
        Pedido p4 = new Pedido(new GregorianCalendar(2024,Calendar.SEPTEMBER,12),"ESTANDAR", true, 4, true, c4, 10);
        Pedido p5 = new Pedido(new GregorianCalendar(2024,Calendar.OCTOBER,4), "PELIGROSA", false, 1, false, c5, 10);
        Pedido p6 = new Pedido(new GregorianCalendar(2024,Calendar.OCTOBER,5), "ESTANDAR", false, 1, false, c6, 10);
        Pedido p7 = new Pedido(new GregorianCalendar(2024,Calendar.NOVEMBER,2),"ESTANDAR", true, 4, true, c7, 10);
        Pedido p8 = new Pedido(new GregorianCalendar(2024,Calendar.DECEMBER,4), "PELIGROSA", false, 1, false, c8, 10);
        Pedido p9 = new Pedido(new GregorianCalendar(2024,Calendar.DECEMBER,5), "ESTANDAR", false, 1, false, c9, 10);
        //Pedido p10 = new Pedido(new GregorianCalendar(2000, 1, 1), "ESTANDAR", false, 10, false, c1, 10);
        //Pedido p11=new Pedido(new GregorianCalendar(), "PELIGROSA", false, 20, false, c2, 10);

        try {
            IViaje v1 = e.asignarPedidoVehiculo(p1);
            IViaje v2 = e.asignarPedidoVehiculo(p2);
            IViaje v3 = e.asignarPedidoVehiculo(p3);
            IViaje v4 = e.asignarPedidoVehiculo(p4);
            IViaje v5 = e.asignarPedidoVehiculo(p5);
            IViaje v6 = e.asignarPedidoVehiculo(p6);
            IViaje v7 = e.asignarPedidoVehiculo(p7);
            IViaje v8 = e.asignarPedidoVehiculo(p8);
            IViaje v9 = e.asignarPedidoVehiculo(p9);
            //IViaje v10 = e.asignarPedidoVehiculo(p10);
            //IViaje v11 = e.asignarPedidoVehiculo(p11);

            e.asignarViajeChofer(v1);
            e.asignarViajeChofer(v2);
            e.asignarViajeChofer(v3);
            e.asignarViajeChofer(v4);
            e.asignarViajeChofer(v5);
            e.asignarViajeChofer(v6);
            c1.pagar_viaje();
            c2.pagar_viaje();
            c3.pagar_viaje();
            choferContratado.finalizarViaje();
            choferContratado2.finalizarViaje();
            choferTemporario.finalizarViaje();
            e.asignarViajeChofer(v7);
            e.asignarViajeChofer(v8);
            e.asignarViajeChofer(v9);
            //e.asignarViajeChofer(v10);
            //e.asignarViajeChofer(v11);
        }
        catch(VehiculoNoDisponibleException ex){
            System.out.println(ex);
        }
        catch(ChoferNoDisponibleException ex){
            System.out.println(ex);
        }
        catch(PedidoIncoherenteException ex) {
            System.out.println(ex);
        }



        //System.out.println("\nVIAJES\n");
        for (Iterator<IViaje> it = e.getViajes(); it.hasNext(); ) {
            IViaje v = it.next();
            v.calcularCostoViaje();
            //System.out.println(v);
        }

        ///Listados

        System.out.println(e.getSueldosTotales(new GregorianCalendar(2024,Calendar.SEPTEMBER,1)));
        System.out.println(e.viajesChoferesFecha(choferTemporario,new GregorianCalendar(2024,Calendar.SEPTEMBER,1),new GregorianCalendar(2024, Calendar.DECEMBER,1)));
        System.out.println(e.viajesClienteFecha(c3,new GregorianCalendar(2024,Calendar.SEPTEMBER,1),new GregorianCalendar(2024, Calendar.DECEMBER,1)));

        System.out.println("\nLISTADO VEHICULOS\n");
        System.out.println(e.listado_vehiculos());

        System.out.println("\nLISTADO CLIENTES\n");
        System.out.println(e.listado_clientes());

        e.puntaje_mes_finalizado(new GregorianCalendar(2000,4,1));
        System.out.println("\nLISTADO CHOFERES\n");
        System.out.println(e.listado_choferes());

        System.out.println("\nLISTADO VIAJES\n");
        System.out.println(e.historico_viajes());

    }
}
