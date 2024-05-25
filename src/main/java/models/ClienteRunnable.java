package models;

class ClienteRunnable extends Cliente implements Runnable {
    private BolsaDeViajes bolsa;
    private String nombreCliente;

    public ClienteRunnable(BolsaDeViajes bolsa, String nombreCliente) {
        super();//habria que modificar el constructor, para meterle aki los atributos
        this.bolsa = bolsa;
        this.nombreCliente = nombreCliente;
    }

    public void run() {
        // Crear pedido y solicitar aceptación
        Pedido pedido = crearPedido();
        boolean esAceptado = bolsa.solicitarAceptacion(pedido);

        if (esAceptado) {
            // Solicitar un viaje sobre el pedido aceptado
            Viaje viaje = bolsa.solicitarViaje(pedido);
            if (viaje != null) {
                // Pagar un viaje
                pagarViaje(viaje);
            }
        }
    }

    private Pedido crearPedido() {
        // Lógica para crear un nuevo pedido
        return new Pedido(/* parámetros del pedido */);
    }

    private void pagarViaje(Viaje viaje) {
        // Lógica para realizar el pago del viaje
        synchronized (viaje) {
            viaje.pagar();
        }
    }
}
