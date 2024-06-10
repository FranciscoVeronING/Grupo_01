package models;

import Exception.PedidoIncoherenteException;

/**
 * Clase que representa a un cliente en la aplicación, ejecutado en un hilo.
 * El cliente realiza un pedido de viaje y espera su procesamiento.
 */
public class ClienteAppRunnable implements Runnable {

        private BolsaDeViajes bolsa;
        private Pedido p;
        private IViaje v;
    /**
     * Constructor de la clase ClienteAppRunnable.
     * <b>Pre:</b> La bolsa de viajes y el pedido no deben ser null.
     * <b>Post:</b> Se crea una instancia de ClienteAppRunnable con la bolsa de viajes y el pedido especificados.
     *
     * @param bolsa La bolsa de viajes que gestiona los pedidos.
     * @param p El pedido que realiza el cliente.
     */
        public ClienteAppRunnable(BolsaDeViajes bolsa, Pedido p) {
            this.bolsa = bolsa;
            this.p = p;
        }
    /**
     * Obtiene la bolsa de viajes.
     *
     * @return La bolsa de viajes.
     */
        public BolsaDeViajes getBolsa() {
            return this.bolsa;
        }
    /**
     * Establece el viaje asociado al pedido.
     * <b>Pre:</b> El viaje no debe ser null.
     * <b>Post:</b> Se asigna el viaje al pedido.
     *
     * @param v El viaje a asociar.
     */
        public void setViaje(IViaje v) {
            this.v = v;
        }
    /**
     * Obtiene el viaje asociado al pedido.
     *
     * @return El viaje asociado.
     */
        public IViaje getViaje() {
            return this.v;
        }
    /**
     * Método que ejecuta la lógica del cliente cuando es ejecutado en un hilo.
     * <b>Pre:</b> La instancia del sistema debe estar inicializada y debe haber choferes disponibles.
     * <b>Post:</b> El cliente realiza un pedido de viaje y espera su procesamiento.
     *          Si hay choferes disponibles, se lanza el pedido y se espera la aceptación y asignación del viaje.
     *          Si no hay choferes, se rechaza el pedido.
     */
        public void run() {
            if (!Sistema.getInstancia().getChoferes().isEmpty()) { // Si hay choferes
                bolsa.lanzarPedido(p);
                try {
                    Sistema.getInstancia().solicitarAceptacion(p);
                    this.v = Sistema.getInstancia().solicitarViaje(p);
                } catch (PedidoIncoherenteException e) {
                    bolsa.rechazarPedido(p);
                }
            } else {
                // Si no hay choferes
                bolsa.rechazarPedidoPorChoferes(this.p.getCliente());
            }

        }

}
