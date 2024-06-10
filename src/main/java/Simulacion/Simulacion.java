package Simulacion;

import models.Sistema;
import vista.*;

/**
 * Clase utilizada para la simulacion del viaje
 * El sistema quedara preparado para las interacciones del usuario a traves de las vistas inicializadas.
 */
public class Simulacion {
    public static void main(String[] args) {
        VistaAppCliente_SituacionViaje vistaSituacionViaje = new VistaAppCliente_SituacionViaje();
        VistaAppCliente_formulario vistaFormulario = new VistaAppCliente_formulario();
        VistaAppCliente_inicio vistaInicio = new VistaAppCliente_inicio();
        VistaAppCliente_login vistaLogin = new VistaAppCliente_login();
        VistaAppCliente_Registrarse vistaRegistrarse = new VistaAppCliente_Registrarse();
        VistaGeneral vistaGeneral = new VistaGeneral();
        VistaAppCliente_MisDatos vistaMisDatos = new VistaAppCliente_MisDatos();
        VistaAppCliente_MisViajes vistaMisViajes = new VistaAppCliente_MisViajes();

        Controlador controlador = new Controlador(vistaFormulario, vistaLogin, vistaInicio, vistaRegistrarse, vistaSituacionViaje, vistaMisDatos, vistaMisViajes);
        ControladorVentanaGeneral controladorGeneral = new ControladorVentanaGeneral(vistaGeneral, vistaSituacionViaje);



    }
}