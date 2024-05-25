package models;

class EmpleadoRunnable extends Empleado implements Runnable {
    private BolsaDeViajes bolsa;

    public EmpleadoRunnable(BolsaDeViajes bolsa) {
        this.bolsa = bolsa;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Viaje viaje = bolsa.obtenerViajeSinChofer(); // Método ficticio para obtener un viaje sin chofer
            if (viaje != null) {
                // Suponiendo que hay un método en Viaje para marcar que el chofer ha tomado el viaje
                viaje.setChoferAsignado(true);

                // Esperar a que el cliente pague el viaje
                synchronized (viaje) {
                    while (!viaje.isPagado()) { // Método ficticio para verificar si el viaje ha sido pagado
                        try {
                            viaje.wait(); // Espera a que el cliente notifique que el pago se ha realizado
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }

                // Finalizar el viaje
                viaje.finalizar(); // Método ficticio para finalizar el viaje
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
