package models;

abstract class EmpleadoRunnable extends Empleado implements Runnable {
    private BolsaDeViajes bolsa;

    public EmpleadoRunnable(BolsaDeViajes bolsa) {
        super();
        this.bolsa = bolsa;
    }

    public void run() {
        while (bolsa.getSimulacionActiva()) {
            IViaje v = null;
            synchronized (bolsa) {
                v = bolsa.viajeSinChofer();
                if (v != null) bolsa.asignarChofer(v, this);
            }
            if (v != null) bolsa.viajeFinalizado(v);
            try {
                Thread.sleep(1000); // Espera un segundo antes de buscar otro viaje
                } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
