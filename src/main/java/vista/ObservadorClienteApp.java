package vista;

import models.Cliente;
import models.ClienteAppRunneable;
import models.EventoSistema;

import java.util.Observable;

/**
 * La clase ObservadorClienteApp extiende ObservadorAbstracto y actúa como un observador específico para los eventos relacionados con los usuarios que utilizan la app.
 */
public class ObservadorClienteApp extends ObservadorAbstracto{
    IVistaAppCliente_SituacionViaje vista ;
    private ClienteAppRunneable cliente;
    public ObservadorClienteApp(Observable observado, IVistaAppCliente_SituacionViaje vista) {
        super(observado);
        this.vista = vista;
        this.cliente = (ClienteAppRunneable) observado;
        this.cliente.addObserver(this);
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
