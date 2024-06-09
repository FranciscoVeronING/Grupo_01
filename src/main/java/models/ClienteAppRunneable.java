package models;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Random;

public class ClienteAppRunneable extends Observable implements Runnable{

        private BolsaDeViajes bolsa;
        private Pedido p;
        private boolean pagado;

        public ClienteAppRunneable(BolsaDeViajes bolsa, Pedido p) {
            this.bolsa = bolsa;
            this.p = p;
            this.pagado = false;
        }

        public void run() {
            bolsa.lanzarPedido(p);
            // Solicitar un viaje sobre el pedido aceptado
            IViaje viaje = Sistema.getInstancia().solicitarViaje(p);
            setChanged();
            notifyObservers("Viaje Solicitado");
            while (pagado == false) {
                //loop esperando a que se aprete el boton
                while (pagado == true) {
                    bolsa.viajePagado(viaje);
                    setChanged();
                    notifyObservers("Pagado");
                }
            }
        }

        public void setPagado(boolean pagado) {
            this.pagado = pagado;
        }

}
