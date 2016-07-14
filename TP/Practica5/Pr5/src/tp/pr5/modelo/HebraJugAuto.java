package tp.pr5.modelo;

import tp.pr5.controlador.ControladorGUI;

public class HebraJugAuto implements Runnable {

	private ControladorGUI _c;

	public HebraJugAuto(ControladorGUI c) {

		_c = c;
	}

	@Override
	public void run() {

		try {
			
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_c.ponerAleatorio();
	}
}
