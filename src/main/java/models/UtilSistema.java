package models;
/**
 * Clase de utilidades para el sistema que proporciona métodos para la conversión de datos.
 */
public class UtilSistema {
    /**
     * Convierte la instancia actual del sistema en un objeto SistemaDTO.
     * <b>Pre:</b> La instancia del sistema debe estar inicializada.
     * <b>Post:</b> Se genera un objeto SistemaDTO con los datos de la instancia actual del sistema.
     *
     * @return Un objeto SistemaDTO que contiene los datos de la instancia actual del sistema.
     */
    public static SistemaDTO sistemaDTOfromSistema(){
        SistemaDTO sistemaDTO = new SistemaDTO();
        sistemaDTO.setChoferes(Sistema.getInstancia().getChoferes());
        sistemaDTO.setVehiculos(Sistema.getInstancia().getVehiculos());
        sistemaDTO.setClientes(Sistema.getInstancia().getClientes());
        sistemaDTO.setClientesApp(Sistema.getInstancia().getClientesApp());
        return sistemaDTO;
    }
}



