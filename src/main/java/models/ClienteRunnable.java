package models;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Random;
import Exception.PedidoIncoherenteException;

public class ClienteRunnable extends Cliente implements Runnable, Serializable {
    private BolsaDeViajes bolsa;
    private int cantViajes;

    public ClienteRunnable(BolsaDeViajes bolsa) {
        super();
        Random r = new Random();
        this.bolsa = bolsa;
        cantViajes = r.nextInt(4)+1;
    }

    public ClienteRunnable(Cliente cliente) {
        super();
        Random r = new Random();
        cantViajes = r.nextInt(4)+1;
    }
    public ClienteRunnable() {
    }

    public BolsaDeViajes getBolsa() {
        return bolsa;
    }

    public void setBolsa(BolsaDeViajes bolsa) {
        this.bolsa = bolsa;
    }

    public int getCantViajes() {
        return cantViajes;
    }

    public void setCantViajes(int cantViajes) {
        this.cantViajes = cantViajes;
    }

    public void run() {
        for (int i = 0; i < cantViajes; i++) {
            Pedido pedido = crearPedido();
            try {
                Sistema.getInstancia().solicitarAceptacion(pedido);
                // Solicitar un viaje sobre el pedido aceptado
                IViaje viaje = Sistema.getInstancia().solicitarViaje(pedido);
                // Pagar un viaje
                Thread.currentThread().sleep(1000);
                bolsa.viajePagado(viaje);
            } catch (PedidoIncoherenteException ex) {
                bolsa.rechazarPedido(pedido);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

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
