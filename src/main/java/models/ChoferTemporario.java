package models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;


/**
 * Clase utilizada para representar al chofer temporario que se extiende de la clsae chofer
 */
public class ChoferTemporario extends Chofer {
    private final double plus_x_cant_viajes;

 /**
  * Constructor que setea el plus por cantidad de viajes
  * <b>Pre:</b> El parametro dni no puede ser null ni estar vacio
  * @param dni dni del chofer temporario seteado en la clase chofer
  * <b>Pre: </b>El parametro nombre no puede ser null ni estar vacio
  * @param nombre nombre del chofer temporario seteado en la clase chofer
  *               <b>Pre:</b> El parametro aportes debe ser mayor a cero
  * @param aportes Aportes del chofer temporario seteado en la clase chofer
  *                 <b>Pre: </b> plusCantViajes debe ser mayor a cero
  * @param plusCantViajes Porcentaje que se aplica al básico según la cantidad de viajes
  */
    public ChoferTemporario(BolsaDeViajes b, String dni, String nombre, double aportes, double plusCantViajes) {
        super(b, dni, nombre, aportes);
        this.plus_x_cant_viajes = plusCantViajes;
    }

 /**
  *
  * <b>Pre:</b>La fecha debe ser valida
  * @param fecha_inicio_mes La fecha en la que se quiere saber el salario del chofer temporario
  * @return Devuelve el sueldo del chofer temporario
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
