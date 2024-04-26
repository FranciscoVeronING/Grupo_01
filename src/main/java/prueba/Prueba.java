package prueba;

import models.*;

import java.util.Date;

public class Prueba {
    public Prueba() {
        super();
    }

    public static void main(String[] args) {

        Prueba prueba = new Prueba();

        Empresa e = Empresa.getInstancia();

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

        System.out.println("\nVEHICULOS\n");
        for (IVehiculo v : e.getVehiculos())
            System.out.println(v);

        Cliente c1 = new Cliente("Juan");
        Cliente c2 = new Cliente("Maria");

        e.agregarCliente(c1);
        e.agregarCliente(c2);

        Empleado choferTemporario = new ChoferTemporario("12345678", "Maria", 0.1, 0.1);
        Empleado choferPermanente = new ChoferPermanente("98746532", "Juan", 0.1, 0.1, 0.1);
        Empleado choferContratado = new ChoferContratado("77446688", "Pepe", 0.0);

        e.agregarChofer(choferContratado);
        e.agregarChofer(choferTemporario);
        e.agregarChofer(choferPermanente);

        System.out.println("\nCHOFERES\n");
        for (Empleado v : e.getChoferes())
            System.out.println(v);

        Pedido p1 = new Pedido(new Date(),"ESTANDAR", true, 4, true, c1);
        Pedido p2 = new Pedido(new Date(), "PELIGROSA", false, 1, false, c2);
        Pedido p3 = new Pedido(new Date(), "ESTANDAR", false, 1, false, c1);


    }
}
