package models;
import Exception.UsuarioRepetidoException;

public abstract class Usuario {
    protected  String  nombre_usuario;
    protected String contrasenia;

    public Usuario (String nombre_usuario, String contrasenia) {
        this.contrasenia = contrasenia;
        this.setNombre_usuario(nombre_usuario);
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

}
