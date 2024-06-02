package models;

import java.util.GregorianCalendar;
import java.util.Random;
import Exception.PedidoIncoherenteException;

class ClienteRunnable extends Cliente implements Runnable {
    private BolsaDeViajes bolsa;
    private Sistema e = Sistema.getInstancia();

    public ClienteRunnable() {
       // super(nombre_usuario, contrasenia, nombre, apellido, telefono, mail, direccion, fecha_nacimiento);
       // this.bolsa = bolsa;
    }

    public void run() {
        // Crear pedido y solicitar aceptación
        Pedido pedido = crearPedido();
        try {
            e.solicitarAceptacion(pedido);
        } catch (PedidoIncoherenteException ex) {
            throw new RuntimeException(ex);
        }

        // Solicitar un viaje sobre el pedido aceptado
        Viaje viaje = (Viaje) e.solicitarViaje(pedido);
        if (viaje != null) {
            // Pagar un viaje
            pagarViaje(viaje);
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

    private void pagarViaje(Viaje viaje) {
        // Lógica para realizar el pago del viaje
        synchronized (viaje) {
            viaje.pagarse();
        }
    }

    public String generaZona() {
        String[] zonas = {"ESTANDAR", "SIN ASFALTAR", "PELIGROSA"};
        Random rand = new Random();
        return zonas[rand.nextInt(zonas.length)];
    }

}
