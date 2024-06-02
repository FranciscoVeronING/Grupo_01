package Exception;

import models.Usuario;

public class UsuarioIncorrectoException extends Exception {
    private String usuario;
    private String contrasenia;

    public UsuarioIncorrectoException(String usuario, String contrasenia) {
        super("Usuario o contrasenia incorrectos");
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
}
