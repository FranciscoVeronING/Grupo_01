package vista;

import java.util.Observable;
import java.util.Observer;
/**
 * Es una clase abstracta que implementa la interfaz Observer y actúa como un observador genérico en el patrón de diseño Observador
 */
public abstract class ObservadorAbstracto implements Observer {
protected Observable observado;


	public ObservadorAbstracto(Observable observado) {
	super();
	this.observado = observado;
	this.observado.addObserver(this);
}
	@Override
	public void update(Observable obs, Object arg) {
		if(obs!= this.observado) {
			throw new IllegalArgumentException();
		}		
	}
	
}
