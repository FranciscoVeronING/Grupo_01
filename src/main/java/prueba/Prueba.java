package prueba;

import models.*;
import Exception.*;

import java.util.GregorianCalendar;
import java.util.Iterator;

public class Prueba {
    public Prueba() {
        super();
    }

    public static void main(String[] args) {

        Prueba prueba = new Prueba();

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

        try {
            e.agregarCliente(c1);
            e.agregarCliente(c2);
            // e.agregarCliente(c1);
        }
        catch (UsuarioRepetidoException ex){
            System.out.println(ex);
        }

        Empleado choferTemporario = new ChoferTemporario("12345678", "Maria", 0.1, 0.1);
        Empleado choferPermanente = new ChoferPermanente("98746532", "Juan", 0.1, new GregorianCalendar(1995,2,16), 20,2);
        Empleado choferContratado = new ChoferContratado("77446688", "Pepe", 0.6);

        e.agregarChofer(choferContratado);
        e.agregarChofer(choferTemporario);
        e.agregarChofer(choferPermanente);
        /*
        System.out.println("\nCHOFERES\n");
        for (Iterator<Empleado> it = e.getChoferes(); it.hasNext(); ) {
            Empleado v = it.next();
            System.out.println(v);
        }
        */
        Pedido p1 = new Pedido(new GregorianCalendar(1999,5,10),"ESTANDAR", true, 4, true, c1, 10);
        Pedido p2 = new Pedido(new GregorianCalendar(2000,4,12), "PELIGROSA", false, 1, false, c2, 10);
        Pedido p3 = new Pedido(new GregorianCalendar(2000,4,25), "ESTANDAR", false, 1, false, c1, 10);
        //Pedido p4 = new Pedido(new GregorianCalendar(2000, 1, 1), "ESTANDAR", false, 10, false, c1, 10);
        //Pedido p5 = new Pedido(new GregorianCalendar(), "PELIGROSA", false, 20, false, c2, 10);

        try {
            IViaje v1 = e.asignarPedidoVehiculo(p1);
            IViaje v2 = e.asignarPedidoVehiculo(p2);
            IViaje v3 = e.asignarPedidoVehiculo(p3);
            //IViaje v4 = e.asignarPedidoVehiculo(p4);
            //IViaje v5 = e.asignarPedidoVehiculo(p5);

            e.asignarViajeChofer(v1);
            e.asignarViajeChofer(v2);
            e.asignarViajeChofer(v3);
            //e.asignarViajeChofer(v4);
            //e.asignarViajeChofer(v5);
        }
        catch (VehiculoNoDisponibleException ex){
            System.out.println(ex);
        }
        catch(ChoferNoDisponibleException ex){
            System.out.println(ex);
        }
        catch (PedidoIncoherenteException ex) {
            System.out.println(ex);
        }

        //System.out.println("\nVIAJES\n");
        for (Iterator<IViaje> it = e.getViajes(); it.hasNext(); ) {
            IViaje v = it.next();
            v.calcularCostoViaje();
            //System.out.println(v);
        }


        int sueldo = 0;
        System.out.println("\nSUELDOS\n");
        Iterator<Empleado> it = e.getChoferes();
        while (it.hasNext()) {
            Empleado chofer = it.next();
            System.out.println("El sueldo de " + chofer + " es " + e.getSueldoMensual(chofer));
        }
        System.out.println("El sueldo total de los choferes es: " + e.getSueldosTotales());

        System.out.println("\nLISTADO VEHICULOS\n");
        System.out.println(e.listado_vehiculos());

        System.out.println("\nLISTADO CLIENTES\n");
        System.out.println(e.listado_clientes());

        e.puntaje_mes_finalizado(new GregorianCalendar(2000,4,1));
        System.out.println("\nLISTADO CHOFERES\n");
        System.out.println(e.listado_choferes());

    }
}
