package vista;

import java.util.Observable;
import java.util.Observer;
/**
 * Es una clase abstracta que implementa la interfaz Observer y actúa como un observador genérico
 * en el patrón de diseño Observador.
 * Las clases que hereden de esta deberán implementar el método update para manejar
 * las actualizaciones del objeto observado.
 */
public abstract class ObservadorAbstracto implements Observer {
protected Observable observado;

	/**
	 * Constructor para inicializar el observador con el objeto Observable a observar.
	 *
	 * @param observado El objeto Observable que este observador va a observar.
	 */
	public ObservadorAbstracto(Observable observado) {
	super();
	this.observado = observado;
	this.observado.addObserver(this);
}
/**
	 * Método llamado cuando el objeto observado ha cambiado. Este método verifica que el objeto
	 * que ha cambiado es el mismo que este observador está observando y lanza una excepción
	 * si no es así.
	 *
	 * @param obs El objeto Observable que ha notificado el cambio.
	 * @param arg Un argumento pasado por el objeto Observable, generalmente detalles del cambio.
	 */
	@Override
	public void update(Observable obs, Object arg) {
		if(obs!= this.observado) {
			throw new IllegalArgumentException();
		}		
	}
	
}
