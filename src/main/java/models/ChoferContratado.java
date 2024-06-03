package models;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;

/**
 * Es la clase utilizada para representar al chofer que es de tipo Contratado
 */
public class ChoferContratado extends EmpleadoRunnable {
    private  double ganancia_viaje;

    /**
     * @param b Recurso compartido
     * <b>Post: </b> El chofer contratado fue inicializado con su ganancia correspondiente
     */
    public ChoferContratado(BolsaDeViajes b) {
        super(b);
        Random r = new Random();
        this.ganancia_viaje = r.nextDouble() * 50 + 1;
    }

    /**
     * Metodo utilizado para calcular el sueldo del chofer contratado
     * <b>Pre:</b> La fecha indicada tiene que ser valida
     * @param fecha_inicio_mes : Parametro utilizado para calcular la fecha en la que se quiere verificar el sueldo del chofer contratado
     * <b>Pre: </b> EL sueldo debera ser mayor a cero
     * @return : El metodo devolvera el sueldo del chofer contratado
     * <b>Post: </b> El salario es calculado segun los viajes que realizo
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