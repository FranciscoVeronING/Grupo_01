package models;

abstract class EmpleadoRunnable extends Empleado implements Runnable {
    private BolsaDeViajes bolsa;

    public EmpleadoRunnable(BolsaDeViajes bolsa) {
        super();
        this.bolsa = bolsa;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted() && bolsa.getSimulacionActiva()) {
            Viaje viaje = bolsa.obtenerViajeSinChofer(); // Método ficticio para obtener un viaje sin chofer
            this.ocupado = true;
            if (viaje != null) {
                // Esperar a que el cliente pague el viaje
                synchronized (viaje) {
                    while (!viaje.getEstado_de_viaje().equalsIgnoreCase("PAGADO")) {
                        try {
                            viaje.wait(); // Espera a que el cliente notifique que el pago se ha realizado
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // Finalizar el viaje
                    this.ocupado = false;
                    bolsa.viajeFinalizado(viaje);
                    notifyAll();
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
