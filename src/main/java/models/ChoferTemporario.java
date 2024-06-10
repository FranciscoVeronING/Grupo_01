package models;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;


/**
 * Clase utilizada para representar al chofer temporario que se extiende de la clase chofer
 */
public class ChoferTemporario extends Chofer implements Serializable {
    private final double plus_x_cant_viajes;

 /**
  * Constructor que setea el plus por cantidad de viajes
  * @param b Recurso compartido
  * <b>Post: </b> El chofer temporario fue inicializado con sus datos y ganancia correspondiente
  */
 // TODO = Borrar cuando se haya creado el campo de viajes max por chofer
    public ChoferTemporario(BolsaDeViajes b) {
        super(b);
        Random r = new Random();
        this.plus_x_cant_viajes = r.nextDouble()*50 + 1;
    }

    /* TODO = Reemplazo de el de arriba
    * public ChoferTemporario(BolsaDeViajes b, int max) {
        super(b, max);
        Random r = new Random();
        this.plus_x_cant_viajes = r.nextDouble()*50 + 1;
    }
    * */

    public ChoferTemporario() {
        Random r = new Random();
        this.plus_x_cant_viajes = r.nextDouble()*50 + 1;
    }

    public double getPlus_x_cant_viajes() {
        return plus_x_cant_viajes;
    }

    /**
  *
  * <b>Pre:</b>La fecha debe ser valida
  * @param fecha_inicio_mes La fecha en la que se quiere saber el salario del chofer temporario
  * @return Devuelve el sueldo del chofer temporario
  * <b>Post: </b>El sueldo es incrementado segun sus viajes y aportes
  */
    @Override
    public double getSueldo(GregorianCalendar fecha_inicio_mes, Iterator<IViaje> viajes) {
        double salario = sueldo_basico;
        int cant_viajes = 0;
        while (viajes.hasNext() && viajes.next().getPedido().getFecha().compareTo(fecha_inicio_mes) > 0)
            cant_viajes++;
        salario *= (1 + plus_x_cant_viajes * cant_viajes);
        salario *= (1 - aportes);
        return salario;
    }

    @Override
    public String toString() {
        return "ChoferTemporario {" + super.toString() +
                ", plus_x_cant_viajes=" + plus_x_cant_viajes +
                " }" ;
    }
}
