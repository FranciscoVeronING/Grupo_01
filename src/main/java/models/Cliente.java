package models;
import com.sun.tools.jconsole.JConsoleContext;

import java.util.GregorianCalendar;
import java.util.Random;

import static java.util.Calendar.*;

/**
 * La clase cliente extendida de usuario representa a cada usuario que quiera hacer un pedido al sistema
 * La clase se extiende de usuario y es cloneable
 */

public class Cliente extends Usuario implements Cloneable {
    private String nombre;
    private String apellido;
    private String telefono;
    private String mail;
    private Direccion direccion;
    private GregorianCalendar fecha_nacimiento;

    /**
     * Constructor utilizado para setear nombre,apellido,telefono,mail,direccion y fecha de nacimiento al cliente
     * El nombre de usuario y la contrasenia se obtienen de la clase Usuario
     * <b>Pre: </b> El parametro nombre_usuario no puede ser null ni estar vacio
     * @param nombre_usuario : Parametro utilizado para almacenar el nombre de usuario
     * El parametro contrasenia no puede ser null ni estar vacio
     * @param contrasenia : Parametro utilizado para almacenar la contrasenia del usuario
     * El parametro nombre no puede ser null ni estar vacio
     * @param nombre : Parametro utilizado para almacenar el nombre del usuario
     * El parametro apellido no puede ser null ni estar vacio
     * @param apellido : Parametro utilizado para almacenar el apellido del usuario
     * El parametro telefono no puede ser null ni estar vacio
     * @param telefono : Parametro utilizado para almacenar el telefono del usuario
     * El parametro mail no puede ser null ni estar vacio
     * @param mail : Parametro utilizado para almacenar el mail del usuario
     * El parametro direccion no puede ser null ni estar vacio
     * @param direccion : Parametro utilizado para almacenar la direccion del usuario
     * El parametro fecha_nacimiento no puede ser null ni estar vacio
     * @param fecha : Parametro utilizado para almacenar la fecha de nacimiento del usuario
     *<b>Post: </b> El cliente sera inicializado con sus datos personales y nombre de usuario unico
*/

    public Cliente(String nombre_usuario, String contrasenia, String nombre, String apellido, String telefono, String mail, Direccion direccion, GregorianCalendar fecha){
        super(nombre_usuario,contrasenia);
        this.nombre = nombre;
        //this.contrasenia = contrasenia;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha;
    }

    /**
     * Constructor utilizado para setear nombre,apellido,telefono,mail,direccion y fecha de nacimiento al cliente de manera aleatoria
     * Los datos del usuario seran generados mediante metodos
     *<b>Post: </b> El cliente estara inicializado con sus datos personales y nombre de usuario unico generados de manera random
     */

    public Cliente(){
        super();
        Utiles utiles = new Utiles();
        this.nombre = utiles.generaNombre();
        this.apellido = utiles.generaApellido();
        this.telefono = utiles.generaTelefono();
        this.mail = utiles.generaMail(this.nombre, this.apellido);
        this.direccion =  utiles.generaDireccionAleatoria();
        this.fecha_nacimiento = utiles.generaFechaNacimientoAleatoria();
        this.setNombre_usuario(this.nombre+this.apellido);
        this.setContrasenia("1234");
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
     * <b>Pre: </b> El cliente debe ser clonable
     * <b>Post: </b> Se devolvera un clon a partir de la clonacion profunda
     * @return Un clon del cliente actual
     * @throws AssertionError En el caso que ocurra una excepción CloneNotSupportedException (lo que no debería suceder si Cliente implementa Cloneable correctamente)
     */
    @Override
    public Cliente clone() {
        try {
            Cliente clone = (Cliente) super.clone();
            clone.direccion =this.direccion.clone();
            clone.fecha_nacimiento = (GregorianCalendar) this.fecha_nacimiento.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String formatFecha(GregorianCalendar fecha){
        final StringBuilder sb = new StringBuilder("(");
        sb.append(fecha.get(YEAR)).append("/").append(fecha.get(MONTH) + 1).append("/").append(fecha.get(DAY_OF_MONTH)).append(")");
        return sb.toString();
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
        sb.append(", fecha_nacimiento=").append(formatFecha(fecha_nacimiento));
        sb.append('}');
        return sb.toString();
    }
}
