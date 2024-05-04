package models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Es la clase utilizada cuando el chofer es de tipo Contratado
 */
public class ChoferContratado extends Empleado {
    private double ganancia_viaje;

    /**
     * Es un construtor que utiliza el nombre y dni seteado de la clase empleado y asigna las ganancias
     * <b>Pre: </> nombre no puede ser null ni vacio
     * @param nombre nombre del chofer contratado
     *<b>Pre: </> dni no puede ser null ni vacio
     * @param dni numero de dni del chofer contratado
     * <b>Pre: </> ganancia no puede ser menor a 0
     * @param ganancia Es la ganancia del chofer contratado
     */
    public ChoferContratado(String nombre, String dni, double ganancia) {
        super(nombre, dni);
        this.ganancia_viaje = ganancia;
    }

    /**
     * Metodo utilizado para calcular el sueldo del chofer contratado
     * <b>Pre:</> La fecha indicada tiene que ser valida
     * @param fecha_inicio_mes Parametro utilizado para calcular la fecha en la que se quiere verificar el sueldo del chofer contratado
     * <b>Pre: </> EL sueldo debera ser mayor a cero
     * @return El metodo devolvera el sueldo del chofer contratado
     */
    @Override
    public double getSueldo(GregorianCalendar fecha_inicio_mes) {
        double salario = 0;
        Sistema e = Sistema.getInstancia();
        Iterator<IViaje> viajes = e.getViajesChofer(this);
        while (viajes.hasNext() && viajes.next().getPedido().getFecha().compareTo(fecha_inicio_mes) > 0){
            IViaje viaje = viajes.next();
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