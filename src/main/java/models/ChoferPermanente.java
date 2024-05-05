package models;

import java.util.GregorianCalendar;

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
     *<b>Pre:</> El parametro dni no puede ser null ni estar vacio
     *  @param dni Numero de dni del chofer permanente
     *  <b>Pre:</> El parametro nombre no puede ser null ni estar vacio
     * @param nombre Nombre del chofer permanente
     * <b>Pre: </>El paramentro aportes debe ser mayor de cero
     * @param aportes Aportes del chofer permanente
     * <b>Pre:</> El parametro fecha_ingreso no puede ser null ni estar vacio
     * @param fecha_ingreso Fecha en la que ingreso el chofer permanente
     *<b>Pre:</> El parametro antiguedad debe ser mayor que cero
     * @param antiguedad Antiguedad que tiene el chofer permanente trabajando
     * <b>Pre:</> El parametro cant_Hijos debe ser cero o mayor
     * @param cant_Hijos La cantidad de hijos que el chofer permanente
     */
    public ChoferPermanente(String dni, String nombre, double aportes,GregorianCalendar fecha_ingreso, double antiguedad, double cant_Hijos) {
        super(dni, nombre, aportes);
        this.antiguedad = antiguedad;
        this.cant_Hijos = cant_Hijos;
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * Metodo utilizado para calcular el salario del chofer permanente
     * <b>Pre: </> El parametro fecha_inicio_mes debe ser una fecha valida
     * @param fecha_inicio_mes El parametro es utilizado para saber en que dia se quiere calcular el salario del chofer
     * @return Devuelve el salario del chofer permanente
     */
    @Override
    public double getSueldo(GregorianCalendar fecha_inicio_mes) {
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
