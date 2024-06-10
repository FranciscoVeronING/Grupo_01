package models;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Random;

/**
 * Clase utilizada para representar al chofer permanente que se extiende de la clsae chofer
 */
public class ChoferPermanente extends Chofer implements Serializable {
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
    // TODO = Borrar cuando se haya creado el campo de viajes max por chofer
    public ChoferPermanente(BolsaDeViajes b) {
        super(b);
        Random r = new Random();
        Utiles utiles = new Utiles();
        this.antiguedad = r.nextDouble() *25 + 1;
        this.cant_Hijos = r.nextInt(4) + 1;
        this.fecha_ingreso = utiles.generaFechaAleatoria();
    }

    /* TODO = Reemplazo de arriba
    * public ChoferPermanente(BolsaDeViajes b, int max) {
        super(b, max);
        Random r = new Random();
        Utiles utiles = new Utiles();
        this.antiguedad = r.nextDouble() *25 + 1;
        this.cant_Hijos = r.nextInt(4) + 1;
        this.fecha_ingreso = utiles.generaFechaAleatoria();
    }
    * */

    public ChoferPermanente() {
    }

    public static double getPlus_antiguedad() {
        return plus_antiguedad;
    }

    public static void setPlus_antiguedad(double plus_antiguedad) {
        ChoferPermanente.plus_antiguedad = plus_antiguedad;
    }

    public static double getPlus_cant_hijos() {
        return plus_cant_hijos;
    }

    public static void setPlus_cant_hijos(double plus_cant_hijos) {
        ChoferPermanente.plus_cant_hijos = plus_cant_hijos;
    }

    public GregorianCalendar getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(GregorianCalendar fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public double getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(double antiguedad) {
        this.antiguedad = antiguedad;
    }

    public double getCant_Hijos() {
        return cant_Hijos;
    }

    public void setCant_Hijos(double cant_Hijos) {
        this.cant_Hijos = cant_Hijos;
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
