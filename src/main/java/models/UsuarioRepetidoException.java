package models;

public class UsuarioRepetidoException extends Exception{
    public UsuarioRepetidoException () {
        super("El nombre del usuario ya esta ocupado");
    }
}
