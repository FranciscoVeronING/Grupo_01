package models;

/**
 * La clase que representa a cualquier usuario que se quiera registrar en el sistema
 */
public abstract class Usuario {
    protected  String  nombre_usuario;
    protected String contrasenia;

    /**
     * Constructor utilizado para setear el nombre de usuario y contrasenia al usuario
     * <b>Pre: </b> El nombre de usuario no puede ser null ni estar vacio
     * @param nombre_usuario : Parametro utilizado para almacenar el nombre de usuario
     * <b>Pre: </b> La contrasenia no puede ser null ni estar vacio
     * @param contrasenia : Parametro que almacena la contrasenia del usuario
     */
    public Usuario (String nombre_usuario, String contrasenia) {
        this.contrasenia = contrasenia;
        this.setNombre_usuario(nombre_usuario);
    }

    public Usuario() {
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public String getContrasenia() {return contrasenia;}

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}