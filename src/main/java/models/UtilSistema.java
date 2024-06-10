package models;

public class UtilSistema {

    public static SistemaDTO sistemaDTOfromSistema(){
        SistemaDTO sistemaDTO = new SistemaDTO();
        sistemaDTO.setChoferes(Sistema.getInstancia().getChoferes());
        sistemaDTO.setVehiculos(Sistema.getInstancia().getVehiculos());
        sistemaDTO.setClientes(Sistema.getInstancia().getClientes());
        return sistemaDTO;
    }
}



