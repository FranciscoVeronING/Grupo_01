package models;

import java.util.GregorianCalendar;
import java.util.Random;
import Exception.PedidoIncoherenteException;

class ClienteRunnable extends Cliente implements Runnable {
    private BolsaDeViajes bolsa;

    public ClienteRunnable(BolsaDeViajes bolsa) {
       super();
       this.bolsa = bolsa;
    }

    public void run() {
        // Crear pedido y solicitar aceptación
        Pedido pedido = crearPedido();
        try {
            Sistema.getInstancia().solicitarAceptacion(pedido);
        } catch (PedidoIncoherenteException ex) {
            throw new RuntimeException(ex);
        }

        // Solicitar un viaje sobre el pedido aceptado
        IViaje viaje = Sistema.getInstancia().solicitarViaje(pedido);
        synchronized (viaje) {
            // Espera a que le hayan asignado un vehiculo
            while(!viaje.getEstado_de_viaje().equalsIgnoreCase("INICIADO")) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            // Pagar un viaje
            bolsa.viajePagado((Viaje) viaje);
        }
    }

    private Pedido crearPedido() {
        // Parametros aleatorios
        String zona = generaZona();
        boolean equipaje = new Random().nextBoolean();
        boolean mascotas = new Random().nextBoolean();
        int cantPax = new Random().nextInt(10) + 1;
        double distancia = new Random().nextFloat() * 50;
        return new Pedido(new GregorianCalendar(), zona, mascotas, cantPax, equipaje, this, distancia );
    }


    public String generaZona() {
        String[] zonas = {"ESTANDAR", "SIN ASFALTAR", "PELIGROSA"};
        Random rand = new Random();
        return zonas[rand.nextInt(zonas.length)];
    }

}
