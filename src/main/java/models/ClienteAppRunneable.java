package models;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Random;


public class ClienteAppRunneable extends Observable implements Runnable{

        private BolsaDeViajes bolsa;
        private Pedido p;

        public ClienteAppRunneable(BolsaDeViajes bolsa, Pedido p) {
            this.bolsa = bolsa;
            this.p = p;
        }

        public void run() {
            bolsa.lanzarPedido(p);
            // Solicitar un viaje sobre el pedido aceptado
            IViaje viaje = Sistema.getInstancia().solicitarViaje(p);
            setChanged();
            notifyObservers(new EventoSistema(viaje, EventoSistema.NUEVOVIAJE));

            bolsa.viajePagado(viaje);
            setChanged();
            notifyObservers(new EventoSistema(viaje,EventoSistema.PAGADO));

        }

}
