package models;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Random;

import Exception.PedidoIncoherenteException;


public class ClienteAppRunneable implements Runnable{

        private BolsaDeViajes bolsa;
        private Pedido p;
        private IViaje v;

        public ClienteAppRunneable(BolsaDeViajes bolsa, Pedido p) {
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
            bolsa.lanzarPedido(p);
            try {
                Sistema.getInstancia().solicitarAceptacion(p);
                this.v = Sistema.getInstancia().solicitarViaje(p);
            } catch (PedidoIncoherenteException e) {
                bolsa.rechazarPedido(p);
            }

        }

}
