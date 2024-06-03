package prueba;

import models.Sistema;
import vista.*;

public class Prueba {
    public static void main(String[] args) {
        VistaAppCliente_SituacionViaje vistaSituacionViaje = new VistaAppCliente_SituacionViaje();
        VistaAppCliente_formulario vistaFormulario = new VistaAppCliente_formulario();
        VistaAppCliente_inicio vistaInicio = new VistaAppCliente_inicio();
        VistaAppCliente_login vistaLogin = new VistaAppCliente_login();
        VistaAppCliente_Registrarse vistaRegistrarse = new VistaAppCliente_Registrarse();
        VistaGeneral vistaGeneral = new VistaGeneral();

        Controlador controlador = new Controlador(vistaFormulario, vistaLogin, vistaInicio, vistaRegistrarse, vistaSituacionViaje);
        ControladorVentanaGeneral controladorGeneral = new ControladorVentanaGeneral(vistaGeneral);

        ObservadorVentanaGral observadorGeneral = new ObservadorVentanaGral(Sistema.getInstancia().getBolsaDeViajes());

    }
}