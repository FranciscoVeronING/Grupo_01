package models;

import java.util.GregorianCalendar;
import java.util.Random;

public class Utiles {
    public String generaNombre(){
        String[] nombres = {"Juan", "Pedro", "Pablo", "Maria", "Ximena", "Ana", "Lucia", "Carlos", "Fernando", "Sofia", "Lionel", "Guille", "Ivonne", "Matias", "Maite", "Francisco", "Martin", "Dario", "Ximena", "Homero"};
        Random rand = new Random();
        return nombres[rand.nextInt(nombres.length)];
    }

    public String generaApellido(){
        String[] apellidos = {"Gonzalez", "Rodriguez", "Perez", "Sanchez", "Ramirez", "Torres", "Dominguez", "Castillo", "Gutierrez", "Hernandez", "Lopez", "Martinez", "Rivera", "Mendoza", "Vasquez", "Castro", "Ortiz", "Ruiz", "Romero", "Alvarez"};
        Random rand = new Random();
        return apellidos[rand.nextInt(apellidos.length)];
    }

    public String generaTelefono() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(7);
        for (int i = 0; i < 7; i++) {
            int num = rand.nextInt(10); // Genera un número aleatorio entre 0 y 9
            sb.append(num);
        }
        return "223" + sb.toString();
    }

    public String generaMail(String nombre, String apellido) {
        return nombre + apellido + "@gmail.com";
    }
    public Direccion generaDireccionAleatoria() {
        String[] nombres = {"Calle", "Avenida", "Bulevar", "Camino", "Paseo"};
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        Random rand = new Random();
        String nombre = nombres[rand.nextInt(nombres.length)] + " " + (rand.nextInt(100) + 1);
        String altura = String.valueOf(rand.nextInt(10000) + 1);
        String piso = String.valueOf(rand.nextInt(20) + 1);
        String letra = letras[rand.nextInt(letras.length)];

        return new Direccion(nombre, altura, piso, letra);
    }

    public GregorianCalendar generaFechaNacimientoAleatoria() {
        Random rand = new Random();
        int ano = rand.nextInt((2003 - 1950) + 1) + 1950;
        int mes = rand.nextInt(12);
        int dia = rand.nextInt(28) + 1;
        return new GregorianCalendar(ano, mes, dia);        //creo que mandandole el numero de mes deberia funcar
    }

    public GregorianCalendar generaFechaAleatoria() {
        Random rand = new Random();
        int ano = rand.nextInt((2020 - 2000) + 1) + 2000; // Genera un año aleatorio entre 2000 y 2020
        int mes = rand.nextInt(12);
        int dia = rand.nextInt(28) + 1;
        return new GregorianCalendar(ano, mes, dia);        //creo que mandandole el numero de mes deberia funcar
    }

    public String generaDNI() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int num = rand.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }

    public String generaPatente() {
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
