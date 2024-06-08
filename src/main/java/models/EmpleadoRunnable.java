package models;

abstract class EmpleadoRunnable extends Empleado implements Runnable {
    private BolsaDeViajes bolsa;

    public EmpleadoRunnable(BolsaDeViajes bolsa) {
        super();
        this.bolsa = bolsa;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted() && bolsa.getSimulacionActiva()) {
            IViaje viaje = bolsa.obtenerViajeSinChofer();
            this.ocupado = true;
            if (viaje != null) {
                synchronized (viaje) {
                    while (!viaje.getEstado_de_viaje().equalsIgnoreCase("PAGADO")) {
                        try {
                            viaje.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // Finalizar el viaje
                    this.ocupado = false;
                    bolsa.viajeFinalizado(viaje.getViaje());
                    viaje.notifyAll(); // Notifica a todos los hilos que están esperando en este objeto
                }
            }

            try {
                Thread.sleep(1000); // Espera un segundo antes de buscar otro viaje
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
