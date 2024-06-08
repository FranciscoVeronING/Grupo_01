package vista;

import models.Cliente;
import models.EventoSistema;

import java.util.Observable;

public class ObservadorClienteApp extends ObservadorAbstracto{
    IVistaAppCliente_SituacionViaje vista ;
    public ObservadorClienteApp(Observable observado, IVistaAppCliente_SituacionViaje vista) {
        super(observado);
        this.vista = vista;
    }

    @Override
    public void update(Observable obs, Object arg) {
        super.update(obs, arg);

        EventoSistema evento = (EventoSistema) arg;
        String mensaje = evento.getMensaje();

        if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVIAJE)) {
            this.vista.actualizarEstadoViaje("Viaje confirmado!,\n Esperando que te asignen vehículo...");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVEHICULO)) {
            this.vista.actualizarEstadoViaje("Vehículo confirmado!, tu vehiculo es un/a "+evento.getViaje().getVehiculo().toString()+"\n Esperando que te asignen chofer...");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOCHOFER)) {
            this.vista.actualizarEstadoViaje("Chofer confirmado!, tu chofer será "+evento.getViaje().getChofer().getNombre()+"\n Ya comenzo tu viaje! Disfutalo");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.PAGADO)) {
            this.vista.actualizarEstadoViaje("Gracias por elegirnos!");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.FINALIZADO)) {
            this.vista.actualizarEstadoViaje("Tu chofer a finalizado el viaje, Hasta la proxima!");
        }
    }
}