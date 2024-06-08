package models;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;

/**
 * Clase utilizada para representar al chofer permanente que se extiende de la clsae chofer
 */
public class ChoferPermanente extends Chofer {
    private  static double plus_antiguedad = 0.02;
    private  static double plus_cant_hijos = 0.015;
    private GregorianCalendar fecha_ingreso;
    private double antiguedad;
    private double cant_Hijos;

    /**
     * Constructor utilizado para setear antiguedad,cant de hijos y fecha de ingreso del chofer permanente y utiliza el dni,nombre y aporte seteado anteriormente en la clase Chofer
     * @param b Recurso compartido
     * <b>Post: </b> El chofer permanente fue inicializado con sus datos y ganancia correspondiente
     */
    public ChoferPermanente(BolsaDeViajes b) {
        super(b);
        Random r = new Random();
        Utiles utiles = new Utiles();
        this.antiguedad = r.nextDouble() *25 + 1;
        this.cant_Hijos = r.nextInt(4) + 1;
        this.fecha_ingreso = utiles.generaFechaAleatoria();
    }

    /**
     * Metodo utilizado para calcular el salario del chofer permanente
     * <b>Pre: </b> El parametro fecha_inicio_mes debe ser una fecha valida
     * @param fecha_inicio_mes : El parametro es utilizado para saber en que dia se quiere calcular el salario del chofer
     * @return Devuelve el salario del chofer permanente
     * <b>Post: </b> El salario es calculado segun los viajes que realizo
     */
    @Override
    public double getSueldo(GregorianCalendar fecha_inicio_mes, Iterator<IViaje> viajes) {
        double salario = this.sueldo_basico;
        salario += sueldo_basico * (1 + this.antiguedad * plus_antiguedad);
        salario += sueldo_basico * (1 + plus_cant_hijos * cant_Hijos);
        salario *= (1-aportes);
        return salario;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChoferPermanente { ");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
