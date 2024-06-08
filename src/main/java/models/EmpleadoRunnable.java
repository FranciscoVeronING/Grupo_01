package models;

abstract class EmpleadoRunnable extends Empleado implements Runnable {
    private BolsaDeViajes bolsa;

    public EmpleadoRunnable(BolsaDeViajes bolsa) {
        super();
        this.bolsa = bolsa;
    }

    public void run() {
        while (bolsa.getSimulacionActiva()) {
            IViaje viaje = bolsa.asignarseAViaje(this);
            if (viaje != null) bolsa.viajeFinalizado(viaje);
            try {
                Thread.sleep(1000); // Espera un segundo antes de buscar otro viaje
                } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
