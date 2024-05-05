package models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import Exception.UsuarioRepetidoException;

/**
 * La clase cliente extendida de usuario representa a cada usuario que quiera hacer un pedido al sistema
 * La clase se extiende de usuario y es cloneable
 *
 */

public class Cliente extends Usuario implements Cloneable{
    private String nombre;
    private String apellido;
    private String telefono;
    private String mail;
    private Direccion direccion;
    private GregorianCalendar fecha_nacimiento;

    //Lanzar excepcion si nombre de usuario ya existe

    /**
     * Constructor utilizado para setear nombre,apellido,telefono,mail,direccion y fecha de nacimiento al cliente
     * El nombre de usuario y la contrasenia se obtienen de la clase Usuario
     * <b>Pre: </> El parametro nombre_usuario no puede ser null ni estar vacio
     * @param nombre_usuario Parametro utilizado para almacenar el nombre de usuario
     * El parametro contrasenia no puede ser null ni estar vacio
     * @param contrasenia Parametro utilizado para almacenar la contrasenia del usuario
     * El parametro nombre no puede ser null ni estar vacio
     * @param nombre Parametro utilizado para almacenar el nombre del usuario
     * El parametro apellido no puede ser null ni estar vacio
     * @param apellido Parametro utilizado para almacenar el apellido del usuario
     * El parametro telefono no puede ser null ni estar vacio
     * @param telefono Parametro utilizado para almacenar el telefono del usuario
     * El parametro mail no puede ser null ni estar vacio
     * @param mail Parametro utilizado para almacenar el mail del usuario
     * El parametro direccion no puede ser null ni estar vacio
     * @param direccion Parametro utilizado para almacenar la direccion del usuario
     * El parametro fecha_nacimiento no puede ser null ni estar vacio
     * @param fecha_nacimiento Parametro utilizado para almacenar la fecha de nacimiento del usuario
     */
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

    /**
     * Metodo utilizado para que el usuario califique al chofer
     * <b>Pre: </> El parametro chofer debe existir, no puede ser null ni estar vacio
     * @param chofer Parametro que identifica al chofer que realizo el viaje solicitado por el usuario
     */
    public void calificar_Chofer(Chofer chofer){
        int calif, n = 11;
        calif = (int) (Math.random() * n) + 1;
        chofer.setCalificacion_clientes(calif);
   }

    /**
     * Metodo que representa que el usuario pague el viaje realizado
     */
    public void pagar_viaje() {
        IViaje v = Sistema.getInstancia().getViajeActivoCliente(this);
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

    @Override
    public Cliente clone() {
        try {
            Cliente clone = (Cliente) super.clone();
            clone.direccion = (Direccion) this.direccion.clone();
            clone.fecha_nacimiento = (GregorianCalendar) this.fecha_nacimiento.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
