package models;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class SistemaXML {
    private static final String File_Name = "SISTEMA.xml";
    public static void grabaSistema() {
        SistemaDTO sistemaDTO = UtilSistema.sistemaDTOfromSistema();
        XMLEncoder encoder = null;
        try{
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(File_Name)));
        }
        catch(FileNotFoundException fileNotFound){
            System.out.println("ERROR AL CREAR EL ARCHIVO");
        }
        encoder.writeObject(sistemaDTO);
        encoder.close();
    }

    public static SistemaDTO cargaSistema() {
        XMLDecoder decoder = null;
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(File_Name)));
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR AL ABRIR EL ARCHIVO");
        }

        SistemaDTO sistemaDTO = (SistemaDTO) decoder.readObject();
        return sistemaDTO;
    }
}
