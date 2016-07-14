package tp.pr5.controlador;

import tp.pr5.modelo.HebraJugAuto;

/**
 * @author Sergio
 * 
 * Esta clase se encarga de recoger todos los métodos relacionados
 * con el modo de jugador automático para todos los modos de juego
 */
public class ModoAuto extends Thread implements Modo {

	private ControladorGUI _c;
	private Thread _modoAuto;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param c - Objeto de la clase ControladorGUI
	 */
	public ModoAuto(ControladorGUI c) {
		
		_c = c;
	}
	
	/* (non-Javadoc)
	 * @see tp.pr5.controlador.Modo#comienzaJugar()
	 */
	@Override
	public void comienzaJugar() {

		_modoAuto = new Thread(new HebraJugAuto(_c));
		
		_modoAuto.start();
	}

	/* (non-Javadoc)
	 * @see tp.pr5.controlador.Modo#terminaJugar()
	 */
	@Override
	public void terminaJugar() {

		if(_modoAuto != null)
			_modoAuto.interrupt();
	}
}
