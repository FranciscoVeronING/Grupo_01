package models;

/**
 * Clase utilizada para cuando el usuario es registrado como administrador
 */
public class Administrador extends Usuario{

    /**
     * Constructor utilizado para guardar nombre de usuario y contrasenia al administrador
     * <b>Pre: </b> El nombre_usuario no puede ser null ni estar vacio
     * <b>Pre: </b> Contrasenia no puede ser null ni estar vacio
     * @param nombre_usuario : Es el parametro donde se guardara el nombre de usuario del administrador
     * @param contrasenia : Es el parametro donde se guardara la contrasenia de usuario del administrador
     *
     */
    public Administrador(String nombre_usuario, String contrasenia){
        super(nombre_usuario,contrasenia);
    }
}
