package models;

import javafx.beans.Observable;

import java.util.GregorianCalendar;
import java.util.Random;

public class ClienteAppRunneable implements Runnable{

        private BolsaDeViajes bolsa;
        private Pedido p;
        private boolean app;

        public ClienteAppRunneable(BolsaDeViajes bolsa, Pedido p) {
            this.bolsa = bolsa;
            this.p = p;
            this.app = true;
        }

        public void run() {
            bolsa.lanzarPedido(p);

            // Solicitar un viaje sobre el pedido aceptado
            IViaje viaje = Sistema.getInstancia().solicitarViaje(p);
            // Pagar un viaje
            bolsa.viajePagado(viaje);
        }


}
