package models;

import java.io.Serializable;

public abstract class EmpleadoRunnable extends Empleado implements Runnable, Serializable {
    private BolsaDeViajes bolsa;

    public EmpleadoRunnable(BolsaDeViajes bolsa) {
        super();
        this.bolsa = bolsa;
    }

    public EmpleadoRunnable() {
    }

    public BolsaDeViajes getBolsa() {
        return bolsa;
    }

    public void setBolsa(BolsaDeViajes bolsa) {
        this.bolsa = bolsa;
    }

    public void run() {
        while (bolsa.getSimulacionActiva()) {
            IViaje v = null;
            synchronized (bolsa) {
                v = bolsa.viajeSinChofer();
                if (v != null) bolsa.asignarChofer(v, this);
            }
            if (v != null) {
                try {
                    bolsa.viajeFinalizado(v);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(1000); // Espera un segundo antes de buscar otro viaje
                } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
