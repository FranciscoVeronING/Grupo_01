package models;

import Exception.PedidoIncoherenteException;


public class ClienteAppRunnable implements Runnable {

        private BolsaDeViajes bolsa;
        private Pedido p;
        private IViaje v;

        public ClienteAppRunnable(BolsaDeViajes bolsa, Pedido p) {
            this.bolsa = bolsa;
            this.p = p;
        }

        public BolsaDeViajes getBolsa() {
            return this.bolsa;
        }

        public void setViaje(IViaje v) {
            this.v = v;
        }

        public IViaje getViaje() {
            return this.v;
        }

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
