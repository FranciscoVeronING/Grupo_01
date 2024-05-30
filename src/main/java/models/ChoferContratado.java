package models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Es la clase utilizada para representar al chofer que es de tipo Contratado
 */
public class ChoferContratado extends Empleado{
    private double ganancia_viaje;

    /**
     * Constructor que utiliza el nombre y dni seteado de la clase Empleado y asigna las ganancias
     * <b>Pre: </b> nombre no puede ser null ni vacio
     * @param nombre : nombre del chofer contratado
     *<b>Pre: </b> dni no puede ser null ni vacio
     * @param dni : numero de dni del chofer contratado
     * <b>Pre: </b> ganancia no puede ser menor a 0
     * @param ganancia : Es la ganancia del chofer contratado
     */
    public ChoferContratado(String nombre, String dni, double ganancia) {
        super(nombre, dni);
        this.ganancia_viaje = ganancia;
    }

    /**
     * Metodo utilizado para calcular el sueldo del chofer contratado
     * <b>Pre:</b> La fecha indicada tiene que ser valida
     * @param fecha_inicio_mes : Parametro utilizado para calcular la fecha en la que se quiere verificar el sueldo del chofer contratado
     * <b>Pre: </b> EL sueldo debera ser mayor a cero
     * @return : El metodo devolvera el sueldo del chofer contratado
     */
    @Override
    public double getSueldo(GregorianCalendar fecha_inicio_mes, Iterator<IViaje> viajes) {
        double salario = 0;
        while (viajes.hasNext()){
            IViaje viaje = viajes.next();
            if (viaje.getPedido().getFecha().compareTo(fecha_inicio_mes) > 0)
                salario += (viaje.getCosto_viaje() * ganancia_viaje);
        }
        return salario;
    }

    @Override
    public String toString() {
        return "ChoferContratado {" + super.toString() +
                " ganancia_viaje=" + ganancia_viaje +
                " }";
    }
}