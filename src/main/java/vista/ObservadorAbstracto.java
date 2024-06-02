package vista;

import java.util.Observable;
import java.util.Observer;

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
