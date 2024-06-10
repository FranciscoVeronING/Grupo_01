package models;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
/**
 * Clase utilitaria para la serialización y deserialización del sistema en formato XML.
 */

public class SistemaXML {
    private static final String File_Name = "SISTEMA.xml";
    /**
     * Método para guardar el estado del sistema en un archivo XML.
     */
    public static void grabaSistema() {
        SistemaDTO sistemaDTO = UtilSistema.sistemaDTOfromSistema();
        XMLEncoder encoder = null;
        try{
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(File_Name)));
        }
        catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR AL CREAR EL ARCHIVO");
        }
        assert encoder != null;
        encoder.writeObject(sistemaDTO);
        encoder.close();
    }
    /**
     * Método para cargar el estado del sistema desde un archivo XML.
     *
     * @return Un objeto SistemaDTO que representa el estado del sistema cargado desde el archivo XML.
     */
    public static SistemaDTO cargaSistema() {
        XMLDecoder decoder = null;
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(File_Name)));
        }
        catch(FileNotFoundException e){
        }

        assert decoder != null;
        SistemaDTO sistemaDTO = (SistemaDTO) decoder.readObject();
        return sistemaDTO;
    }
}
