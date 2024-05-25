package models;

class SistemaThread extends Thread {
    private BolsaDeViajes bolsa;
    private ArrayList<Vehiculo> vehiculosDisponibles;

    public SistemaThread(BolsaDeViajes bolsa, ArrayList<Vehiculo> vehiculosDisponibles) {
        this.bolsa = bolsa;
        this.vehiculosDisponibles = vehiculosDisponibles;
    }

    public void run() {
        while (true) {
            Viaje viaje = bolsa.obtenerViajeSinVehiculo(); // Método ficticio para obtener un viaje sin vehículo
            if (viaje != null && !vehiculosDisponibles.isEmpty()) {
                // Asignar un vehículo al viaje
                Vehiculo vehiculoAsignado = vehiculosDisponibles.remove(0); // Supone que tomas el primer vehículo disponible
                viaje.setVehiculo(vehiculoAsignado); // Método ficticio para asignar el vehículo al viaje
                bolsa.agregarViaje(viaje); // Vuelve a poner el viaje en la bolsa, pero ahora con vehículo asignado
            }
            try {
                Thread.sleep(1000); // Espera un segundo antes de intentar asignar otro vehículo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
