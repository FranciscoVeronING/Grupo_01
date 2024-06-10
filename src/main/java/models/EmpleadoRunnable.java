package models;

import java.io.Serializable;
/**
 * Representa un empleado que puede ejecutar su comportamiento como hilo.
 */
public abstract class EmpleadoRunnable extends Empleado implements Runnable, Serializable {
    protected BolsaDeViajes bolsa;
    protected int cantMaxViajes;

    // TODO = Borrar cuando se haya creado el campo de viajes max por chofer
    /**
     * Constructor de EmpleadoRunnable que inicializa la bolsa de viajes y la cantidad máxima de viajes.
     *<b>Pre:</b> El parametro bolsa debe estar inicializado
     * @param bolsa la bolsa de viajes asociada al empleado.
     */
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

    /**
     * Ejecuta el comportamiento del empleado como un hilo, buscando y asignando viajes mientras la simulación está activa
     * <b>Pre:</b>
     * La instancia de `EmpleadoRunnable` debe estar correctamente inicializada.
     * El atributo `bolsa` no debe ser nulo y debe ser una instancia válida de `BolsaDeViajes`.
     * El atributo `cantMaxViajes` debe ser un valor no negativo que represente la cantidad máxima de viajes que puede realizar el empleado.
     *
     * <b>Post:</b>
     * El empleado buscará y asignará viajes mientras la simulación esté activa y el número de viajes realizados sea menor que la cantidad máxima de viajes permitidos.
     * Si se encuentra un viaje sin chofer, se asignará un chofer y se finalizará el viaje.
     * Si el empleado alcanza su cantidad máxima de viajes permitidos, se eliminará de la simulación.
     */
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
