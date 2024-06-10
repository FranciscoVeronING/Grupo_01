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
            

        }

}
