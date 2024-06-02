package models;
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

    //Lanzar excepcion si nombre de usuario ya existe

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
     * @param fecha_nacimiento : Parametro utilizado para almacenar la fecha de nacimiento del usuario
     */
    public Cliente(){
        super();
        this.nombre = generaNombre();
        this.apellido = generaApellido();
        this.telefono = generaTelefono();
        this.mail = generaMailAleatorio();
        this.direccion = direccion;             //FALTA VER ESTO
        this.fecha_nacimiento = fecha_nacimiento;
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
     * Metodo utilizado para que el usuario califique al chofer
     * <b>Pre: </b> El parametro chofer debe existir, no puede ser null ni estar vacio
     * @param chofer : Parametro que identifica al chofer que realizo el viaje solicitado por el usuario
     */
    public void calificar_Chofer(Empleado chofer){
        int calif, n = 11;
        calif = (int) (Math.random() * n) + 1;
        chofer.setCalificacion_clientes(calif);
   }

    /**
     * Metodo que representa que el usuario pague el viaje realizado y califica al chofer.
     */
    public void pagar_viaje() {
        Sistema e = Sistema.getInstancia();
        IViaje v = e.getViajeActivoCliente(this);
        Sistema.getInstancia().pagarViaje(v);
        this.calificar_Chofer(v.getChofer());
    }

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

    public String generaNombre(){
        String[] nombres = {"Juan", "Pedro", "Pablo", "Maria", "Jose", "Ana", "Lucia", "Carlos", "Fernando", "Sofia", "Lionel", "Guille", "Ivonne", "Matias", "Maite", "Francisco", "Martin", "Dario", "Tomas", "Homero"};
        Random rand = new Random();
        return nombres[rand.nextInt(nombres.length)];
    }

    public String generaApellido(){
        String[] apellidos = {"Gonzalez", "Rodriguez", "Perez", "Sanchez", "Ramirez", "Torres", "Dominguez", "Castillo", "Gutierrez", "Hernandez", "Lopez", "Martinez", "Rivera", "Mendoza", "Vasquez", "Castro", "Ortiz", "Ruiz", "Romero", "Alvarez"};
        Random rand = new Random();
        return apellidos[rand.nextInt(apellidos.length)];
    }

    public String generaTelefono() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(7);
        for (int i = 0; i < 7; i++) {
            int num = rand.nextInt(10); // Genera un número aleatorio entre 0 y 9
            sb.append(num);
        }
        return "223" + sb.toString();
    }

    public String generaMailAleatorio() {
        String caracteres = "abcdefghijklmnopqrstuvwxyz";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = rand.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        sb.append("@gmail.com");
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
