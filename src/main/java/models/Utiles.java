package models;

import java.util.GregorianCalendar;
import java.util.Random;

import static java.util.Calendar.*;

/**
 * Clase útil para crear datos de prueba o ejemplos de manera aleatoria.
 * <b>Pre:</b> Los datos pasados como parámetros para generar usuarios con información aleatoria no deben ser null ni estar vacíos.
 * <b>Post:</b> Se generan datos aleatorios correspondientes al tipo de dato solicitado.
 */
public class Utiles {
    /**
     * Genera una zona aleatoria entre "ESTANDAR", "SIN ASFALTAR" y "PELIGROSA".
     *
     * @return Una cadena con la zona generada aleatoriamente.
     */
    public static String generaZona() {
        String[] zonas = {"ESTANDAR", "SIN ASFALTAR", "PELIGROSA"};
        Random rand = new Random();
        return zonas[rand.nextInt(zonas.length)];
    }
    /**
     * Formatea una fecha dada en formato (YYYY/MM/DD).
     *
     * @param fecha La fecha a formatear.
     * @return Una cadena con la fecha formateada.
     */
    public static String formatFecha(GregorianCalendar fecha){
         StringBuilder sb = new StringBuilder("(");
        sb.append(fecha.get(YEAR)).append("/").append(fecha.get(MONTH) + 1).append("/").append(fecha.get(DAY_OF_MONTH)).append(")");
        return sb.toString();
    }
    /**
     * Genera un nombre aleatorio de una lista predefinida de nombres.
     *
     * @return Una cadena con el nombre generado aleatoriamente.
     */
    public static String generaNombre(){
        String[] nombres = {"Juan", "Pedro", "Pablo", "Maria", "Ximena", "Ana", "Lucia", "Carlos", "Fernando", "Sofia", "Lionel", "Guille", "Ivonne", "Matias", "Maite", "Francisco", "Martin", "Dario", "Ximena", "Homero"};
        Random rand = new Random();
        return nombres[rand.nextInt(nombres.length)];
    }
    /**
     * Genera un apellido aleatorio de una lista predefinida de apellidos.
     *
     * @return Una cadena con el apellido generado aleatoriamente.
     */
    public static String generaApellido(){
        String[] apellidos = {"Gonzalez", "Rodriguez", "Perez", "Sanchez", "Ramirez", "Torres", "Dominguez", "Castillo", "Gutierrez", "Hernandez", "Lopez", "Martinez", "Rivera", "Mendoza", "Vasquez", "Castro", "Ortiz", "Ruiz", "Romero", "Alvarez"};
        Random rand = new Random();
        return apellidos[rand.nextInt(apellidos.length)];
    }
    /**
     * Genera un número de teléfono aleatorio de 7 dígitos precedido por "223".
     *
     * @return Una cadena con el número de teléfono generado aleatoriamente.
     */
    public static String generaTelefono() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(7);
        for (int i = 0; i < 7; i++) {
            int num = rand.nextInt(10); // Genera un número aleatorio entre 0 y 9
            sb.append(num);
        }
        return "223" + sb.toString();
    }
    /**
     * Genera una dirección de correo electrónico usando el nombre y apellido proporcionados.
     *
     * @param nombre El nombre para la dirección de correo electrónico.
     * @param apellido El apellido para la dirección de correo electrónico.
     * @return Una cadena con la dirección de correo electrónico generada.
     */
    public static String generaMail(String nombre, String apellido) {
        return nombre + apellido + "@gmail.com";
    }
    /**
     * Genera una dirección aleatoria.
     *
     * @return Un objeto Direccion con una dirección generada aleatoriamente.
     */
    public static Direccion generaDireccionAleatoria() {
        String[] nombres = {"Calle", "Avenida", "Bulevar", "Camino", "Paseo"};
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        Random rand = new Random();
        String nombre = nombres[rand.nextInt(nombres.length)] + " " + (rand.nextInt(100) + 1);
        String altura = String.valueOf(rand.nextInt(10000) + 1);
        String piso = String.valueOf(rand.nextInt(20) + 1);
        String letra = letras[rand.nextInt(letras.length)];

        return new Direccion(nombre, altura, piso, letra);
    }
    /**
     * Genera una fecha de nacimiento aleatoria entre los años 1950 y 2003.
     *
     * @return Un objeto GregorianCalendar con la fecha de nacimiento generada.
     */
    public static GregorianCalendar generaFechaNacimientoAleatoria() {
        Random rand = new Random();
        int ano = rand.nextInt((2003 - 1950) + 1) + 1950;
        int mes = rand.nextInt(12);
        int dia = rand.nextInt(28) + 1;
        return new GregorianCalendar(ano, mes, dia);        //creo que mandandole el numero de mes deberia funcar
    }
    /**
     * Genera una fecha aleatoria entre los años 2000 y 2020.
     *
     * @return Un objeto GregorianCalendar con la fecha generada.
     */
    public static GregorianCalendar generaFechaAleatoria() {
        Random rand = new Random();
        int ano = rand.nextInt((2020 - 2000) + 1) + 2000; // Genera un año aleatorio entre 2000 y 2020
        int mes = rand.nextInt(12);
        int dia = rand.nextInt(28) + 1;
        return new GregorianCalendar(ano, mes, dia);        //creo que mandandole el numero de mes deberia funcar
    }
    /**
     * Genera un número de DNI aleatorio de 8 dígitos.
     *
     * @return Una cadena con el número de DNI generado.
     */
    public static String generaDNI() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int num = rand.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }
    /**
     * Genera una patente aleatoria de 3 letras seguidas por 3 números.
     *
     * @return Una cadena con la patente generada aleatoriamente.
     */
    public static String generaPatente() {
        StringBuilder sb = new StringBuilder(6);
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            sb.append((char)(r.nextInt(26) + 65));
        }
        for (int i = 0; i < 3; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

}
