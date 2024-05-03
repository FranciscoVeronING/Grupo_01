package models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import Exception.UsuarioRepetidoException;


public class Cliente extends Usuario{
    private String nombre;
    private String apellido;
    private String telefono;
    private String mail;
    private Direccion direccion;
    private GregorianCalendar fecha_nacimiento;

    //Lanzar excepcion si nombre de usuario ya existe
    public Cliente(String nombre_usuario, String contrasenia,String nombre, String apellido, String telefono, String mail, Direccion direccion, GregorianCalendar fecha_nacimiento){
        super(nombre_usuario,contrasenia);
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public GregorianCalendar getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(GregorianCalendar fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void calificar_Chofer(Chofer chofer){
        int calif, n = 11;
        calif = (int) (Math.random() * n) + 1;
        chofer.setCalificacion_clientes(calif);
   }

    public void pagar_viaje(Viaje viaje) {
        Viaje v = Sistema.getInstancia().getViajeActivoCliente(this);
        Sistema.getInstancia().pagarViaje(v);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cliente{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", contrasenia='").append(contrasenia).append('\'');
        sb.append(", nombre_usuario='").append(nombre_usuario).append('\'');
        sb.append(", telefono='").append(telefono).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", direccion=").append(direccion);
        sb.append(", fecha_nacimiento=").append(fecha_nacimiento);
        sb.append('}');
        return sb.toString();
    }
}
