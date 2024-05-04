package Exception;

/**<br>
Clase extendida de la clase de java Exception
<br>Es utilizada para cuando hay un usuario nuevo que se quiere registrar con un nombre de usuario que ya este en uso por otro usuario
 */
public class UsuarioRepetidoException extends Exception{

    /**
    <br> Metodo que se utiliza para cuando hay un usuario que quiere utilizar un nombre de usuario ocupado por otro usuario
    <b> Pre: <b> El parametro arg0 no debe ser null
    @param arg0 Parametro que indica a que se debe que se haya lanzado la excepcion
     */
    public UsuarioRepetidoException(String arg0){
         super(arg0);
     }

    }

