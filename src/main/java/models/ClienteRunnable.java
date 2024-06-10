package models;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Random;
import Exception.PedidoIncoherenteException;
/**
 * Clase que representa un cliente ejecutado en un hilo.
 * Este cliente puede realizar múltiples pedidos de viajes durante la simulación.
 */
public class ClienteRunnable extends Cliente implements Runnable, Serializable {
    private BolsaDeViajes bolsa;
    private int cantViajes;

    /**
     * Constructor de ClienteRunnable con una bolsa de viajes.
     * <b>Pre:</b> La bolsa de viajes no debe ser null.
     * <b>Post:</b> Se crea un cliente con una cantidad aleatoria de viajes (entre 1 y 4).
     *
     * @param bolsa La bolsa de viajes.
     */

    // TODO = BORRAR ESTE CONSTRUCTOR CUANDO SE HAYA IMPLEMENTADO EL CAMPO PARA PONER CANTIDAD VIAJES
    public ClienteRunnable(BolsaDeViajes bolsa) {
        super();
        Random r = new Random();
        this.bolsa = bolsa;
        cantViajes = r.nextInt(4)+1;
    }
    /**
     * Constructor de ClienteRunnable con una bolsa de viajes y una cantidad específica de viajes.
     * <b>Pre:</b> La bolsa de viajes no debe ser null.
     * <b>Post:</b> Se crea un cliente con la cantidad especificada de viajes.
     *
     * @param bolsa La bolsa de viajes.
     * @param cantViajes La cantidad de viajes que realizará el cliente.
     */
    public ClienteRunnable(BolsaDeViajes bolsa, int cantViajes) {
        super();
        this.bolsa = bolsa;
        this.cantViajes = cantViajes;
    }
    /**
     * Constructor por defecto de ClienteRunnable.
     */
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


    /**
     * Método que ejecuta la lógica del cliente cuando es ejecutado en un hilo.
     * <b>Pre:</b> La instancia del sistema debe estar inicializada y debe haber choferes disponibles.
     * <b>Post:</b> El cliente realiza los pedidos de viaje especificados y espera su procesamiento.
     *          Si se completan todos los viajes, el cliente se elimina del sistema.
     */
    public void run() {
        int i = 0;
        while (i < cantViajes && Sistema.getInstancia().getBolsaDeViajes().getSimulacionActiva() && Sistema.getInstancia().getChoferesActivos() != 0) {
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
                i++;
        }
        if (cantViajes == i) Sistema.getInstancia().eliminarse(this);
    }
    /**
     * Crea un pedido de viaje con datos aleatorios.
     * <b>Pre:</b> La instancia del sistema debe estar inicializada.
     * <b>Post:</b> Se crea y retorna un pedido de viaje con datos aleatorios.
     *
     * @return El pedido de viaje creado.
     */
    private Pedido crearPedido() {
        // Probabilidad de cada opción
        double probabilidadOpcion1 = 0.30; // 30%
        double probabilidadOpcion2 = 0.50; // 50%
        double probabilidadOpcion3 = 0.20; // 20%

        Random random = new Random();
        double probabilidadTotal = probabilidadOpcion1 + probabilidadOpcion2 + probabilidadOpcion3;
        double randomNumber = random.nextDouble() * probabilidadTotal;

        String zona = Utiles.generaZona();
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
        return Sistema.getInstancia().hacerPedido(new GregorianCalendar(), zona, mascotas, cantPax, equipaje, this, distancia);
    }
}
