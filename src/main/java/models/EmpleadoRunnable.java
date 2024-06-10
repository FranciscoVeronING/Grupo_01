package models;

import java.io.Serializable;

public abstract class EmpleadoRunnable extends Empleado implements Runnable, Serializable {
    protected BolsaDeViajes bolsa;
    protected int cantMaxViajes;

    // TODO = Borrar cuando se haya creado el campo de viajes max por chofer
    public EmpleadoRunnable(BolsaDeViajes bolsa) {
        super();
        this.bolsa = bolsa;
        this.cantMaxViajes = 3;
    }

    /* TODO = Reemplazo del de arriba
    * public EmpleadoRunnable(BolsaDeViajes bolsa, int max) {
        super();
        this.bolsa = bolsa;
        * this.cantMaxViajes = max;
    }
    * */

    public int getCantMaxViajes() {
        return cantMaxViajes;
    }

    public void setCantMaxViajes(int cantMaxViajes) {
        this.cantMaxViajes = cantMaxViajes;
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
        while (bolsa.getSimulacionActiva() && this.cant_viajes < this.cantMaxViajes) {
            if (Sistema.getInstancia().getClientesActivos() != 0 || Sistema.getInstancia().getClienteAppActivo()) {
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
        if (this.cant_viajes == this.cantMaxViajes) Sistema.getInstancia().eliminarse(this);
    }
}
