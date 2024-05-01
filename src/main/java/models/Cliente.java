package models;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Funciones Cliente:
 * * Registro en sistema
 * * formulario de solicitud de viaje
 * * pago de viaje
 * * calificacion chofer
 * * visaulizacion de sus viajes
 *
 *
 */
public class Cliente {
    private String nombre;
    private String apellido;
    private String contrasenia;
    private String nombre_usuario;
    private String telefono;
    private String mail;
    private Direccion direccion;
    private GregorianCalendar fecha_nacimiento;
    private ArrayList<Viaje> viajes;

    //Lanzar excepcion si nombre de usuario ya existe
    public Cliente(String nombre, String apellido, String contrasenia, String nombre_usuario, String telefono, String mail, Direccion direccion, GregorianCalendar fecha_nacimiento) throws UsuarioRepetidoException{
        if (Sistema.getInstancia().validarUsuario(nombre_usuario))
            this.nombre_usuario = nombre_usuario;
        else throw new UsuarioRepetidoException();
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.mail = mail;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.viajes = new ArrayList<Viaje>();

    }

    public Cliente(String nombre) {
        this.nombre = nombre;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
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

    public void setViajes(Viaje viaje) {
        this.viajes.add(viaje);
    }

    public void calificar_Chofer(Chofer chofer){
        int calif, n = 11;
        calif = (int) (Math.random() * n) + 1;
        chofer.setCalificacion_clientes(calif);
   }

   public void ver_viajes(){
       for (int i = 0; i < this.viajes.size(); i++) {
           this.viajes.get(i).toString();
       }
   }

    public void pagar_viaje(Viaje viaje) {
        viaje.setEstado_de_viaje("pagado");
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
