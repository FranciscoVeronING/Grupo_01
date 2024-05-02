package models;

import java.util.GregorianCalendar;

public class ChoferPermanente extends Chofer {
    private  static double plus_antiguedad = 0.02;
    private  static double plus_cant_hijos = 0.015;
    private GregorianCalendar fecha_ingreso;
    private double antiguedad;
    private double cant_Hijos;

    public ChoferPermanente(String dni, String nombre, double aportes,GregorianCalendar fecha_ingreso, double antiguedad, double cant_Hijos) {
        super(dni, nombre, aportes);
        this.antiguedad = antiguedad;
        this.cant_Hijos = cant_Hijos;
        this.fecha_ingreso = fecha_ingreso;
    }


    @Override
    public double getSueldo() {
        double salario = this.sueldo_basico;
        salario += sueldo_basico * (1 + this.antiguedad * plus_antiguedad);
        salario += sueldo_basico * (1 + plus_cant_hijos * cant_Hijos);
        salario *= (1-aportes);
        return salario;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChoferPermanente{");
        sb.append("fecha_ingreso=").append(fecha_ingreso);
        sb.append(", antiguedad=").append(antiguedad);
        sb.append(", cant_Hijos=").append(cant_Hijos);
        sb.append(", sueldo_basico=").append(sueldo_basico);
        sb.append(", aportes=").append(aportes);
        sb.append(", calificacion_clientes=").append(calificacion_clientes);
        sb.append(", dni='").append(dni).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", ocupado=").append(ocupado);
        sb.append('}');
        return sb.toString();
    }
}
