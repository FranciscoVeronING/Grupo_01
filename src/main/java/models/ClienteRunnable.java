package models;

import java.util.GregorianCalendar;
import java.util.Random;
import Exception.PedidoIncoherenteException;

class ClienteRunnable extends Cliente implements Runnable {
    private BolsaDeViajes bolsa;
    private int cantViajes;

    public ClienteRunnable(BolsaDeViajes bolsa) {
        super();
        Random r = new Random();
        this.bolsa = bolsa;
        cantViajes = r.nextInt(4);
    }

    public void run() {
        for (int i = 0; i < cantViajes; i++) {
            Pedido pedido = crearPedido();
            try {
                Sistema.getInstancia().solicitarAceptacion(pedido);
            } catch (PedidoIncoherenteException ex) {
                Thread.currentThread().interrupt();
            }
            // Solicitar un viaje sobre el pedido aceptado
            IViaje viaje = Sistema.getInstancia().solicitarViaje(pedido);
            // Pagar un viaje
            bolsa.viajePagado(viaje);
        }
    }

    private Pedido crearPedido() {
        // Probabilidad de cada opción
        double probabilidadOpcion1 = 0.30; // 30%
        double probabilidadOpcion2 = 0.50; // 50%
        double probabilidadOpcion3 = 0.20; // 20%

        Random random = new Random();
        double probabilidadTotal = probabilidadOpcion1 + probabilidadOpcion2 + probabilidadOpcion3;
        double randomNumber = random.nextDouble() * probabilidadTotal;

        String zona = generaZona();
        boolean equipaje = random.nextBoolean();
        boolean mascotas = random.nextBoolean();
        int cantPax;
        double distancia = random.nextDouble() * 50;

        if (randomNumber < probabilidadOpcion1) {
            cantPax = 1;
            equipaje = false;
            mascotas = false;
        } else if (randomNumber < probabilidadOpcion1 + probabilidadOpcion2) {
            cantPax = random.nextInt(4) + 1; // Entre 1 y 4 pasajeros
        } else {
            cantPax = random.nextInt(6) + 5; // Entre 5 y 10 pasajeros
            mascotas = false;
        }
        Pedido p = new Pedido(new GregorianCalendar(), zona, mascotas, cantPax, equipaje, this, distancia);
        bolsa.lanzarPedido(p);
        return p;
    }


    public String generaZona() {
        String[] zonas = {"ESTANDAR", "SIN ASFALTAR", "PELIGROSA"};
        Random rand = new Random();
        return zonas[rand.nextInt(zonas.length)];
    }
}
